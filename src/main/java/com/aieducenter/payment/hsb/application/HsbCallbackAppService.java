package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.callback.HsbPaymentCallbackParam;
import com.aieducenter.payment.hsb.application.dto.callback.HsbRefundCallbackParam;
import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;
import com.aieducenter.payment.hsb.application.mapper.HsbPaymentOrderMapper;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbRefundOrderRepository;
import com.aieducenter.payment.hsb.infrastructure.HsbConfig;
import com.aieducenter.payment.hsb.infrastructure.HsbSignUtil;
import com.aieducenter.payment.hsb.infrastructure.HsbSplicingUtil;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class HsbCallbackAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbCallbackAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbRefundOrderRepository refundOrderRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbConfig hsbConfig;
    private final BusinessSystemNotifier businessSystemNotifier;

    @Transactional
    public String handlePaymentCallback(HsbPaymentCallbackParam param, String rawBody) {
        log.info("Received HSB payment callback: mainOrderNo={}, ordrStcd={}",
            param.getMainOrdrNo(), param.getOrdrStcd());
        log.info("HSB payment callback raw body: {}", rawBody);

        String signStr = HsbSplicingUtil.createSign(rawBody, true);
        log.info("HSB payment callback sign string: {}", signStr);
        log.info("HSB payment callback sign value (Sign_Inf): {}", param.getSignInf());

        boolean verified = HsbSignUtil.verifySign(hsbConfig.getPlatformPublicKey(), signStr, param.getSignInf());

        if (!verified) {
            log.warn("HSB payment callback signature verification failed: mainOrderNo={}", param.getMainOrdrNo());
            return buildCallbackResponse();
        }

        HsbPaymentOrder order = paymentOrderRepository
            .findByBusinessMainOrderNo(param.getMainOrdrNo()).orElse(null);

        if (order == null) {
            log.warn("HSB payment callback: order not found, mainOrderNo={}", param.getMainOrdrNo());
            return buildCallbackResponse();
        }

        try {
            paymentLogRepository.save(new HsbPaymentLog(
                order.getPaymentOrderNo(), null, "PAYMENT_CALLBACK", "gatherPlaceorder",
                null, null, rawBody, 200, param.getOrdrStcd(), null,
                null, true, null
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB callback log: {}", e.getMessage());
        }

        if (order.getStatus() != HsbPaymentStatus.PENDING) {
            log.info("HSB payment callback: order already in terminal state, status={}", order.getStatus());
            return buildCallbackResponse();
        }

        if (param.isPaymentSuccess()) {
            Long actualAmount = yuanToFen(param.getOrdrAmt());
            order.markAsPaid(param.getPyTrnNo(), actualAmount);
            if (param.getPrimOrdrNo() != null) {
                order.setPaymentResult(order.getCshdkUrl(), order.getPayUrl(), order.getPayQrCode(), param.getPrimOrdrNo());
            }
        } else if (param.isPaymentFailed()) {
            order.markAsFailed();
        } else if (param.isPaymentExpired()) {
            order.markAsExpired();
        }
        paymentOrderRepository.save(order);

        HsbPaymentOrder finalOrder = order;
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                HsbPaymentOrderResponse response = HsbPaymentOrderMapper.convert(finalOrder);
                businessSystemNotifier.notify(finalOrder.getNotifyUrl(), response);
            }
        });

        return buildCallbackResponse();
    }

    @Transactional
    public String handleRefundCallback(HsbRefundCallbackParam param, String rawBody) {
        log.info("Received HSB refund callback: custRfndTrcno={}, refundRspSt={}",
            param.getCustRfndTrcno(), param.getRefundRspSt());
        log.info("HSB refund callback raw body: {}", rawBody);

        String signStr = HsbSplicingUtil.createSign(rawBody, true);
        log.info("HSB refund callback sign string: {}", signStr);
        log.info("HSB refund callback sign value (Sign_Inf): {}", param.getSignInf());

        boolean verified = HsbSignUtil.verifySign(hsbConfig.getPlatformPublicKey(), signStr, param.getSignInf());

        if (!verified) {
            log.warn("HSB refund callback signature verification failed: custRfndTrcno={}", param.getCustRfndTrcno());
            return buildRefundCallbackResponse(param.getIttpartyTms());
        }

        HsbRefundOrder refundOrder = refundOrderRepository
            .findByRefundOrderNo(param.getCustRfndTrcno()).orElse(null);

        if (refundOrder == null) {
            log.warn("HSB refund callback: order not found, custRfndTrcno={}", param.getCustRfndTrcno());
            return buildRefundCallbackResponse(param.getIttpartyTms());
        }

        try {
            paymentLogRepository.save(new HsbPaymentLog(
                refundOrder.getPaymentOrderNo(), refundOrder.getRefundOrderNo(),
                "REFUND_CALLBACK", "refundOrder",
                null, null, rawBody, 200, param.getRefundRspSt(), param.getRefundRspInf(),
                null, true, null
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB refund callback log: {}", e.getMessage());
        }

        if (refundOrder.getStatus().isTerminal()) {
            log.info("HSB refund callback: order already in terminal state, status={}", refundOrder.getStatus());
            return buildRefundCallbackResponse(param.getIttpartyTms());
        }

        if (param.isRefundSuccess()) {
            refundOrder.markAsSuccess(param.getSuperRefundNo());
        } else {
            refundOrder.markAsFailed();
        }
        refundOrderRepository.save(refundOrder);

        HsbRefundOrder finalOrder = refundOrder;
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                HsbRefundOrderResponse response = HsbPaymentOrderMapper.convert(finalOrder);
                businessSystemNotifier.notify(finalOrder.getNotifyUrl(), response);
            }
        });

        return buildRefundCallbackResponse(param.getIttpartyTms());
    }

    private String buildCallbackResponse() {
        String rcvTm = java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "{\"Svc_Rsp_St\":\"00\",\"Rcv_Tm\":\"" + rcvTm + "\"}";
    }

    private String buildRefundCallbackResponse(String ittpartyTms) {
        String rcvTm = java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "{\"Svc_Rsp_St\":\"00\",\"Rcv_Tm\":\"" + rcvTm + "\",\"Ittparty_Tms\":\"" + ittpartyTms + "\"}";
    }

    public String handleReconciliationCallback(String fileSmryInf, String signInf, MultipartFile file) {
        log.info("Received HSB reconciliation callback: fileSmryInf={}", fileSmryInf);

        String signStr = "File_Smry_Inf=" + fileSmryInf;
        boolean verified = verifyReconciliationSign(hsbConfig.getPlatformPublicKey(), signStr, signInf);

        if (!verified) {
            log.warn("HSB reconciliation callback signature verification failed");
            return buildReconciliationResponse(false);
        }

        if (file == null || file.isEmpty()) {
            log.warn("HSB reconciliation callback: empty file");
            return buildReconciliationResponse(false);
        }

        try {
            String dateDir = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path dirPath = Path.of(hsbConfig.getReconciliationStoragePath(), dateDir);
            Files.createDirectories(dirPath);

            String fileName = System.currentTimeMillis() + ".zip";
            Path filePath = dirPath.resolve(fileName);
            file.transferTo(filePath);

            log.info("HSB reconciliation file saved: {}, size: {}", filePath, file.getSize());
            return buildReconciliationResponse(true);
        } catch (IOException e) {
            log.error("HSB reconciliation callback: failed to save file", e);
            return buildReconciliationResponse(false);
        }
    }

    private String buildReconciliationResponse(boolean success) {
        return "{\"Svc_Rsp_St\":\"" + (success ? "00" : "01") + "\"}";
    }

    protected boolean verifyReconciliationSign(String platformPublicKey, String signStr, String signInf) {
        return HsbSignUtil.verifySign(platformPublicKey, signStr, signInf);
    }

    private Long yuanToFen(String yuan) {
        if (yuan == null || yuan.isBlank()) return null;
        return new BigDecimal(yuan).multiply(BigDecimal.valueOf(100)).longValue();
    }
}

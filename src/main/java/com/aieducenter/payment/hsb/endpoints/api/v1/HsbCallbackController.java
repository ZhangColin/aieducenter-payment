package com.aieducenter.payment.hsb.endpoints.api.v1;

import com.aieducenter.payment.hsb.application.HsbCallbackAppService;
import com.aieducenter.payment.hsb.application.dto.callback.HsbPaymentCallbackParam;
import com.aieducenter.payment.hsb.application.dto.callback.HsbRefundCallbackParam;
import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/hsb/callback")
@RequiredArgsConstructor
@Tag(name = "HSB Callback API", description = "惠市宝回调接口")
public class HsbCallbackController {

    private static final Logger log = LoggerFactory.getLogger(HsbCallbackController.class);

    private final HsbCallbackAppService callbackAppService;

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "接收建行惠市宝支付结果回调")
    public ResponseEntity<String> handlePaymentCallback(
            @RequestBody String rawBody
    ) {
        HsbPaymentCallbackParam param = JSON.parseObject(rawBody, HsbPaymentCallbackParam.class);
        log.info("Received HSB payment callback: mainOrderNo={}", param.getMainOrdrNo());
        String response = callbackAppService.handlePaymentCallback(param, rawBody);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @PostMapping(value = "/refund", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "接收建行惠市宝退款结果回调")
    public ResponseEntity<String> handleRefundCallback(
            @RequestBody String rawBody
    ) {
        HsbRefundCallbackParam param = JSON.parseObject(rawBody, HsbRefundCallbackParam.class);
        log.info("Received HSB refund callback: custRfndTrcno={}", param.getCustRfndTrcno());
        String response = callbackAppService.handleRefundCallback(param, rawBody);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @PostMapping(value = "/reconciliation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "接收建行惠市宝对账文件推送")
    public ResponseEntity<String> handleReconciliationCallback(
            @RequestParam("File_Smry_Inf") String fileSmryInf,
            @RequestParam("Sign_Inf") String signInf,
            @RequestPart MultipartFile file
    ) {
        log.info("Received HSB reconciliation file push: fileSmryInf={}, fileName={}, fileSize={}",
                fileSmryInf, file.getOriginalFilename(), file.getSize());
        String response = callbackAppService.handleReconciliationCallback(fileSmryInf, signInf, file);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

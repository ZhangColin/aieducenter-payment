package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.PaymentCallbackAppService;
import com.aieducenter.payment.application.dto.callback.IcbcCallbackParam;
import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 支付回调控制器
 *
 * <p>接收工行支付结果异步通知</p>
 * <p>此路径已在 WebConfig 中排除签名验证</p>
 */
@RestController
@RequestMapping("/api/v1/payment/callbacks")
@RequiredArgsConstructor
@Tag(name = "Payment Callback API", description = "支付回调接口")
public class PaymentCallbackController {

    private static final Logger log = LoggerFactory.getLogger(PaymentCallbackController.class);

    private final PaymentCallbackAppService paymentCallbackAppService;

    @PostMapping(value = "/icbc", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Operation(summary = "接收工行支付结果回调")
    public ResponseEntity<String> handleIcbcCallback(@RequestParam Map<String, String> params) {
        log.info("Received ICBC callback: params keys={}", params.keySet());

        String sign = params.get("sign");
        String bizContent = params.get("biz_content");

        if (sign == null || bizContent == null) {
            log.warn("ICBC callback missing required parameters");
            return ResponseEntity.badRequest().body("missing parameters");
        }

        // 解析 biz_content
        IcbcCallbackParam callbackParam = JSON.parseObject(bizContent, IcbcCallbackParam.class);

        // 处理回调
        String response = paymentCallbackAppService.handleCallback(
            "/api/v1/payment/callbacks/icbc",
            params,
            sign,
            callbackParam
        );

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
}

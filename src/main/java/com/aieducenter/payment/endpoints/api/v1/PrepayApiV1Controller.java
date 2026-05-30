package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.PrepayAppService;
import com.aieducenter.payment.application.dto.command.CreatePrepayCommand;
import com.aieducenter.payment.application.dto.response.PrepayOrderResponse;
import com.cartisan.core.context.RequestContext;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import com.cartisan.web.util.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Validated
@Tag(name = "Prepay API v1", description = "聚合支付预支付接口 v1")
public class PrepayApiV1Controller {

    private final PrepayAppService prepayAppService;

    @PostMapping("/prepay")
    @RequireSignature
    @Operation(summary = "创建预支付订单")
    public ApiResponse<PrepayOrderResponse> createPrepay(
            @Valid @RequestBody CreatePrepayCommand command,
            HttpServletRequest request
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        String clientIp = IpUtil.getClientIp(request);
        PrepayOrderResponse response = prepayAppService.createPrepay(command, businessSystemName, clientIp);
        return ApiResponse.ok(response);
    }
}

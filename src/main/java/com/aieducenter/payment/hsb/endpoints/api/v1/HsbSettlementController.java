package com.aieducenter.payment.hsb.endpoints.api.v1;

import com.aieducenter.payment.hsb.application.HsbSettlementAppService;
import com.aieducenter.payment.hsb.application.dto.command.ConfirmHsbSettlementCommand;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hsb/settlements")
@RequiredArgsConstructor
@Validated
@Tag(name = "HSB Settlement API v1", description = "惠市宝分账确认接口")
public class HsbSettlementController {

    private final HsbSettlementAppService settlementAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "确认惠市宝分账")
    public ApiResponse<Void> confirmSettlement(
            @Valid @RequestBody ConfirmHsbSettlementCommand command
    ) {
        settlementAppService.confirmSettlement(command);
        return ApiResponse.ok(null);
    }
}

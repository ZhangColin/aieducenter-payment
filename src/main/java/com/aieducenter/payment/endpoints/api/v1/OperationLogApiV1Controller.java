package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.OperationLogAppService;
import com.aieducenter.payment.application.dto.query.OperationLogQuery;
import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import com.cartisan.web.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/operation-logs")
@RequiredArgsConstructor
@Validated
@Tag(name = "Operation Log API v1", description = "操作日志接口 v1")
public class OperationLogApiV1Controller {

    private final OperationLogAppService operationLogAppService;

    @GetMapping
    @RequireSignature
    @Operation(summary = "分页查询操作日志")
    public ApiResponse<PageResponse<OperationLogResponse>> list(
            OperationLogQuery query,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ApiResponse.ok(operationLogAppService.list(query, pageable));
    }
}

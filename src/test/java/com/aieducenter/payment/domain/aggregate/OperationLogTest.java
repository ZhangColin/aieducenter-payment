package com.aieducenter.payment.domain.aggregate;

import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.cartisan.core.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * OperationLog 聚合根测试（领域缝）。
 *
 * <p>校验构造不变式；OperationLog 为追加只写，无状态迁移方法。</p>
 */
@DisplayName("OperationLog 聚合根测试")
class OperationLogTest {

    @Test
    @DisplayName("给定有效输入，创建操作日志时应该成功并保存全部字段")
    void given_validInput_when_createOperationLog_then_allFieldsStored() {
        OperationLog log = new OperationLog(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "张三", "admin-bff", "SUCCESS", "同意退款"
        );

        assertThat(log.getTargetType()).isEqualTo(OperationLogTargetType.REFUND);
        assertThat(log.getTargetNo()).isEqualTo("REF001");
        assertThat(log.getOperation()).isEqualTo(OperationType.AUDIT_APPROVE);
        assertThat(log.getOperatorId()).isEqualTo(123L);
        assertThat(log.getOperatorName()).isEqualTo("张三");
        assertThat(log.getOperatorSystem()).isEqualTo("admin-bff");
        assertThat(log.getResult()).isEqualTo("SUCCESS");
        assertThat(log.getRemark()).isEqualTo("同意退款");
    }

    @Test
    @DisplayName("给定系统发起的动作，操作者字段为空时也应该成功")
    void given_systemInitiated_when_operatorFieldsNull_then_success() {
        OperationLog log = new OperationLog(
            OperationLogTargetType.PAYMENT, "PAY001", OperationType.NOTIFY_RESEND,
            null, null, null, "SUCCESS", null
        );

        assertThat(log.getOperatorId()).isNull();
        assertThat(log.getOperatorName()).isNull();
        assertThat(log.getOperatorSystem()).isNull();
        assertThat(log.getRemark()).isNull();
    }

    @Test
    @DisplayName("给定目标类型为空，创建时应该抛出异常")
    void given_nullTargetType_when_create_then_throwsException() {
        assertThatThrownBy(() -> new OperationLog(
            null, "REF001", OperationType.AUDIT_APPROVE,
            1L, "张三", "sys", "SUCCESS", null
        ))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("操作日志目标类型不能为空");
    }

    @Test
    @DisplayName("给定目标单号为空白，创建时应该抛出异常")
    void given_blankTargetNo_when_create_then_throwsException() {
        assertThatThrownBy(() -> new OperationLog(
            OperationLogTargetType.REFUND, "  ", OperationType.AUDIT_APPROVE,
            1L, "张三", "sys", "SUCCESS", null
        ))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("操作日志目标单号不能为空");
    }

    @Test
    @DisplayName("给定操作类型为空，创建时应该抛出异常")
    void given_nullOperation_when_create_then_throwsException() {
        assertThatThrownBy(() -> new OperationLog(
            OperationLogTargetType.REFUND, "REF001", null,
            1L, "张三", "sys", "SUCCESS", null
        ))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("操作日志操作类型不能为空");
    }

    @Test
    @DisplayName("给定结果为空，创建时应该抛出异常")
    void given_blankResult_when_create_then_throwsException() {
        assertThatThrownBy(() -> new OperationLog(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            1L, "张三", "sys", "", null
        ))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("操作日志结果不能为空");
    }
}

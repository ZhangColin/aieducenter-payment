package com.aieducenter.payment.domain.aggregate;


import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.RefundStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * RefundOrder 聚合根测试
 *
 * <p>测试退款订单的业务逻辑和审核工作流</p>
 */
@DisplayName("RefundOrder 聚合根测试")
class RefundOrderTest {

    @Test
    @DisplayName("给定有效输入，创建退款订单时应该成功")
    void given_valid_input_when_createRefundOrder_then_success() {
        // When
        RefundOrder order = new RefundOrder(
            "ORDER001",
            "PAY001",
            "TestSystem",
            "课程购买",
            10000L,
            10000L,
            "课程取消",
            null,
            null
        );

        // Then
        assertThat(order.getBusinessOrderNo()).isEqualTo("ORDER001");
        assertThat(order.getPaymentOrderNo()).isEqualTo("PAY001");
        assertThat(order.getBusinessSystemName()).isEqualTo("TestSystem");
        assertThat(order.getBusinessName()).isEqualTo("课程购买");
        assertThat(order.getStatus()).isEqualTo(RefundStatus.PENDING);
        assertThat(order.getRefundAmount()).isEqualTo(10000L);
        assertThat(order.getRefundableAmount()).isEqualTo(10000L);
        assertThat(order.getReason()).isEqualTo("课程取消");
    }

    @Test
    @DisplayName("给定待审核退款，审核通过时应该更新状态为已批准")
    void given_pendingRefund_when_auditApproved_then_statusChangesToApproved() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );

        // When
        order.audit(123L, "张三", true, "同意退款");

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.APPROVED);
        assertThat(order.isApproved()).isTrue();
        assertThat(order.getAuditorId()).isEqualTo(123L);
        assertThat(order.getAuditorName()).isEqualTo("张三");
        assertThat(order.getAuditAgreed()).isTrue();
        assertThat(order.getAuditedAt()).isNotNull();
        assertThat(order.getApprovedAt()).isNotNull();
        assertThat(order.getAuditRemark()).isEqualTo("同意退款");
        assertThat(order.getAuditType()).isEqualTo(AuditType.MANUAL);
    }

    @Test
    @DisplayName("给定待审核退款，审核拒绝时应该更新状态为已拒绝")
    void given_pendingRefund_when_auditRejected_then_statusChangesToRejected() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );

        // When
        order.audit(123L, "张三", false, "不符合退款条件");

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.REJECTED);
        assertThat(order.isApproved()).isFalse();
        assertThat(order.getAuditAgreed()).isFalse();
        assertThat(order.getAuditedAt()).isNotNull();
        assertThat(order.getApprovedAt()).isNull(); // 拒绝时没有批准时间
        assertThat(order.getAuditType()).isEqualTo(AuditType.MANUAL);
    }

    @Test
    @DisplayName("给定待审核退款，免审自动通过时应该置 auditType=AUTO 且无审核者")
    void given_pendingRefund_when_autoAudit_then_auditTypeAutoAndNoAuditor() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );

        // When
        order.autoAudit();

        // Then
        assertThat(order.getAuditType()).isEqualTo(AuditType.AUTO);
        assertThat(order.getStatus()).isEqualTo(RefundStatus.APPROVED);
        assertThat(order.isApproved()).isTrue();
        assertThat(order.getAuditorId()).isNull();
        assertThat(order.getAuditorName()).isNull();
        assertThat(order.getAuditAgreed()).isTrue();
        assertThat(order.getAuditedAt()).isNotNull();
        assertThat(order.getApprovedAt()).isNotNull();
    }

    @Test
    @DisplayName("给定非待审核退款（已审核），免审自动通过时应该抛出异常")
    void given_auditedRefund_when_autoAuditAgain_then_throwsException() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );
        order.audit(123L, "张三", true, "同意");

        // When & Then
        assertThatThrownBy(() -> order.autoAudit())
            .isInstanceOf(com.cartisan.core.exception.DomainException.class)
            .hasMessageContaining("退款订单不是待审核状态");
    }

    @Test
    @DisplayName("给定已批准退款，开始退款时应该更新状态为退款中")
    void given_approvedRefund_when_startRefund_then_statusChangesToRefunding() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );
        order.audit(123L, "张三", true, "同意退款");

        // When
        order.startRefund("ICBC_REFUND_123");

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.REFUNDING);
    }

    @Test
    @DisplayName("给定待审核退款，开始退款时应该抛出异常")
    void given_pendingRefund_when_startRefund_then_throwsException() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );

        // When & Then
        assertThatThrownBy(() -> order.startRefund("ICBC_REFUND_123"))
            .isInstanceOf(com.cartisan.core.exception.DomainException.class)
            .hasMessageContaining("退款订单未批准");
    }

    @Test
    @DisplayName("给定退款中订单，标记退款成功时应该更新状态和时间戳")
    void given_refundingRefund_when_refundSuccess_then_statusChangesToSuccess() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );
        order.audit(123L, "张三", true, "同意退款");
        order.startRefund("ICBC_REFUND_123");

        // When
        order.refundSuccess("ICBC_REFUND_123");

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.SUCCESS);
        assertThat(order.getBankRefundNo()).isEqualTo("ICBC_REFUND_123");
        assertThat(order.getRefundedAt()).isNotNull();
    }

    @Test
    @DisplayName("给定退款中订单，标记退款失败时应该更新状态和时间戳")
    void given_refundingRefund_when_refundFailed_then_statusChangesToFailed() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );
        order.audit(123L, "张三", true, "同意退款");
        order.startRefund("ICBC_REFUND_123");

        // When
        order.refundFailed("银行账户异常");

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.FAILED);
        assertThat(order.getFailedAt()).isNotNull();
    }

    @Test
    @DisplayName("给定退款金额超过可退款金额，创建时应该抛出异常")
    void given_refundAmountExceedsRefundable_when_create_then_throwsException() {
        // When & Then
        assertThatThrownBy(() -> new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 15000L, 10000L, "Reason", null, null
        ))
            .isInstanceOf(com.cartisan.core.exception.DomainException.class)
            .hasMessageContaining("退款金额超过可退款金额");
    }

    @Test
    @DisplayName("给定待审核退款，二次审核时应该抛出异常")
    void given_auditedRefund_when_auditAgain_then_throwsException() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, null
        );
        order.audit(123L, "张三", true, "同意退款");

        // When & Then
        assertThatThrownBy(() -> order.audit(456L, "李四", false, "重新审核"))
            .isInstanceOf(com.cartisan.core.exception.DomainException.class)
            .hasMessageContaining("退款订单不是待审核状态");
    }

    @Test
    @DisplayName("给定已批准退款，开始退款时应该保存银行退款流水号")
    void given_approvedRefund_when_startRefund_then_savesBankRefundNo() {
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            "课程购买", 10000L, 10000L, "Reason", null, "https://biz.example.com/notify"
        );
        order.audit(123L, "张三", true, "同意退款");

        order.startRefund("ICBC_REFUND_456");

        assertThat(order.getStatus()).isEqualTo(RefundStatus.REFUNDING);
        assertThat(order.getBankRefundNo()).isEqualTo("ICBC_REFUND_456");
        assertThat(order.getNotifyUrl()).isEqualTo("https://biz.example.com/notify");
    }
}

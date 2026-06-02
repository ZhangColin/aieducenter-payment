package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.infrastructure.HsbConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class HsbReconciliationCallbackTest {

    private HsbConfig hsbConfig;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        hsbConfig = new HsbConfig();
        hsbConfig.setReconciliationStoragePath(tempDir.toString());
    }

    @Test
    @DisplayName("验签通过且文件保存成功，返回成功")
    void handleReconciliationCallback_success() {
        MultipartFile file = new MockMultipartFile(
            "file", "test.zip", "application/zip",
            "zip-content".getBytes()
        );

        HsbCallbackAppService appService = new TestableHsbCallbackAppService(hsbConfig, true);

        String result = appService.handleReconciliationCallback(
            "summary-info", "sign-value", file
        );

        assertThat(result).contains("\"Svc_Rsp_St\":\"00\"");
    }

    @Test
    @DisplayName("验签失败，返回失败")
    void handleReconciliationCallback_signatureVerificationFailed() {
        MultipartFile file = new MockMultipartFile(
            "file", "test.zip", "application/zip",
            "zip-content".getBytes()
        );

        HsbCallbackAppService appService = new TestableHsbCallbackAppService(hsbConfig, false);

        String result = appService.handleReconciliationCallback(
            "summary-info", "sign-value", file
        );

        assertThat(result).contains("\"Svc_Rsp_St\":\"01\"");
    }

    @Test
    @DisplayName("文件为空，返回失败")
    void handleReconciliationCallback_emptyFile() {
        MultipartFile file = new MockMultipartFile(
            "file", "test.zip", "application/zip", new byte[0]
        );

        HsbCallbackAppService appService = new TestableHsbCallbackAppService(hsbConfig, true);

        String result = appService.handleReconciliationCallback(
            "summary-info", "sign-value", file
        );

        assertThat(result).contains("\"Svc_Rsp_St\":\"01\"");
    }

    /**
     * Test subclass that overrides signature verification to control the outcome.
     */
    private static class TestableHsbCallbackAppService extends HsbCallbackAppService {

        private final boolean signatureValid;

        TestableHsbCallbackAppService(HsbConfig hsbConfig, boolean signatureValid) {
            super(null, null, null, hsbConfig, null);
            this.signatureValid = signatureValid;
        }

        @Override
        protected boolean verifyReconciliationSign(String platformPublicKey, String signStr, String signInf) {
            return signatureValid;
        }
    }
}

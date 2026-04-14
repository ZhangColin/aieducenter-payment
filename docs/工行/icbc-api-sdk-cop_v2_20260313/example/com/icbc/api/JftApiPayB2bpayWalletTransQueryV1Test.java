public void test() throws IcbcApiException {

        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String icbcPulicKey = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON,
                icbcPulicKey,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key,null,null);

        JftApiPayB2bpayWalletTransQueryRequestV1 request = new JftApiPayB2bpayWalletTransQueryRequestV1();
        request.setServiceUrl("http://[需替换]工行API网关地址/api/jft/api/b2bpay/wallettransquery/V1");
        JftApiPayB2bpayWalletTransQueryRequestV1.JftApiPayB2bpayWalletTransQueryRequestV1Biz bizContent = new JftApiPayB2bpayWalletTransQueryRequestV1.JftApiPayB2bpayWalletTransQueryRequestV1Biz();

        bizContent.setAppId("[需替换]app_id");
        bizContent.setOutUserId("HW071601");
        bizContent.setTrxDate("2022-01-01");
        bizContent.setCheckWorkDateFlag("1");
        bizContent.setInitFlag("1");
        bizContent.setRowReq("10");
        bizContent.setBegDate("2022-01-01");
        bizContent.setEndDate("2022-01-02");
        bizContent.setPageTurn("2022-01-01 11:59:59.000000");
        request.setBizContent(bizContent);
        JftApiPayB2bpayWalletTransQueryResponseV1 responseV1 = client.execute(request);
        System.out.println(responseV1);
}

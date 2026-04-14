package com.icbc.api;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.icbc.api.request.JftUiB2bpaySettleauditRequestV1;

public class JftUiB2bpaySettleauditRequestV1TestV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String APP_ID = "10000000000000000000";
            String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
            String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
            UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);
            ;
            JftUiB2bpaySettleauditRequestV1 request = new JftUiB2bpaySettleauditRequestV1();
            String host = "[需替换]工行API网关地址";
            request.setServiceUrl(host + "/ui/jft/ui/b2bpay/settleaudit/V1");
            JftUiB2bpaySettleauditRequestV1.JftUiB2bpaySettleauditV1Biz bizContent = new JftUiB2bpaySettleauditRequestV1.JftUiB2bpaySettleauditV1Biz();
            bizContent.setAppId("10000000000000000000");
            bizContent.setOutVendorId("123456");
            bizContent.setAuditSerialNo("123456");
            bizContent.setSceneType("01");
            JftUiB2bpaySettleauditRequestV1.TrxVo trx = new JftUiB2bpaySettleauditRequestV1.TrxVo();
            List trxList = new ArrayList();
            trx.setTrxId("trxId");
            trx.setAmount("amount");
            trx.setOutVendorId("outVendorId");
            trx.setPayAcctNum("payAcctNum");
            trx.setPayAcctName("payAcctName");
            trx.setTotalCount("2");
            trxList.add(trx);
            bizContent.setTrxList(trxList);
            List detailList = new ArrayList();
            JftUiB2bpaySettleauditRequestV1.DetailVo detailVo = new JftUiB2bpaySettleauditRequestV1.DetailVo();
            detailVo.setOutVendorId("123456");
            detailVo.setTotalAmount("totalAmount");
            detailVo.setTotalCount("totalCount");
            detailVo.setPayAcctNum("payAcctNum");
            detailVo.setPayAcctName("payAcctName");
            detailList.add(detailVo);
            bizContent.setDetailList(detailList);
            bizContent.setVerifiedCode("123456789");
            bizContent.setTradeTime("01");
            bizContent.setUkeyId("123456789");
            bizContent.setTrxIp("01");
            bizContent.setReturnUrl("123456789");
            bizContent.setSignKind("123456789");
            bizContent.setTotalAmount("01");
            bizContent.setTotalCount("123456789");
            bizContent.setBatchFlag("1");
            request.setBizContent(bizContent);
            String form = client.buildPostForm(request);
            System.out.println(form);

        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

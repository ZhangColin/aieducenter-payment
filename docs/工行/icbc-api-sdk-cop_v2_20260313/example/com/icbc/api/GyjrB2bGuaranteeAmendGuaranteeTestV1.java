package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bGuaranteeAmendGuaranteeRequestV1;
import com.icbc.api.response.GyjrB2bGuaranteeAmendGuaranteeResponseV1;

import java.util.Random;

public class GyjrB2bGuaranteeAmendGuaranteeTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000123456";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bGuaranteeAmendGuaranteeRequestV1 request = new GyjrB2bGuaranteeAmendGuaranteeRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/guarantee/amendguarantee/V1");
        GyjrB2bGuaranteeAmendGuaranteeRequestV1.BizContentV1Biz bizContent = new GyjrB2bGuaranteeAmendGuaranteeRequestV1.BizContentV1Biz();
        GyjrB2bGuaranteeAmendGuaranteeRequestV1.TransInfoV1Biz transInfo
                = new GyjrB2bGuaranteeAmendGuaranteeRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setfSeqNo("1111111");
        transInfo.setPlatVendorId("14");
        transInfo.setRefno("1111111");
        transInfo.setNewAmt("11111");
        transInfo.setExtmatDat("2023-12-12");
        transInfo.setOthModInfo("11111");
        transInfo.setImgser("1111");
        transInfo.setReferenceNo("11111");
        transInfo.setRequestDate("20231212121212");
        transInfo.setCallBackUrl("xxxxxxxx");
        transInfo.setMailflag("Y");
        transInfo.setMailaddr("1111");

        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        GyjrB2bGuaranteeAmendGuaranteeResponseV1 response;
        try {
            response = client.execute(request,new Random().nextInt(99999) + "msg");
            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
            } else {
                // 失败
                System.out.println(response.getReturnCode());
                System.out.println(response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

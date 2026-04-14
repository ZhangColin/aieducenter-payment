package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bGuaranteeApplyGuaranteeRequestV1;
import com.icbc.api.response.GyjrB2bGuaranteeApplyGuaranteeResponseV1;

import java.util.Random;

public class GyjrB2bGuaranteeApplyGuaranteeTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000123456";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bGuaranteeApplyGuaranteeRequestV1 request = new GyjrB2bGuaranteeApplyGuaranteeRequestV1();
        request.setServiceUrl("http://IP:PORT/api/gyjr/b2b/guarantee/applyguarantee/V1");
        GyjrB2bGuaranteeApplyGuaranteeRequestV1.BizContentV1Biz bizContent = new GyjrB2bGuaranteeApplyGuaranteeRequestV1.BizContentV1Biz();
        GyjrB2bGuaranteeApplyGuaranteeRequestV1.TransInfoV1Biz transInfo
                = new GyjrB2bGuaranteeApplyGuaranteeRequestV1.TransInfoV1Biz();
        transInfo.setProtocolCode("GYJR20220119160206728375706");
        transInfo.setPlatVendorId("14");
        transInfo.setfSeqNo("1111111");
        transInfo.setEnterpriseName("11111");
        transInfo.setEnterprisePhone("11111");
        transInfo.setEnterpriseContact("11111");
        transInfo.setEnterpriseEmail("11111");
        transInfo.setEnterpriseAddr("11111");
        transInfo.setSignflg("Y");
        transInfo.setContractNo("1111");
        transInfo.setContractDate("2022-02");
        transInfo.setContractSignPeriod("2");
        transInfo.setGuaranteeUsage("11111");
        transInfo.setGuaranteeContract("11111");
        transInfo.setGuaranteeDuty("11111");
        transInfo.setGuaranteeProj("11111");
        transInfo.setGuaranteeType("001");
        transInfo.setGuaranteeFileType("1");
        transInfo.setGuaranteeFileName("1111");
        transInfo.setGuaranteeCurtype("CNY");
        transInfo.setGuaranteeAmount("200.00");
        transInfo.setGuaranteeStartType("1");
        transInfo.setGuaranteeStartDate("2022-02-02");
        transInfo.setGuaranteeStartEvent("1111");
        transInfo.setGuaranteeEndType("1");
        transInfo.setGuaranteeEndDate("2022-02-02");
        transInfo.setGuaranteeEndEvent("1111");
        transInfo.setBeneficiaryName("1111");
        transInfo.setBeneficiaryAddr("1111");
        transInfo.setBeneficiaryPerson("1111");
        transInfo.setBeneficiaryContact("1111");
        transInfo.setImgser("1111");
        transInfo.setMailflag("Y");
        transInfo.setMailaddr("1111");
        transInfo.setAdviceflag("Y");
        transInfo.setBeneficiaryEmail("1111111");
        transInfo.setDefaultClause1("1111111");
        transInfo.setGuaranteedName("1111111");
        transInfo.setGuaranteedAddr("1111111");
        transInfo.setGuaranteedPerson("1111111");
        transInfo.setGuaranteedContact("1111111");
        transInfo.setGuaranteedUscc("1111111");
        transInfo.setAgrno("1111111");
        transInfo.setReferenceNo("1111111");
        transInfo.setRequestDate("20220202150000");
        transInfo.setRemark("1111111");
        transInfo.setCallBackUrl("xxxxxxxx");

        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        GyjrB2bGuaranteeApplyGuaranteeResponseV1 response;
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

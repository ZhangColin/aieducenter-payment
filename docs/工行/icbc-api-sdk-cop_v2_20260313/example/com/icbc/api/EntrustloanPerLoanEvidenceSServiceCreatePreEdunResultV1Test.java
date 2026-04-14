package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultRequestV1;
import com.icbc.api.response.EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultResponseV1;

public class EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultRequestV1 request = new EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultRequestV1();

        EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultRequestV1.EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultRequestV1Biz bizContent = new EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultRequestV1.EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultRequestV1Biz();

        bizContent.setSerialNo("12345678901234567890");
        bizContent.setAppNo("F-GCMS");
        bizContent.setAreaCode("0020000072");
        bizContent.setEmployeeCode("");
        bizContent.setLanguage("zh_CN");
        bizContent.setTransNo("PerLoanEvidenceSService.createPreEdunResult");
        bizContent.setVer("1.0");
        bizContent.setTurnPageFlag("");
        bizContent.setBeginRow("");
        bizContent.setRowCount("");
        bizContent.setDealArea("0020000072");
        bizContent.setOper("1");
        bizContent.setCino("020006202541727");
        bizContent.setLoanProtocolCode("02002000000001123702");
        bizContent.setCorpCis("020078900071312");
        bizContent.setCorpName("贷款催收公司");
        bizContent.setEdunEmpCode("888800135");
        bizContent.setEdunEmpName("理理");
        bizContent.setEdunPhoneRelation("15968185578");
        bizContent.setEdunType("7");
        bizContent.setEdunDate("20240909");
        bizContent.setEdunStatus("1");
        bizContent.setEdunResult("1");
        bizContent.setMoreInfo("承诺部分还款");
        bizContent.setLoanKind("1");
        bizContent.setCreateEmp("888800135");
        bizContent.setProdKindCode("030100000661");
        bizContent.setPromisDate("20240923");
        request.setBizContent(bizContent);

        try {
            EntrustloanPerLoanEvidenceSServiceCreatePreEdunResultResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

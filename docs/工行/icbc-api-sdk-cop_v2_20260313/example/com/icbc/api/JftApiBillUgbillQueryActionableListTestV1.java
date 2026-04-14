package com.icbc.api;

import com.icbc.api.request.JftApiBillUgbillQueryActionableListRequestV1;
import com.icbc.api.response.JftApiBillUgbillQueryActionableListResponseV1;

import java.util.ArrayList;
import java.util.List;

public class JftApiBillUgbillQueryActionableListTestV1 {

    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static final String APP_ID = "10000000000000005452";
    String host = "[需替换]工行API网关地址";
    String apiUrl= host+"/api/jft/api/bill/ugbill/actionable/qry/V1";
    String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

    @SuppressWarnings("unchecked")
    public void test_cop() throws Exception {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

        JftApiBillUgbillQueryActionableListRequestV1 request = new JftApiBillUgbillQueryActionableListRequestV1();
        request.setServiceUrl(apiUrl);

        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiBillUgbillQueryActionableListRequestV1.JftApiBillUgbillQueryActionableListRequestV1Biz bizContent = new JftApiBillUgbillQueryActionableListRequestV1.JftApiBillUgbillQueryActionableListRequestV1Biz();
        bizContent.setAppId(APP_ID);//合作方编号
        bizContent.setOutVendorId("999900001");//商户外部系统编号
        bizContent.setQueryType("01");
        bizContent.setCdTp("AC01");
        bizContent.setDueDateBgn("20221010");
        bizContent.setDueDateEnd("20221011");
        bizContent.setPackAmtBgn("100");
        bizContent.setPackAmtEnd("200");
        bizContent.setPageIndex("1");
        bizContent.setPageSize("10");
        bizContent.setHolderAcctId("62022021532153153121613");
        bizContent.setAccptrBank("102");
        bizContent.setEndorseTimes("2");
        List<String> noList = new ArrayList<>();
        noList.add("100000296020200931111255600000");
        bizContent.setPackNoList(noList);
        request.setBizContent(bizContent);

        JftApiBillUgbillQueryActionableListResponseV1 response;
        try {
            response = (JftApiBillUgbillQueryActionableListResponseV1) client.execute(request, System.currentTimeMillis()+"");
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("returnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("returnCode:"+response.getReturnCode());
                System.out.println("returnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new JftApiBillUgbillQueryActionableListTestV1().test_cop();
    }

}

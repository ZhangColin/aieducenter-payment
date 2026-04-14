package com.icbc.api;

import com.icbc.api.request.JftApiBillInstrinfoQryRequestV1;
import com.icbc.api.response.JftApiBillInstrinfoQryResponseV1;

import java.util.ArrayList;
import java.util.List;

public class JftApiBillInstrinfoQryTestV1 {
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static final String APP_ID = "10000000000000005452";
    String host = "[需替换]工行API网关地址";
    String apiUrl= host+"/api/jft/api/bill/instrinfo/qry/V1";
    String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

    @SuppressWarnings("unchecked")
    public void test_cop() throws Exception {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

        JftApiBillInstrinfoQryRequestV1 request = new JftApiBillInstrinfoQryRequestV1();
        request.setServiceUrl(apiUrl);

        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiBillInstrinfoQryRequestV1.JftApiBillInstrinfoQryRequestV1Biz bizContent = new JftApiBillInstrinfoQryRequestV1.JftApiBillInstrinfoQryRequestV1Biz();
        bizContent.setAppId(APP_ID);//合作方编号
        bizContent.setOutVendorId("999900001");//商户外部系统编号
        bizContent.setPageIndex("1");
        bizContent.setPageSize("10");
        bizContent.setBeginDate("2023116");
        bizContent.setEndDate("2023116");
        bizContent.setOperationType("apply");
        bizContent.setBusinessType("pledge");
        bizContent.setInstrState("111");
        bizContent.setCdTp("AC01");
        bizContent.setBatSerialNo("2023116");
        bizContent.setBusiSeq("202311601");
        bizContent.setAcctId("2023116");
        bizContent.setCdAmtBgn("2023116");
        bizContent.setCdAmtEnd("2023118");
        List<String> packList = new ArrayList<>();
        packList.add("1153321531");
        bizContent.setPackNoList(packList);
        request.setBizContent(bizContent);

        JftApiBillInstrinfoQryResponseV1 response;
        try {
            response = (JftApiBillInstrinfoQryResponseV1) client.execute(request, System.currentTimeMillis()+"");
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
        new JftApiBillInstrinfoQryTestV1().test_cop();
    }
}

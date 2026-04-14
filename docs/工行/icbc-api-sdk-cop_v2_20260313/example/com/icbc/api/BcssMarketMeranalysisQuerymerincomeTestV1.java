package com.icbc.api;

//import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MeranalysisQuerymerincomeRequestV1;
import com.icbc.api.response.MeranalysisQuerymerincomeResponseV1;

public class BcssMarketMeranalysisQuerymerincomeTestV1 {
    public final static String URL = "/api/bcss/meranalysis/querymerincome/V1";
    protected static final String MY_PRIVATE_KEY = "xxxxxxx";
    protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
    protected static final String APP_ID = "xxxx";
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = "xxxxxxx";
    private final static String SIGN_TYPE = "xxxxxxx";

    private final static String SM4_KEY= "xxxx";
    private final static String SM4_IV= "xxxx";

    public static void main(String[] args) {
    	try {
            //requestV1();//queryMode为001时
            requestV2();//queryMode为002时
//            requestV3();//queryMode为003时
    	} catch (Exception e) {
    		//打印错误信息
    	}
    }

/*    public static void requestV1() throws IcbcApiException {
    	MeranalysisQuerymerincomeRequestV1 request = new MeranalysisQuerymerincomeRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);

        MeranalysisQuerymerincomeRequestV1.BcssMarketMeranalysisQuerymerincomeRequestBizV1 bizContent = new MeranalysisQuerymerincomeRequestV1.BcssMarketMeranalysisQuerymerincomeRequestBizV1();
        bizContent.setCorpId("2000000031"); // 企业编号
        bizContent.setQueryMode("001");//查询模式：001-查询商户信息，002-查询经营收入，003-查询商户返款

        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MeranalysisQuerymerincomeResponseV1 response = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }

    public static void requestV3() throws IcbcApiException {
    	MeranalysisQuerymerincomeRequestV1 request = new MeranalysisQuerymerincomeRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);

        MeranalysisQuerymerincomeRequestV1.BcssMarketMeranalysisQuerymerincomeRequestBizV1 bizContent = new MeranalysisQuerymerincomeRequestV1.BcssMarketMeranalysisQuerymerincomeRequestBizV1();
        bizContent.setCorpId("2000000031"); // 企业编号
        bizContent.setQueryMode("003");//查询模式：001-查询商户信息，002-查询经营收入，003-查询商户返款
        bizContent.setQueryDate("2025-12-30");//yyyy-mm-dd，如果queryMode=002/003则必输

        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MeranalysisQuerymerincomeResponseV1 execute = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(execute));
    }*/

    public static void requestV2() throws Exception {
    	MeranalysisQuerymerincomeRequestV1 request = new MeranalysisQuerymerincomeRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);

        MeranalysisQuerymerincomeRequestV1.BcssMarketMeranalysisQuerymerincomeRequestBizV1 bizContent = new MeranalysisQuerymerincomeRequestV1.BcssMarketMeranalysisQuerymerincomeRequestBizV1();
        bizContent.setCorpId("2000000031"); // 企业编号
        bizContent.setQueryMode("002");//查询模式：001-查询商户信息，002-查询经营收入，003-查询商户返款
        bizContent.setQueryDate("2023-07-10");//yyyy-mm-dd，如果queryMode=002/003则必输
        bizContent.setIncomeSeqIds("0001");//经营收入id

        request.setBizContent(bizContent);
//        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MeranalysisQuerymerincomeResponseV1 response = client.execute(request);
//        System.out.println("解密前返回示例：" + JSONObject.toJSONString(response));
        //进行验签和解密
//        int returnCode = response.getReturnCode();
//        String resultText = response.getResultText();
//        String sign = response.getSign();
    }
}

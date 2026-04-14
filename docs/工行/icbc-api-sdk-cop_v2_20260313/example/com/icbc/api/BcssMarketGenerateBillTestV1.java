package com.icbc.api;

//import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GenerateBillRequestV1;
import com.icbc.api.response.GenerateBillResponseV1;

public class BcssMarketGenerateBillTestV1 {
    public final static String URL = "/api/bcss/generate/bill/V1";
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
    		requestV1();
    	} catch (Exception e) {
    		//打印错误信息
    	}
    }

    public static void requestV1() throws Exception {
    	GenerateBillRequestV1 request = new GenerateBillRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);

        GenerateBillRequestV1.BcssMarketGenerateBillRequestBizV1 bizContent = new GenerateBillRequestV1.BcssMarketGenerateBillRequestBizV1();
        bizContent.setCorpId("2000000031"); // 企业编号
        bizContent.setMchPayConfId("1");//缴费项编号
        bizContent.setOutBillNo("1");//三方账单号
        bizContent.setPayAmt("1");//账单金额,单位：分
        bizContent.setPhone("xxx");//手机号码
        
        request.setBizContent(bizContent);
//        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GenerateBillResponseV1 response = client.execute(request);
//        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }
}

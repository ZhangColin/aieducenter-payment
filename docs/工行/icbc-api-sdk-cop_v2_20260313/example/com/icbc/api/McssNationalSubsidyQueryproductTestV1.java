package com.icbc.api;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.McssNationalSubsidyQueryproductRequestV1;
import com.icbc.api.request.McssNationalSubsidyQueryproductRequestV1.McssNationalSubsidyQueryproductRequestBizV1;
import com.icbc.api.response.McssNationalSubsidyQueryproductResponseV1;
import java.util.logging.Logger;

public class McssNationalSubsidyQueryproductTestV1 {
		Logger log = Logger.getLogger(McssNationalSubsidyQueryproductTestV1.class.getName());

    protected static final String APIGW_PUBLIC_KEY = "xxxx";
    //重新生成
    protected static final String MY_PRIVATE_KEY = "xxxx";
    //APP_ID：工行API接口的ID
    protected static final String APP_ID = "xxxx";
    public static void main(String[] args) throws Exception{
        McssNationalSubsidyQueryproductTestV1 a=new McssNationalSubsidyQueryproductTestV1();
        a.test_cop();
    }
    public void test_cop() throws IcbcApiException {
        McssNationalSubsidyQueryproductRequestV1 request = new McssNationalSubsidyQueryproductRequestV1();
        request.setServiceUrl("http://ip:port/api/mcss/national/subsidy/queryproduct/V1");
        McssNationalSubsidyQueryproductRequestBizV1 bizContent = new McssNationalSubsidyQueryproductRequestBizV1();
        //商户编号
        bizContent.setMerNo("020004031233");
        //商品条形码
        bizContent.setProductBarCode("25Y04M09D09H10");
        //外部协议编号
        bizContent.setPrtiOutId("0200040312221");
        //应用编号
        bizContent.setAppId("11000000000000006143");

        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        McssNationalSubsidyQueryproductResponseV1 response = client.execute(request);
        log.info(JSONObject.toJSONString(response));
    }
}

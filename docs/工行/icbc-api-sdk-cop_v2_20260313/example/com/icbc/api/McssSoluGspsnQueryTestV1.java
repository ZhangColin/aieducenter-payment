package com.icbc.api.test;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.McssSoluGspsnQueryRequestV1;
import com.icbc.api.request.McssSoluGspsnQueryRequestV1.McssSoluGspsnQueryRequestBizV1;
import com.icbc.api.response.McssSoluGspsnQueryResponseV1;

public class McssSoluGspsnQueryTestV1 {
    protected static final String APIGW_PUBLIC_KEY = "xxxx";
    //重新生成
    protected static final String MY_PRIVATE_KEY = "xxxx";
    //APP_ID：工行API接口的ID
    protected static final String APP_ID = "xxxx";
    public static void main(String[] args) throws Exception{
        McssSoluGspsnQueryTestV1 a=new McssSoluGspsnQueryTestV1();
        a.test_cop();
    }
    public void test_cop() throws IcbcApiException {
        McssSoluGspsnQueryRequestV1 request = new McssSoluGspsnQueryRequestV1();
        request.setServiceUrl("http://ip:port/api/mcss/solu/gspsn/query/V1");
        McssSoluGspsnQueryRequestBizV1 bizContent = new McssSoluGspsnQueryRequestBizV1();
        //商户号
        bizContent.setMchntCd("020057018890");
        //SN码
        bizContent.setSnCode("XFDXBH82EE491131A4");
        //交易订单号
        bizContent.setOrderId("20251222020057018890003047671");
        //订单金额（以分为单位）
        bizContent.setOrderPrice("1800");
        //商品条码
        bizContent.setBarcode("06936520845898");
        //外部协议编号
        bizContent.setPrtiOutId("0200040312221");
        //应用编号
        bizContent.setAppId("11000000000000006143");

        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        McssSoluGspsnQueryResponseV1 response = client.execute(request);
        System.out.println(JSONObject.toJSONString(response));
    }
}

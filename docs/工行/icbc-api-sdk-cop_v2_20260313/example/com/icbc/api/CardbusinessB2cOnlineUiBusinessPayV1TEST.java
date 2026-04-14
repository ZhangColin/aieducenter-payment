package com.icbc.api;

import com.icbc.api.request.CardbusinessB2cOnlineUiBusinessPayRequestV1;
import com.icbc.api.response.CardbusinessB2cOnlineUiBusinessPayResponseV1;

import java.util.logging.Logger;

/**
 * @Author kfzx-dxm
 * @Date 2024/10/15
 */
public class CardbusinessB2cOnlineUiBusinessPayV1TEST {

    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "********";

    //2、appid
    protected static final String APP_ID = "******";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "************";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessB2cOnlineUiBusinessPayRequestV1 request = new CardbusinessB2cOnlineUiBusinessPayRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
        request.setServiceUrl("https://ip:port/ui/cardbusiness/b2c/online/ui/businessPay/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessB2cOnlineUiBusinessPayRequestV1.CardbusinessB2cOnlineUiBusinessPayRequestV1Biz bizContent = new CardbusinessB2cOnlineUiBusinessPayRequestV1.CardbusinessB2cOnlineUiBusinessPayRequestV1Biz();
        bizContent.setOrig_date_time("2025-05-01 11:24:31");
        bizContent.setMer_id("020006030400");
        bizContent.setAttach("附加数据");
        bizContent.setOut_trade_no("020006030400");
        bizContent.setMer_prtcl_no("0200060304000201");
        bizContent.setAmount("1000");
        bizContent.setCur_type("001");
        bizContent.setMer_url("http://www.test.com/notifyurl");
        bizContent.setNotify_type("HS");
        bizContent.setResult_type("0");
        bizContent.setAccess_type("6");
        bizContent.setExpire_time("3000");
        bizContent.setBiz_tp("100005");
        bizContent.setReturn_url("http://www.test.com/notifyurl");
        bizContent.setOrder_apd_inf("3000");
        bizContent.setBizFunc("111011");
        bizContent.setPyeeAcctId("xxxx");
        request.setBizContent(bizContent);
        CardbusinessB2cOnlineUiBusinessPayResponseV1 response;
        try {
            response = client.execute(request, "2323***4345345");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

            if (response.isSuccess()) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);

            } else {
                // 失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

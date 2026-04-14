package com.icbc.api;

import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankPayCpayCpdetailqueryRequestV1;
import com.icbc.api.request.MybankPayCpayCpdetailqueryRequestV1.MybankPayCpayCpdetailqueryRequestV1Biz;
import com.icbc.api.response.MybankPayCpayCpdetialqueryResponseV1;
import java.util.logging.Logger;
public class MybankPayCpayCpdetailqueryTestV1 {
	protected static final String MY_PRIVATE_KEY = "xxx";
	
	protected static final String APIGW_PUBLIC_KEY = "xxx";
	
	protected static final String APP_ID = "XXXX";
    
	static Logger log = Logger.getLogger(MybankPayCpayCpdetailqueryTestV1.class.getName());
    public static void main(String[] args) {

    	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        
		MybankPayCpayCpdetailqueryRequestV1 req = new MybankPayCpayCpdetailqueryRequestV1();

        req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cpdetailquery/V1");

        MybankPayCpayCpdetailqueryRequestV1Biz biz = new MybankPayCpayCpdetailqueryRequestV1Biz();
        
        biz.setQueryType("001");
        biz.setAgreeCode("0020000998060410006041000000891011");
        biz.setOrderNo("");
        biz.setBaginDate("20221112145343");
        biz.setEndDate("20221231215343");
        biz.setBegNum("1");
        biz.setFetchNum("2");
        biz.setStatus("");
        req.setBizContent(biz);
        MybankPayCpayCpdetialqueryResponseV1 response = new MybankPayCpayCpdetialqueryResponseV1();

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";

        try {
            response = client.execute(req, msgId);
            log.fine("response:" + response.toString());
            if (response.isSuccess()) {
              
            	// 业务成功处理
				System.out.println("success");//
				log.fine("success");
            } else {
                
            	// 失败
				System.out.println("error");
				log.fine("error");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        } finally {
        }
    }
	
}

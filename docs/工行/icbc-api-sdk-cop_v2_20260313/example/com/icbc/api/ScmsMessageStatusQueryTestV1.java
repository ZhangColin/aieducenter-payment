package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.request.ScmsMessageStatusQueryRequestV1;
import com.icbc.api.request.ScmsMessageStatusQueryRequestV1.ScmsGimsOuterappMsgsendRequestBizV1ChanCommV10;
import com.icbc.api.request.ScmsMessageStatusQueryRequestV1.ScmsGimsOuterappMsgsendRequestBizV1InfoCommV10;
import com.icbc.api.request.ScmsMessageStatusQueryRequestV1.ScmsGimsOuterappMsgsendRequestBizV1Srvprivate;
import com.icbc.api.request.ScmsMessageStatusQueryRequestV1.ScmsMessageStatusQueryRequestBizV1;

import com.icbc.api.response.ScmsMessageStatusQueryResponseV1;

public class ScmsMessageStatusQueryTestV1 {
protected static Logger log = Logger.getLogger(ScmsMessageStatusQueryTestV1.class.getName());
	
	//1、网关公钥	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	//2、appid	
	protected static final String APP_ID = "123";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥	
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		ScmsMessageStatusQueryRequestV1 request = new ScmsMessageStatusQueryRequestV1();
		request.setServiceUrl("http://ip:port/api/scms/message/status/query/V1");
        //request.setServiceUrl("http://IP:PORT/api/scms/message/status/query/V1");
        ScmsMessageStatusQueryRequestBizV1 bizContent = new ScmsMessageStatusQueryRequestBizV1();
        ScmsGimsOuterappMsgsendRequestBizV1ChanCommV10 bizContentCom =
                new ScmsGimsOuterappMsgsendRequestBizV1ChanCommV10();
        ScmsGimsOuterappMsgsendRequestBizV1InfoCommV10 bizContentInfo =
                new ScmsGimsOuterappMsgsendRequestBizV1InfoCommV10();
        ScmsGimsOuterappMsgsendRequestBizV1Srvprivate bizContentPri =
                new ScmsGimsOuterappMsgsendRequestBizV1Srvprivate();
        
        // 请对照接口文档用bizContentPri.setxxx()方法对业务上送数据进行赋值
        bizContentCom.setOapp("F-SCMS");
        bizContentCom.setChantype("702");
        
        bizContentInfo.setZoneno("0200");
        
        bizContentPri.setMsgid("310000001292");
        
        bizContent.setChanCommV10(bizContentCom);
        bizContent.setInfoCommV10(bizContentInfo);
        bizContent.setSrvprivate(bizContentPri);
        
        request.setBizContent(bizContent);
        ScmsMessageStatusQueryResponseV1 response;
		try {
			response = client.execute(request);
			if (response.isSuccess()) {
	            // 业务成功处理
	            System.out.println("success");
	        } else {
	            // 失败
	            System.out.println("error");
	        }
		} catch (IcbcApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

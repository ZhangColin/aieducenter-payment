package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.request.ScmsGimsOuterappMsgsendRequestV1;
import com.icbc.api.request.ScmsGimsOuterappMsgsendRequestV1.ScmsGimsOuterappMsgsendRequestBizV1;
import com.icbc.api.request.ScmsGimsOuterappMsgsendRequestV1.ScmsGimsOuterappMsgsendRequestBizV1.ScmsGimsOuterappMsgsendRequestBizV1Private;
import com.icbc.api.response.ScmsGimsOuterappMsgsendResponseV1;

public class ScmsGimsOuterappMsgsendTestV1 {
protected static Logger log = Logger.getLogger(ScmsGimsOuterappMsgsendTestV1.class.getName());
	
	//1、网关公钥	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	//2、appid	
	protected static final String APP_ID = "123";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥	
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		ScmsGimsOuterappMsgsendRequestV1 request = new ScmsGimsOuterappMsgsendRequestV1();
        request.setServiceUrl("http://IP:PORT/api/scms/gims/outerapp/msgsend/V1");
        ScmsGimsOuterappMsgsendRequestBizV1 bizContent = new ScmsGimsOuterappMsgsendRequestBizV1();
        ScmsGimsOuterappMsgsendRequestBizV1Private bizContentPri =
                new ScmsGimsOuterappMsgsendRequestBizV1Private();
        
        // 请对照接口文档用bizContentPri.setxxx()方法对业务上送数据进行赋值
        bizContentPri.setTrancode("SCMS_HTTP_GIMS_ICBCMO_001");
        bizContentPri.setStatus("2019-01-01");
        bizContentPri.setErrcode("10:18:00");
        bizContentPri.setTrandate("");
        bizContentPri.setTrantime("");
        bizContentPri.setVersion("");
        bizContentPri.setBrno("");
        bizContentPri.setTeller("");
        bizContentPri.setMsgtype("");
        bizContentPri.setItemid("");
        bizContentPri.setLanguage("");
        bizContentPri.setAppdata("");
        bizContentPri.setRefnum("");
        bizContentPri.setOrefnum("");
        bizContentPri.setRuniseqno("");
        bizContentPri.setSendbnk("");
        bizContentPri.setRecvbnk("");
        bizContentPri.setFld11rs("");
        bizContentPri.setFld11mtno("");
        bizContentPri.setFld11mtdt("");
        bizContentPri.setFld11sessno("");
        bizContentPri.setFld11seq("");
        bizContentPri.setFld12("");
        bizContentPri.setFld77("");
        bizContentPri.setFld79("");
        bizContentPri.setOmsgtxt("");
        bizContentPri.setAttachflds("");
        bizContentPri.setBanknotes("");
        bizContentPri.setBak("");
        
        bizContent.setPivate(bizContentPri);
        
        request.setBizContent(bizContent);
        ScmsGimsOuterappMsgsendResponseV1 response;
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

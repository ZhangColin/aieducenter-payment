package com.icbc.api;

import java.util.Random;
import java.util.logging.Logger;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.WriteoffTriggerTaskRequestV1;
import com.icbc.api.response.WriteoffTriggerTaskResponseV1;

public class WriteoffTriggerTaskTestV1 {

	// 签名私钥
	protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	// 网关公钥
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	// 合作方登记在门户或内管中的app编号
	protected static final String APP_ID = "11100000000000002369";
	static Logger log = Logger.getLogger(WriteoffTriggerTaskTestV1.class.getName());

	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "SM2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		WriteoffTriggerTaskRequestV1 req = new WriteoffTriggerTaskRequestV1();
		req.setServiceUrl("xxx");
		WriteoffTriggerTaskRequestV1.WriteoffTriggerTaskRequestV1Biz bizContent = new WriteoffTriggerTaskRequestV1.WriteoffTriggerTaskRequestV1Biz();
		bizContent.setCino("xxx");
		bizContent.setUserId("xxx");
		bizContent.setMainAreaCode("xxx");
		bizContent.setCurrentAreaCode("xxx");
		bizContent.setMobilePhone("xxx");
		bizContent.setCustName("xxx");
		bizContent.setCustCertType("xxx");
		bizContent.setCustCertNum("xxx");
		bizContent.setOperationId("xxx");
		bizContent.setParamList("xxx");
		req.setBizContent(bizContent);

		WriteoffTriggerTaskResponseV1 response;

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";

		try {
			response = client.execute(req, msgId);
			log.fine("JSONObject.toJSONString(response):" + JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				log.fine("response.getStatus():" + response.getReturnCode());
			} else {
				// 失败
				log.fine("response.getReturnCode():" + response.getReturnCode());
				log.fine("response.getReturnMsg():" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			log.info("IcbcApiException:" + e.getMessage());
		}

	}

}

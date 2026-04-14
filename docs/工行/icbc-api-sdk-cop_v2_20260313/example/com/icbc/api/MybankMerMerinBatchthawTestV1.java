package com.icbc.api;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankMerMerinBatchthawRequestV1;
import com.icbc.api.request.MybankMerMerinBatchthawRequestV1.MybankMerMerinBatchthawRequestV1Biz;
import com.icbc.api.request.MybankMerMerinBatchthawRequestV1.Order;
import com.icbc.api.response.MybankMerMerinBatchthawResponseV1;

public class MybankMerMerinBatchthawTestV1 {
	// 1銆佺綉鍏冲叕閽�
	protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqG*****";
	// 2銆乤ppid
	protected static final String APP_ID = "10000000000***";

	// 3銆佸瘑閽ュ璁よ瘉鏂瑰紡锛屽叕閽ュ湪API骞冲彴鐧昏锛屾澶勪负绉侀挜
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG****";

	public static void main(String[] args) throws Exception {

		// 绛惧悕绫诲瀷涓篟SA2鏃讹紝闇�浼犲叆appid锛岀閽ュ拰缃戝叧鍏挜锛岀鍚嶇被鍨嬩娇鐢ㄥ畾鍊糏cbcConstants.SIGN_TYPE_RSA2锛屽叾浠栧弬鏁颁娇鐢ㄧ己鐪佸��
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

		MybankMerMerinBatchthawRequestV1 request = new MybankMerMerinBatchthawRequestV1();
    
		// 4銆佹牴鎹祴璇曠幆澧冨拰鐢熶骇鐜鏇挎崲鐩稿簲ip鍜岀鍙�
		request.setServiceUrl("http://****:***/api/mybank/mer/merin/batchthaw/V1");
		// 5銆佽瀵圭収鎺ュ彛鏂囨。鐢╞izContent.setxxx()鏂规硶瀵逛笟鍔′笂閫佹暟鎹繘琛岃祴鍊�
		MybankMerMerinBatchthawRequestV1Biz bizContent = new MybankMerMerinBatchthawRequestV1Biz();
		JSONObject input1 = JSON.parseObject("*****");
		JSONArray inputArray = new JSONArray();
		inputArray.add(input1);
		bizContent.setOrderList(inputArray);
		bizContent.setAllSuccessFlag("****");
		bizContent.setOutAgreeNo("****");
		request.setBizContent(bizContent);
		MybankMerMerinBatchthawResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");// msgId娑堟伅閫氳鍞竴缂栧彿锛岃姹傛瘡娆¤皟鐢ㄧ嫭绔嬬敓鎴愶紝APP绾у敮涓�
	

			if (response.isSuccess()) {
				

			} else {
		

			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
}

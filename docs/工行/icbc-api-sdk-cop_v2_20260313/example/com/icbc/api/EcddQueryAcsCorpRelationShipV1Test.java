package com.icbc.api;

import com.icbc.api.request.EcddQueryAcsCorpRelationShipRequestV1;
import com.icbc.api.request.EcddQueryAcsCorpRelationShipRequestV1.QueryAcsCorpRelationShipRequestV1Biz;
import com.icbc.api.response.EcddPublicEntity;
import com.icbc.api.response.EcddQueryAcsCorpRelationShipResponseV1;

public class EcddQueryAcsCorpRelationShipV1Test {

	public static void main(String[] args) {
		try {
			String APP_ID = "[需替换]工行API APPID，请向工行联系获取";
			String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
			String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
			String URL = "http://[需替换]地址/api/mybank/loan/corp/repaytablequery/V1";

			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY,
					APIGW_PUBLIC_KEY);

			QueryAcsCorpRelationShipRequestV1Biz bizContent = new QueryAcsCorpRelationShipRequestV1Biz();

			EcddPublicEntity publicInput = new EcddPublicEntity();
			publicInput.setSerialNo("");
			publicInput.setOriginalSerialNo("");
			publicInput.setChannelType("1");
			publicInput.setMacAddress("");
			publicInput.setIpAddress("");
			publicInput.setChannelDate("");
			publicInput.setChannelTime("");
			publicInput.setStartApp("F-ECDD");
			publicInput.setStartProdCode("");
			publicInput.setServFace("1");
			publicInput.setZoneNo("");
			publicInput.setBrNo("");
			publicInput.setUserId("");
			publicInput.setTrxCode("");
			publicInput.setTrxDate("");
			publicInput.setTrxTimestamp("");
			bizContent.setPublicEntity(publicInput);

			EcddQueryAcsCorpRelationShipRequestV1.PrivateInput privateInput = new EcddQueryAcsCorpRelationShipRequestV1.PrivateInput();
			privateInput.setCreditCode("");
			privateInput.setPar_to_par_rel_rol("");
			privateInput.setBusiType("03");
			privateInput.setSubType("0301");
			privateInput.setDueName("0301001");
			privateInput.setDueSubName("0");
			privateInput.setAppNo("");
			privateInput.setUseType("01");

			// 对应参数自行添加
			bizContent.setPrivateEntity(privateInput);

			EcddQueryAcsCorpRelationShipRequestV1 request = new EcddQueryAcsCorpRelationShipRequestV1();
			request.setServiceUrl(URL);
			request.setBizContent(bizContent);
			EcddQueryAcsCorpRelationShipResponseV1 response = client.execute(request);
			  
            if (response.isSuccess()) {
            	//业务处理
                
            } else {
            	//业务处理
            }

        } catch (Exception e) {
        	//异常处理
        }
	}
}

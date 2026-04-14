package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiBillAdmittanceDatauploadRequestV1;
import com.icbc.api.response.JftApiBillAdmittanceDatauploadResponseV1;

public class JftApiBillAdmittanceDatauploadTestV1 {
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	protected static final String APP_ID = "10000000000000005452";
	String host = "[需替换]工行API网关地址";
	String apiUrl= host+"/api/jft/api/bill/admittance/dataupload/V1";
	String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

	public void test_cop() throws Exception {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

		JftApiBillAdmittanceDatauploadRequestV1 request = new JftApiBillAdmittanceDatauploadRequestV1();
		request.setServiceUrl(apiUrl);

		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiBillAdmittanceDatauploadRequestV1.JftApiBillAdmittanceDatauploadRequestV1Biz bizContent = new JftApiBillAdmittanceDatauploadRequestV1.JftApiBillAdmittanceDatauploadRequestV1Biz();
		bizContent.setAppId(APP_ID);//合作方编号
		bizContent.setSerialNo("QBIQUOTA0046761216");//请求序列号
		bizContent.setCustName("1");
		bizContent.setSocialCode("102100099999");
		bizContent.setAddress("AC01");
		bizContent.setContact("ME02");
		bizContent.setContactMobile("18209709065");
		bizContent.setImageBusinessNo("11119990912354171128095217000A07");
		bizContent.setLcncumEndDate("20230912");
		bizContent.setIdType("CT0001");
		bizContent.setIdNumber("543222199809178965");
		bizContent.setNotifyUrl("https://test.com");
		bizContent.setImageLegalNo("11119990912354171128095217000A07");
		bizContent.setImageAssetNo("11119990912354171128095217000A07");
		bizContent.setImageProfitsNo("11119990912354171128095217000A07");
		bizContent.setImageOtherNo("11119990912354171128095217000A07");
		bizContent.setImageAccreditNo("11119990912354171128095217000A07");
		bizContent.setImageServiceNo("11119990912354171128095217000A07");
		bizContent.setBranchHvpsBcode("102124703714");
		bizContent.setInstitution("0450000062");
		request.setBizContent(bizContent);

		JftApiBillAdmittanceDatauploadResponseV1 response;
		try {
			response = (JftApiBillAdmittanceDatauploadResponseV1) client.execute(request, System.currentTimeMillis()+"");
			if (response.isSuccess()) {
				//业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new JftApiBillAdmittanceDatauploadTestV1().test_cop();
	}
}

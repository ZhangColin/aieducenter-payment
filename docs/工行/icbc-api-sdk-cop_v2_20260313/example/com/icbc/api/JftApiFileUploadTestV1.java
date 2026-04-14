package com.icbc.api;

import com.icbc.api.request.JftApiFileUploadRequestV1;
import com.icbc.api.request.JftApiFileUploadRequestV1.JftApiFileUploadRequestV1Biz;
import com.icbc.api.response.JftApiFileUploadResponseV1;

/**
 * @desc
 *      聚富通文件上传
 */
public class JftApiFileUploadTestV1 {

	private static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	private static String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
	private static String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	private static String HOST = "[需替换]工行API网关地址";
	private static String API_URL = HOST + "/api/jft/api/file/upload/V1";
	private static String ENCRYPT_KEY = "[需替换]应用方加密串，与工行API开放平台一致";
	public static void main(String[] args) {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",ENCRYPT_KEY, "", "");
		JftApiFileUploadRequestV1 request = new JftApiFileUploadRequestV1();
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl(API_URL);

		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiFileUploadRequestV1Biz bizContent = new JftApiFileUploadRequestV1Biz();
		bizContent.setAppId(APP_ID);//合作方机构编号
		bizContent.setChanSerialNo("123456789012345678901234567");//渠道请求序列号
		bizContent.setOutVendorId("999900001");//子商户外部系统编号
		bizContent.setFile("/92j12/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYE...");//上传文件内容
		bizContent.setBusiType("1");//文件业务类型
		bizContent.setContentType("xls");//文件格式

		request.setBizContent(bizContent);
		
		JftApiFileUploadResponseV1 response;
		try {
			response = (JftApiFileUploadResponseV1) client.execute(request, System.currentTimeMillis()+"");
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

}


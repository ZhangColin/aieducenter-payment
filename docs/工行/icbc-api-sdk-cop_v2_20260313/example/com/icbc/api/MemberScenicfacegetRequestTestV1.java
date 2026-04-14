package com.icbc.api;

import com.icbc.api.request.CardbusinessMerMemberScenicfacegetRequestV1;
import com.icbc.api.request.CardbusinessMerMemberScenicfacegetRequestV1.MemberScenicfacegetRequestV1V1Biz;
import com.icbc.api.response.CardbusinessMerMemberScenicfacegetResponseV1;

import java.util.logging.Logger;


public class MemberScenicfacegetRequestTestV1 {

	private static Logger logger = Logger.getLogger(MedialessProtocolFaceQueryTestV1.class.getName());
	public static void main(String[] args) {
		// 企业交易秘钥,请根据测试环境和生产环境替换成相应的信息
		String corpPayKeyAppId = "appid";
		String corpPayKeyRsaPriKey = "keyinfo";
		
		// API gateway 公钥
		String apigwPublicKey = "public key";
		
		// 构造请求
		MemberScenicfacegetRequestV1V1Biz biz = new MemberScenicfacegetRequestV1V1Biz();
		biz.setCorpId("0000000272");
		biz.settotalNum("10");
		// 请求IP地址，请根据测试环境和生产环境替换相应ip和端口
		String serviceUrl = "https://ip:port/api/mybank/mer/member/scenicfaceget/V1";
		CardbusinessMerMemberScenicfacegetRequestV1 requestV1 = new CardbusinessMerMemberScenicfacegetRequestV1();
		requestV1.setBizContent(biz);
		requestV1.setServiceUrl(serviceUrl);

		DefaultIcbcClient client = new DefaultIcbcClient(corpPayKeyAppId, "RSA2", corpPayKeyRsaPriKey, apigwPublicKey);
		try {
		      CardbusinessMerMemberScenicfacegetResponseV1 responseV1 = client.execute(requestV1);
		      if (responseV1.isSuccess()) {
				  logger.info("处理成功" + responseV1.toString());
		      } else {
				  logger.info("处理失败，错误信息：" + responseV1.getReturnMsg());
		      }
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}
}

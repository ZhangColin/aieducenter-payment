package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.QualQueryRequestV3;
import com.icbc.api.request.QualQueryRequestV3.QualQueryRequestV3Biz;
import com.icbc.api.response.QualQueryResponseV3;

public class QualQueryTestV3 {

	private  static final Logger logger = LoggerFactory.getLogger(QualQueryTestV3.class);
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

	//2、appid
	protected static final String APP_ID = "[需替换]工行APP编号";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
		
	public static void main(String[] args) throws Exception {

		//签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		QualQueryRequestV3 request = new QualQueryRequestV3();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/qual/query/V3");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		QualQueryRequestV3Biz bizContent = new QualQueryRequestV3Biz();
		bizContent.setCampId("EBMPR_0101_0000_2017_000181|EBMPR_0101_0000_2017_000162|8937463|8937464|8937465|p7|p8|p9");
		bizContent.setCiNo("f81d49b50caa5696bfe2b841eea85343");
		bizContent.setStartTime("2019-01-01");
		bizContent.setEndTime("2019-01-02");
		bizContent.setThirdId("SF00200000644");
		
		request.setBizContent(bizContent);

		QualQueryResponseV3 response = new QualQueryResponseV3();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String msgid = dateFormat.format(new Date());
			response = client.execute(request, msgid);
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			logger.error("查询异常", e);
		}
	}

}

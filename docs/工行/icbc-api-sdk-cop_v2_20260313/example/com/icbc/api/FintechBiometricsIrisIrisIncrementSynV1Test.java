package com.icbc.api;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.icbc.api.request.FintechBiometricsIrisIrisIncrementSynRequestV1;
import com.icbc.api.request.FintechBiometricsIrisIrisIncrementSynRequestV1.FintechBiometricsIrisIrisIncrementSynRequestV1Biz;
import com.icbc.api.request.FintechBiometricsIrisIrisIncrementSynRequestV1.Private;
import com.icbc.api.response.FintechBiometricsIrisIrisIncrementSynResponseV1;
import com.icbc.api.response.FintechBiometricsIrisIrisIncrementSynResponseV1.Private.FintechBiometricsIrisIrisIncrementSynResponseV1List;

/**
 * 虹膜数据增量同步
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备泄1�76提示进行数据替换
 */
public class FintechBiometricsIrisIrisIncrementSynV1Test {

	//	调用方自己的AppId
	private static final String APP_ID = new String("xxxxxx");
	//	调用方私钄1�7
	protected static final String MY_PRIVATE_KEY = new String("xxxxxx");
	//	API网关公钥
	protected static final String APIGW_PUBLIC_KEY = new String("xxxxxx");
	//	API请求地址
	private static final String API_SERVICE_URL = new String("http://ip:port/api");
	
	 // 柄1�719幄1�7朄1�7旄1�7 13:14:01 刄1�7 2019幄1�7朄1�7旄1�7 15:14:01 公交虹膜支付业务组中发生新增、修改�1�7�删除的客户信息
	public void testQueryByParPronoAndTranSeqno() throws Exception {

		// 签名类型为RSA2时，霄1�7传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省�1�7�1�7
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);
		FintechBiometricsIrisIrisIncrementSynRequestV1 request = new FintechBiometricsIrisIrisIncrementSynRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端叄1�7
		request.setServiceUrl(API_SERVICE_URL + "/fintech/biometrics/iris/irisincrementsyn/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上逄1�7
		FintechBiometricsIrisIrisIncrementSynRequestV1Biz bizContent = new FintechBiometricsIrisIrisIncrementSynRequestV1Biz();
		Private pfield = new Private();

		pfield.setFeatureType(2);
		pfield.setStartTime("2019-08-12 10:34");
		pfield.setEndTime("2019-08-12 12:34");
		pfield.setPg_flag(1);
		pfield.setPg_maxnum(10);
		pfield.setPg_mob_addr_id("1234567890");
        	pfield.setWorkDate("2019-07-12");
        	pfield.setWorkTime("16:12");
        	pfield.setOrgzType(2);
        	pfield.setOapp("F-GCMC");
        	pfield.setBusKind("");
        	pfield.setUsage(1);
        	pfield.setBasType(1);
        	bizContent.setPrivate(pfield);
		request.setBizContent(bizContent);

		FintechBiometricsIrisIrisIncrementSynResponseV1 response = new FintechBiometricsIrisIrisIncrementSynResponseV1();
		try {
			response = client.execute(request, UUID.randomUUID().toString().replaceAll("-", ""));// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯丄1�7
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数捄1�7
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
				/*System.out.println("IrisInfo:" + response.getIrisInfo());
				System.out.println("CustId:" + response);
				System.out.println("TotalNum:" + response.getTotalNum());
				System.out.println("NpFlag:" + response.getNpFlag());
				System.out.println("Pg_mob_addr_id:" + response.getPg_mob_addr_id());*/
				//System.out.println("response.getItems: " + response.getItems());

				com.icbc.api.response.FintechBiometricsIrisIrisIncrementSynResponseV1.Private p = response.getPrivate();

				System.out.println("response.getItems: " + p.getItems());
				if (!"0".equals(p.getItems())) {

					// 查询成功 获取返回结果
					List<FintechBiometricsIrisIrisIncrementSynResponseV1List> list = p.getList();
					// 获取结果集中字段
					for (FintechBiometricsIrisIrisIncrementSynResponseV1List detail : list) {
						// 获取信息.....

						System.out.println("============================= begin =============================");

						System.out.println("IrisInfo:" + detail.getIrisInfo());
						System.out.println("CustId:" + detail.getCustId());
						System.out.println("TotalNum:" + detail.getTotalNum());
						System.out.println("NpFlag:" + detail.getNpFlag());
						System.out.println("Pg_mob_addr_id:" + detail.getPg_mob_addr_id());						
						
						System.out.println("=============================  end  =============================");

					}
				}
			} else {
				System.out.println("returnCode:" + response.getReturnCode());
				System.out.println("returnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
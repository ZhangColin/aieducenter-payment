package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bFinanceComdrftQryissuancebilllistRequestTestV1;
import com.icbc.api.response.GyjrB2bFinanceComdrftQryissuancebilllistResponseV1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bFinanceComdrftQryissuancebilllistRequestTestV1 {

	protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXX";
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXX";

	protected static final String APP_ID = "11000000000000001051";

	static Logger log = Logger.getLogger(GyjrB2bFinanceComdrftQryissuancebilllistRequestTestV1.class.getName());
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		GyjrB2bFinanceComdrftQryissuancebilllistRequestV1 req = new GyjrB2bFinanceComdrftQryissuancebilllistRequestV1();

		req.setServiceUrl("http://ip:port/api/gyjr/b2b/finance/comdrft/qryissuancebilllist/V1");

		GyjrB2bFinanceComdrftQryissuancebilllistRequestV1.GyjrB2bFinanceComdrftQryissuancebilllistRequestV1Biz bizContent = new GyjrB2bFinanceComdrftQryissuancebilllistRequestV1.GyjrB2bFinanceComdrftQryissuancebilllistRequestV1Biz();

		GyjrB2bFinanceComdrftQryissuancebilllistRequestV1.TransinfoBiz transInfo = new GyjrB2bFinanceComdrftQryissuancebilllistRequestV1.TransinfoBiz();

		transInfo.setProtocolCode("GYJR20260701105640610539510");
		transInfo.setPlatVendorId("3030");
		transInfo.setPageIndex("1");
		transInfo.setPageSize("1");
		List accList =new ArrayList();
		accList.add("0200000609027330386");
		transInfo.setAccountNumList(accList);
		transInfo.setCdType("AC00");
		transInfo.setBillNo("0");
		transInfo.setBillAmtFrom("1");
		transInfo.setBillAmtTo("1000000000");
		transInfo.setBillDeadDateFrom("20220301");
		transInfo.setBillDeadDateTo("20230301");
		transInfo.setUkeyId("dianli001.c.0200");


		bizContent.setTransInfoBiz(transInfo);
		req.setBizContent(bizContent);

		GyjrB2bFinanceComdrftQryissuancebilllistResponseV1 response;

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		try {
			response = client.execute(req,msgId);
			log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				log.fine("response.getReturnCode():"+response.getReturnCode());
			} else {
				//失败
				log.fine("response.getReturnCode():"+response.getReturnCode());
				log.fine("response.getReturnMsg():"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQryacceptanceRequestV1;
import com.icbc.api.response.GyjrB2bBillQryacceptanceResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bBillQryacceptanceRequestTestV1 {

	protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";


	protected static final String APP_ID = "11000000000000001051";

	static Logger log = Logger.getLogger(GyjrB2bBillQryacceptanceRequestTestV1.class.getName());
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		GyjrB2bBillQryacceptanceRequestV1 req = new GyjrB2bBillQryacceptanceRequestV1();

		req.setServiceUrl("http://ip:port/api/gyjr/b2b/bill/qryacceptance/V1");

		GyjrB2bBillQryacceptanceRequestV1.GyjrB2bBillQryacceptanceRequestV1Biz bizContent = new GyjrB2bBillQryacceptanceRequestV1.GyjrB2bBillQryacceptanceRequestV1Biz();

		GyjrB2bBillQryacceptanceRequestV1.TransinfoBiz transInfo = new GyjrB2bBillQryacceptanceRequestV1.TransinfoBiz();

		transInfo.setProtocolCode("GYJR20260701105640610539404");
		transInfo.setPlatVendorid("29");
		transInfo.setPageIndex("1");
		transInfo.setPageSize("100");
		List accIds =new ArrayList();
		accIds.add("0200000609027330386");
		transInfo.setDrwrAcctIds(accIds);
		transInfo.setCdTp("0");
		transInfo.setPackAmtBgn("0");
		transInfo.setPackAmtEnd("100000000000");
		transInfo.setDueDateBgn("20260920");
		transInfo.setDueDateEnd("20270920");
		List<String> packNoList = new ArrayList<>();
		transInfo.setPackNoList(packNoList);
		transInfo.setUkeyId("111");


		bizContent.setTransInfoBiz(transInfo);
		req.setBizContent(bizContent);

		GyjrB2bBillQryacceptanceResponseV1 response;

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

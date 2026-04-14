package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillQuerypledgebillRequestV1;
import com.icbc.api.response.GyjrB2bBillQuerypledgebillResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bBillQuerypledgebillRequestTestV1 {


	protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

	protected static final String APP_ID = "11000000000000001051";

	static Logger log = Logger.getLogger(GyjrB2bBillQuerypledgebillRequestTestV1.class.getName());
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		GyjrB2bBillQuerypledgebillRequestV1 req = new GyjrB2bBillQuerypledgebillRequestV1();

		req.setServiceUrl("http://ip:port/api/gyjr/b2b/bill/qryacceptance/V1");

		GyjrB2bBillQuerypledgebillRequestV1.GyjrB2bBillQuerypledgebillRequestV1Biz bizContent = new GyjrB2bBillQuerypledgebillRequestV1.GyjrB2bBillQuerypledgebillRequestV1Biz();

		GyjrB2bBillQuerypledgebillRequestV1.TransinfoBiz transInfo = new GyjrB2bBillQuerypledgebillRequestV1.TransinfoBiz();

		transInfo.setProtocolCode("GYJR20260701105640610539404");
		transInfo.setPlatVendorId("29");
		transInfo.setPageIndex("1");
		transInfo.setPageSize("100");
		transInfo.setHolderAcctId("");
		transInfo.setPackAmtBgn("0");
		transInfo.setPackAmtEnd("100000000000");
		transInfo.setCdTp("0");
		transInfo.setDueDateBgn("20260220");
		transInfo.setDueDateEnd("20270220");
		transInfo.setAccptrClass("");
		List<String> packNoList = new ArrayList<>();
		transInfo.setPackNoList(packNoList);
		transInfo.setUkeyId("111");


		bizContent.setTransInfoBiz(transInfo);
		req.setBizContent(bizContent);

		GyjrB2bBillQuerypledgebillResponseV1 response;

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

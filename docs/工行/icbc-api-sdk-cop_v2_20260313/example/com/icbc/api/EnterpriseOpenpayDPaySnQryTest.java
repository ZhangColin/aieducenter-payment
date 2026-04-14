package local;

import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.EnterPriseOpenPayDPaySnQryRequestV1;
import com.icbc.api.request.EnterPriseOpenPayDPaySnQryRequestV1.EnterPriseOpenPayDPaySnQryRequestV1Biz;
import com.icbc.api.response.EnterPriseOpenPayDPaySnQryResponseV1;

public class EnterpriseOpenpayDPaySnQryTest {

	/**
	 * @param args
	 */

	protected static final String MY_PRIVATE_KEY = "XXXXXXX";

	protected static final String APIGW_PUBLIC_KEY = "XXXXX";

	protected static final String APP_ID = "123";

	protected static final String password = "12345678";

	protected static final String MY_PUB_KEY = "XXXXX";
	protected static final String encryptType = "xxxx";
	protected static final String encryptKey = "xxxx";

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				MY_PRIVATE_KEY, APIGW_PUBLIC_KEY, MY_PUB_KEY, password);

		// �����������request
		EnterPriseOpenPayDPaySnQryRequestV1 request = new EnterPriseOpenPayDPaySnQryRequestV1();

		// ��������·��
		request.setServiceUrl("https://ip:port/api/enterprise/openpay/dPaySnQry/V1");

		EnterPriseOpenPayDPaySnQryRequestV1Biz bizContent = new EnterPriseOpenPayDPaySnQryRequestV1Biz();

		bizContent.setFileSerialno("fsZHJlyd0049");
		request.setBizContent(bizContent);
		EnterPriseOpenPayDPaySnQryResponseV1 response;

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(msgId);

		try {
			response = client.execute(request, msgId);
			if (response.isSuccess()) {
				// ҵ��ɹ�����
				System.out.println("success");//
			} else {
				// ʧ��
				System.out.println("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}

}

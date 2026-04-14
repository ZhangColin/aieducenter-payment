package com.icbc.api;

import com.icbc.api.request.MybankMerMerinQuerybatchthawRequestV1;
import com.icbc.api.request.MybankMerMerinQuerybatchthawRequestV1.MybankMerMerinQuerybatchthawRequestV1Biz;
import com.icbc.api.response.MybankMerMerinQuerybatchthawResponseV1;

public class MybankMerMerinQuerybatchthawTestV1 {
	// 1閵嗕胶缍夐崗鍐插彆闁斤拷
	protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEB****";

	// 2閵嗕工ppid
	protected static final String APP_ID = "100000000000040****";

	// 3閵嗕礁鐦戦柦銉ヮ嚠鐠併倛鐦夐弬鐟扮础閿涘苯鍙曢柦銉ユ躬API楠炲啿褰撮惂鏄忣唶閿涘本顒濇径鍕礋缁変線鎸�
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9wOryq9****";

	public static void main(String[] args) throws Exception {

		// 缁涙儳鎮曠猾璇茬�锋稉绡烻A2閺冭绱濋棁锟芥导鐘插弳appid閿涘瞼顫嗛柦銉ユ嫲缂冩垵鍙ч崗顒勬寽閿涘瞼顒烽崥宥囪閸ㄥ濞囬悽銊ョ暰閸婄硰cbcConstants.SIGN_TYPE_RSA2閿涘苯鍙炬禒鏍у棘閺侀濞囬悽銊у繁閻礁锟斤拷
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

		MybankMerMerinQuerybatchthawRequestV1 request = new MybankMerMerinQuerybatchthawRequestV1();

		// 4閵嗕焦鐗撮幑顔界ゴ鐠囨洜骞嗘晶鍐ㄦ嫲閻㈢喍楠囬悳顖氼暔閺囨寧宕查惄绋跨安ip閸滃瞼顏崣锟�
		request.setServiceUrl("http://******/api/mybank/mer/merin/querybatchthaw/V1");
		// 5閵嗕浇顕�靛湱鍙庨幒銉ュ經閺傚洦銆傞悽鈺瀒zContent.setxxx()閺傝纭剁�甸�涚瑹閸斺�茬瑐闁焦鏆熼幑顔跨箻鐞涘矁绁撮崐锟�
		MybankMerMerinQuerybatchthawRequestV1Biz bizContent = new MybankMerMerinQuerybatchthawRequestV1Biz();
		bizContent.setRegBatchNo("***");
		bizContent.setOutAgreeNo("***");
		request.setBizContent(bizContent);
		MybankMerMerinQuerybatchthawResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");// msgId濞戝牊浼呴柅姘愁唵閸烆垯绔寸紓鏍у娇閿涘矁顩﹀Ч鍌涚槨濞喡ょ殶閻€劎瀚粩瀣晸閹存劧绱滱PP缁狙冩暜娑擄拷

			//System.out.println("response:" + response.getReturnCode());

			if (response.isSuccess()) {
				// 6閵嗕椒绗熼崝鈩冨灇閸旂喎顦╅悶鍡礉鐠囬攱鐗撮幑顔藉复閸欙絾鏋冨锝囨暏response.getxxx()閼惧嘲褰囬崥灞绢劄鏉╂柨娲栭惃鍕瑹閸斺剝鏆熼幑锟�
				//System.out.println("ReturnCode:" + response.getReturnCode());
				//System.out.println("response:" + response);
				//System.out.print("handleresult: " + response.getHandleResult());
			} else {
				// 婢惰精瑙�
				//System.out.println("ReturnCode:" + response.getReturnCode());
				//System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

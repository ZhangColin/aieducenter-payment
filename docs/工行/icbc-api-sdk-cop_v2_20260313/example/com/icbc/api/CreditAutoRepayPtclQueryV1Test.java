package com.icbc.api;


import com.icbc.api.request.CreditAutoRepayPtclQueryRequestV1;
import com.icbc.api.response.CreditAutoRepayPtclQueryResponseV1;

/**
 * @ClassName
 * @Descirption 查询信用卡自动还款协议
 * @Author kfzx-wangd2
 */
public class CreditAutoRepayPtclQueryV1Test {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
    protected static final String appId = "123";

    protected static final String privateKey = "actualprivateKeyValue";
    protected static final String apigwPublicKey = "actualpublicKeyValue";
    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
		CreditAutoRepayPtclQueryRequestV1 request = new CreditAutoRepayPtclQueryRequestV1();

        request.setServiceUrl("http://ip:port/api/credit/auto/repay/ptcl/query/V1");
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz param = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz();
        //req_json
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson reqJson = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson();

		//public
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.PubReqInfo pubReqInfo = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.PubReqInfo();
        pubReqInfo.setProduct_id("101");
        pubReqInfo.setProject_id("202");
        pubReqInfo.setZoneno("200");
        pubReqInfo.setBrno("620");
        pubReqInfo.setTellerno("12");
        pubReqInfo.setServface("10");
		reqJson.setPubReqInfo(pubReqInfo);
		//channel
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Channel channel = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Channel();
        channel.setChan_type("404");
        channel.setChan_serialno("040402005624216001000003000");
        channel.setOapp("F-APIP");
        reqJson.setChannel(channel);
		//commctrl
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Commctrl commctrl = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Commctrl();
        commctrl.setCkusr_f("0");
        reqJson.setCommctrl(commctrl);
		//comminfo
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Comminfo comminfo = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Comminfo();
        comminfo.setTermid("231121131111111");
        reqJson.setComminfo(comminfo);
		//inqwork
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Inqwork inqwork = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Inqwork();
        inqwork.setInit_flag("1");
        inqwork.setRow_req("5");
        reqJson.setInqwork(inqwork);
		//irow4041
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Irow4041 irow4041 = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Irow4041();
        irow4041.setIncardno("4518110021554328");
        irow4041.setIncurrtype("1");
        reqJson.setIrow4041(irow4041);
		//icom4041
		CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Icom4041 icom4041 = new CreditAutoRepayPtclQueryRequestV1.CreditAutoRepayPtclQueryV1Biz.ReqJson.Icom4041();
        icom4041.setIncardno("4518110021554328");
        icom4041.setIncurrtype("1");
        icom4041.setOutacctype("");
        icom4041.setOutcardno("");
        icom4041.setOutcurrtype("");
        icom4041.setCashexf("0");
        reqJson.setIcom4041(icom4041);
		param.setReqJson(reqJson);
        request.setBizContent(param);
		CreditAutoRepayPtclQueryResponseV1 response = new CreditAutoRepayPtclQueryResponseV1();
        try {
            response = client.execute(request);
        } catch (Exception e) {
        }
        if (response.isSuccess()) {
        } else {
        }
    }


}

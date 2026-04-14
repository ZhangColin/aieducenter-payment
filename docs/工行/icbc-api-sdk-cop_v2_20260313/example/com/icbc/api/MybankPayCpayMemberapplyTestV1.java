package api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankPayCpayMemberapplyRequestV1;
import com.icbc.api.request.MybankPayCpayMemberapplyRequestV1.MybankPayCpayMemberapplyRequestV1Biz;
import com.icbc.api.response.MybankPayCpayMemberapplyResponseV1;

import java.util.*;

public class MybankPayCpayMemberapplyTestV1 {
    protected static final String MY_PRIVATE_KEY = "XXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXX";
    
    protected static final String APP_ID = "123";
    
    
    public static void main(String[] args) {

		manage01();
//		manage02();
    }
	
	//普通的
	public static void manage01(){
				DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        
        MybankPayCpayMemberapplyRequestV1 req = new MybankPayCpayMemberapplyRequestV1();
        
        req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/memberapply/V1");

        MybankPayCpayMemberapplyRequestV1Biz biz = new MybankPayCpayMemberapplyRequestV1Biz();
        
        biz.setAgreeCode("AGREECODE20190215");
        biz.setMemberNo("001");
        biz.setMemberName("会员号1");
		biz.setCopyMemberNo("001");
        biz.setMemberType("01");
        biz.setMemberRole("2");
        biz.setCertificateType("1");
        biz.setCertificateId("123123123");
        biz.setCorpRepreName("某某某");
        biz.setCorpRepreIdType("1");
        biz.setCorpRepreId("123123123123");
        biz.setCorpRepreSignDate("2020-02-22");
        biz.setDealName("某某某");
        biz.setDealTelphoneNo("18575637316");
        biz.setMallUrl("http://XXXX");
        biz.setIcpCode("123455");
        biz.setSingNoNoteAmtd("150000");
        biz.setEnterAmtType("0");
        biz.setAccBankFlag("1");
        biz.setAccno("123123123");
        biz.setAccName("测试");
        biz.setAccBankName("测试");
        biz.setMerEname("测试");
        biz.setMerShname("测试");
        biz.setSaleDepName("测试");
        biz.setShopAddr("测试");
        biz.setPostCode("12345");
        biz.setLinkCode("12345");
        biz.seteMail("xx@.qq.com");
        biz.setRegAmt("1");
        biz.setCallbackUrl("www.baidu.com");
        biz.setOperType("1");
        biz.setBillPlaNo("123");
        Map guaranteeInfo = new HashMap<>();
        biz.setGuaranteeInfo(guaranteeInfo);
        guaranteeInfo.put("b2cGuaranteePay","1");
        MybankPayCpayMemberapplyRequestV1.BeanEnsureInfo beanEnsureInfo = new MybankPayCpayMemberapplyRequestV1.BeanEnsureInfo();
        biz.setEnsureInfo(beanEnsureInfo);
        beanEnsureInfo.setAccBankFlag("1");
        beanEnsureInfo.setAccBankName("工商银行");
        beanEnsureInfo.setAccBankNo("105");
        beanEnsureInfo.setAccname("测试账户");
        beanEnsureInfo.setAccno("0200000209214297964");
        beanEnsureInfo.setEnsurePayFlag("2");
        beanEnsureInfo.setPhone("13811111113");
        beanEnsureInfo.setB2cEnsurePay("1");

        List<MybankPayCpayMemberapplyRequestV1.BeanBillAccInfo> billAccInfoList = new ArrayList<MybankPayCpayMemberapplyRequestV1.BeanBillAccInfo>();
        MybankPayCpayMemberapplyRequestV1.BeanBillAccInfo billAccInfo = new MybankPayCpayMemberapplyRequestV1.BeanBillAccInfo();
        billAccInfo.setBillAccBankFlag( "1");
        billAccInfo.setBillAccno("123");
        billAccInfo.setBillBankNo("123");
        billAccInfoList.add(billAccInfo);
        biz.setBillAccInfo(billAccInfoList);
        req.setBizContent(biz);
        MybankPayCpayMemberapplyResponseV1 response = new MybankPayCpayMemberapplyResponseV1();

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        //log.fine(msgId);

        try {
            response = client.execute(req, msgId);
            //log.fine("JSONObject.toJSONString(response):"+JSONObject.toJSONString(response));
            if (response.isSuccess()) {
              
                //log.fine("success");
            } else {
                
                //log.fine("error");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        } finally {
        }
	}
	
	//开卡的的
	public static void manage02(){
		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        
        MybankPayCpayMemberapplyRequestV1 req = new MybankPayCpayMemberapplyRequestV1();
        
        req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/memberapply/V1");

        MybankPayCpayMemberapplyRequestV1Biz biz = new MybankPayCpayMemberapplyRequestV1Biz();
        
        biz.setAgreeCode("AGREECODE20190215");
        biz.setMemberNo("001");
        biz.setMemberName("会员号1");
        biz.setMemberType("01");
        biz.setMemberRole("2");
        biz.setOperType("5");
        
        MybankPayCpayMemberapplyRequestV1.BeanEnSurePayCardApply enSurePayCardApply = new MybankPayCpayMemberapplyRequestV1.BeanEnSurePayCardApply();
        enSurePayCardApply.setDistriButorCode("000021");
        
        biz.setEnSurePayCardApply(enSurePayCardApply);
        
        req.setBizContent(biz);
        MybankPayCpayMemberapplyResponseV1 response = new MybankPayCpayMemberapplyResponseV1();
        
        

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        //log.fine(msgId);

        try {
            response = client.execute(req, msgId);
            //log.fine("JSONObject.toJSONString(response):"+JSONObject.toJSONString(response));
            if (response.isSuccess()) {
              
                //log.fine("success");
            } else {
                
                //log.fine("error");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        } finally {
        }
	}
}

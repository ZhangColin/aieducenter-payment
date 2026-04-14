package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiBillDiscountEnquiryRequestV1;
import com.icbc.api.response.JftApiBillDiscountEnquiryResponseV1;

import java.util.ArrayList;
import java.util.List;

public class JftApiBillDiscountEnquiryQueryTestV1 {
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	protected static final String APP_ID = "[需替换]应用方APPID";
	String host = "[需替换]工行API网关地址";
    String apiUrl= host+"/api/jft/api/billdis/enquiry/qry/V1";
    String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

	public void test_cop() throws Exception {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

		JftApiBillDiscountEnquiryRequestV1 request = new JftApiBillDiscountEnquiryRequestV1();
		request.setServiceUrl(apiUrl);

		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiBillDiscountEnquiryRequestV1.JftApiBillDiscountEnquiryRequestV1Biz bizContent = new JftApiBillDiscountEnquiryRequestV1.JftApiBillDiscountEnquiryRequestV1Biz();
		bizContent.setAppId(APP_ID);//合作方编号
		bizContent.setOutVendorId("10086868688888888888");//商户外部系统编号
        bizContent.setSerialNo("QBIQUOTA0046761216");//请求序列号
        bizContent.setQryType("1");//查询类型
        bizContent.setQuoteAcctsvcr("102100099999");//贴现行大额行号
        bizContent.setCdTp("AC01");//票据种类
        bizContent.setCdMdm("ME02");//票据介质
        bizContent.setCreditCode("0000000011068751X0");//统一社会信用代码
		bizContent.setDealType("01");//交易方式
        List<JftApiBillDiscountEnquiryRequestV1.JftApiBillDiscountEnquiryRequestV1Biz.QuoteCdParamBo> quoteCdList = new ArrayList<JftApiBillDiscountEnquiryRequestV1.JftApiBillDiscountEnquiryRequestV1Biz.QuoteCdParamBo>();
        JftApiBillDiscountEnquiryRequestV1.JftApiBillDiscountEnquiryRequestV1Biz.QuoteCdParamBo paramBo = new JftApiBillDiscountEnquiryRequestV1.JftApiBillDiscountEnquiryRequestV1Biz.QuoteCdParamBo();
        paramBo.setCdNo("1234567890");//票号/票包号
        paramBo.setRangeBgn("11111");//起始子区间
        paramBo.setRangeEnd("22222");//截止子区间
        paramBo.setAmount("100.97");//票面金额
        paramBo.setDueDate("20220915");//票据到期日
        paramBo.setAccptrAcctsvcr("102100099889");//承兑行大额行号
        paramBo.setCdTp("AC01");//票据种类
        paramBo.setCdMdm("ME02");//票据介质
        quoteCdList.add(paramBo);
		bizContent.setQuoteCdList(quoteCdList);
		request.setBizContent(bizContent);

		JftApiBillDiscountEnquiryResponseV1 response;
		try {
			response = (JftApiBillDiscountEnquiryResponseV1) client.execute(request, System.currentTimeMillis()+"");
			if (response.isSuccess()) {
				//业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new JftApiBillDiscountEnquiryQueryTestV1().test_cop();
	}

}

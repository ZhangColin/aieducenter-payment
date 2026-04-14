package com.icbc.api.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessB2cScenePaymentsignRequestV1;
import com.icbc.api.request.CardbusinessB2cScenePaymentsignRequestV1.CardbusinessB2cScenePaymentsignRequestV1Biz;
import com.icbc.api.response.CardbusinessB2cScenePaymentsignResponseV1;

public class CardbusinessB2cScenePaymentsignV1Test {
	public CardbusinessB2cScenePaymentsignV1Test(){
		super();
	}
	public void init() throws ServletException {
	}
	
	protected void service(HttpServletRequest request,HttpServletResponse response)
	throws javax.servlet.ServletException, IOException {


		request.setCharacterEncoding("GBK");
		
		String service_url = request.getParameter("service_url");//API测试环境 交易对应api url
		
		//公共请求通讯区
		String app_id = request.getParameter("app_id");//APPID
		
		String charset = request.getParameter("charset");//字符集
		
		String public_key = request.getParameter("public_key");//
		
		String private_key = request.getParameter("private_key");//
		
		String external_id = request.getParameter("external_id");//
		
		String pre_agreement_id = request.getParameter("pre_agreement_id");//
		
		String merchant_id = request.getParameter("merchant_id");//
		
		String external_agreement_no = request.getParameter("external_agreement_no");//商户签约号
		
		String merchant_type = request.getParameter("merchant_type");//	商户类型，1-线上POS档案商户；2-线下POS档案商户	
		
		String cardno = request.getParameter("cardno");//签约卡号		
		
		String ums_verify_code = request.getParameter("ums_verify_code");//短信验证码
		
		String ums_msg_no = request.getParameter("ums_msg_no");//短信编号
		

		
		private_key = private_key.replaceAll(" ", "+");
		//String api_public_key = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(app_id, private_key, public_key);	
		//测试环境暂时屏蔽AES加密
		//DefaultIcbcClient client = new DefaultIcbcClient(app_id, "RSA",private_key,"UTF-8","json",public_key,"AES",aes_key,"","");	
		
		response.setHeader("Content-Type", "text/html; charset=" + charset);
		PrintWriter out = response.getWriter();	
		CardbusinessB2cScenePaymentsignRequestV1 requestApi = new CardbusinessB2cScenePaymentsignRequestV1();
		CardbusinessB2cScenePaymentsignRequestV1Biz bizContent = new CardbusinessB2cScenePaymentsignRequestV1Biz();

		bizContent.setExternalAgreementNo(external_agreement_no);
		bizContent.setExternalId(external_id);
		bizContent.setMerchantId(merchant_id);
		bizContent.setMerchantType(merchant_type);
		bizContent.setCardno(cardno);
		bizContent.setUmsMsgNo(ums_msg_no);
		bizContent.setUmsVerifyCode(ums_verify_code);
		bizContent.setPreAgreementId(pre_agreement_id);
		requestApi.setBizContent(bizContent);
		requestApi.setServiceUrl(service_url);
		
		CardbusinessB2cScenePaymentsignResponseV1 responseApi;			
		
		try{
			responseApi = (CardbusinessB2cScenePaymentsignResponseV1) client.execute(requestApi, "msgId");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\">");
			out.write("</head>");
			out.write("<body>");
		if (responseApi.isSuccess()) {
			out.write(JSON.toJSONString(responseApi));

			} else {
				//失败
				out.write(JSON.toJSONString(responseApi));

			}		
		
		out.write("</body>");
		out.write("</html>");
		
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}				
	
	}
}

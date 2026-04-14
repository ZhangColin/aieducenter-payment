package com.icbc.api.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessB2cScenePaymentpresignRequestV1;
import com.icbc.api.request.CardbusinessB2cScenePaymentpresignRequestV1.CardbusinessB2cScenePaymentpresignRequestV1Biz;
import com.icbc.api.response.CardbusinessB2cScenePaymentpresignResponseV1;

public class CardbusinessB2cScenePaymentpresignV1Test {
  
	public CardbusinessB2cScenePaymentpresignV1Test(){
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
			
			String sign_validity_period = request.getParameter("sign_validity_period");//
			
			String merchant_id = request.getParameter("merchant_id");//
			
			String external_agreement_no = request.getParameter("external_agreement_no");//
			
			String merchant_acct = request.getParameter("merchant_acct");//
			
			String merchant_type = request.getParameter("merchant_type");//		
			
			String mobile = request.getParameter("mobile");//手机号
			
			String cardno = request.getParameter("cardno");//合作方二三类户		
			
			String cvn2 = request.getParameter("cvn2");
			
			String expired = request.getParameter("expired");
			
			String partner_app_id = request.getParameter("partner_app_id");//
			
			String partner_flag = request.getParameter("partner_flag");//	
			
			String cust_name = request.getParameter("cust_name");//客户姓名
			
			String cust_cert_type = request.getParameter("cust_cert_type");//客户证件类型
			
			String cust_cert_no = request.getParameter("cust_cert_no");//客户证件号码
			
			String cust_cert_expired = request.getParameter("cust_cert_expired");//证件有效期限
			
			String cust_nationality = request.getParameter("cust_nationality");//国籍
			
			String cust_sex = request.getParameter("cust_sex");//性别
			
			String cust_adress = request.getParameter("cust_adress");//联系地址
			
			String cust_profession = request.getParameter("cust_profession");//职业			
			
			String channel_type = request.getParameter("channel_type");//交易渠道：1-APP，2-非APP
			
			String cust_ip = request.getParameter("cust_ip");//	交易IP
			
			String cust_mac = request.getParameter("cust_mac");//客户端MAC地址
			
			String device_id = request.getParameter("device_id");//设备串号
			
			String cpu_id = request.getParameter("cpu_id");//CPU编号	
			
			String memory_capacity = request.getParameter("memory_capacity");//	内存容量	
			
			String system_version = request.getParameter("system_version");//	操作系统版本	
			
			String browser_version = request.getParameter("browser_version");//	浏览器版本
			
			String browser_lang = request.getParameter("browser_lang");//	浏览器语言
			
			String screen_resolution = request.getParameter("screen_resolution");//	屏幕分辨率
			
			String factory_info = request.getParameter("factory_info");//	厂商信息	

			String dccardflag = request.getParameter("dccardflag");//	签约支持卡种	
			
			String SendMessageFlag = request.getParameter("SendMessageFlag");//	重发短信标志	
			
			
			private_key = private_key.replaceAll(" ", "+");
			//String api_public_key = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
			//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
			DefaultIcbcClient client = new DefaultIcbcClient(app_id, private_key, public_key);	
			//测试环境暂时屏蔽AES加密
			//DefaultIcbcClient client = new DefaultIcbcClient(app_id, "RSA",private_key,"UTF-8","json",public_key,"AES",aes_key,"","");	
			
			response.setHeader("Content-Type", "text/html; charset=" + charset);
			PrintWriter out = response.getWriter();	
			CardbusinessB2cScenePaymentpresignRequestV1 requestApi = new CardbusinessB2cScenePaymentpresignRequestV1();
			CardbusinessB2cScenePaymentpresignRequestV1Biz bizContent = new CardbusinessB2cScenePaymentpresignRequestV1Biz();

			bizContent.setExternalAgreementNo(external_agreement_no);
			bizContent.setExternalId(external_id);
			bizContent.setMerchantAcct(merchant_acct);
			bizContent.setMerchantId(merchant_id);
			bizContent.setMerchantType(merchant_type);
			bizContent.setMobile(mobile);
			bizContent.setCardno(cardno);
			bizContent.setCustCertNo(cust_cert_no);
			bizContent.setCustCertType(cust_cert_type);
			bizContent.setCustName(cust_name);
			bizContent.setCustAdress(cust_adress);
			bizContent.setCustNationality(cust_nationality);
			bizContent.setCustCertExpired(cust_cert_expired);
			bizContent.setCustSex(cust_sex);
			bizContent.setPartnerAppId(partner_app_id);
			bizContent.setPartnerFlag(partner_flag);
			bizContent.setSignValidityPeriod(sign_validity_period);			
			bizContent.setBrowserLang(browser_lang);
			bizContent.setBrowserVersion(browser_version);
			bizContent.setChannelType(channel_type);
			bizContent.setCpuId(cpu_id);
			bizContent.setCustIp(cust_ip);
			bizContent.setCustMac(cust_mac);
			bizContent.setDeviceId(device_id);
			bizContent.setFactoryInfo(factory_info);
			bizContent.setMemoryCapacity(memory_capacity);
			bizContent.setScreenResolution(screen_resolution);
			bizContent.setSystemVersion(system_version);
			bizContent.setDccardflag(dccardflag);
			bizContent.setSendMessageFlag(SendMessageFlag);
			requestApi.setBizContent(bizContent);
			requestApi.setServiceUrl(service_url);
			bizContent.setCvn2(cvn2);
			bizContent.setExpired(expired);
			bizContent.setCustProfession(cust_profession);
			
			CardbusinessB2cScenePaymentpresignResponseV1 responseApi;			
			
			try{
				responseApi = (CardbusinessB2cScenePaymentpresignResponseV1) client.execute(requestApi, "msgId");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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

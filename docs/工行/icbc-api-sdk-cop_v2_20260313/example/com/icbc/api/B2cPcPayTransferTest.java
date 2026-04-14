package com.icbc.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.ApiJsonUtils;
import com.icbc.api.BizContent;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.B2cPcPayTransferRequestV1;

public class B2cPcPayTransferTest extends HttpServlet {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1588786734333856129L;

	public B2cPcPayTransferTest() {
		super();
	}

	public void init() throws ServletException {
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {

		request.setCharacterEncoding("GBK");
		
		String service_url = request.getParameter("service_url");//API测试环境 交易对应api url
		
		//公共请求通讯区
		String app_id = request.getParameter("app_id");//APPID
		
		String charset = request.getParameter("charset");//字符集
		
		String ca = request.getParameter("ca");//ca证书
		String private_key = request.getParameter("private_key");//私钥
		String password = request.getParameter("password");//密码
		
		String notify_url = request.getParameter("notify_url");//通知地址
		
		String interfaceName = request.getParameter("interfaceName");//接口名
		String interfaceVersion = request.getParameter("interfaceVersion");//接口版本号
		
		// 处理前台提交的数据
		/*请注意，对于可能出现中文的goods_name,mer_hint,remark1,remark2,mer_var,goods_address,mer_order_remark,backup1,backup2,backup3,backup4
		 * 字段，需要进行base64编码再传过来，否则会出现乱码*/
		String ret = dealPostData(request);

		ca = ca.replaceAll(" ", "+");
		private_key = private_key.replaceAll(" ", "+");

		//String api_public_key = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		UiIcbcClient client = new UiIcbcClient(app_id, private_key, charset, ca, password);

		response.setHeader("Content-Type", "text/html; charset=" + charset);
		PrintWriter out = response.getWriter();
		try {
			B2cPcPayTransferRequestV1 requestApi = new B2cPcPayTransferRequestV1();

			BizContent bizContent = JSON.parseObject(ret,
					requestApi.getBizContentClass());
			requestApi.setBizContent(bizContent);
			requestApi.setServiceUrl(service_url);
			requestApi.setNotifyUrl(notify_url);
			requestApi.setInterfaceName(interfaceName);
			requestApi.setInterfaceVersion(interfaceVersion);
			
			String str = client.buildPostForm(requestApi);
			System.out.println(str);

			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\">");
			out.write("</head>");
			out.write("<body>");
			out.write(str);
			out.write("</body>");
			out.write("</html>");
			/*B2CPayTransferResponseV1 applePayRes = client.execute(requestApi);
			String res = JSONObject.toJSONString(applePayRes);
			Map<?, ?> resJson = null;
			Map<?, ?> bank = null;
			try {
				resJson = ApiJsonUtils.jsonToBean(res, Map.class);
				bank = (Map<?, ?>) resJson.get("bank");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object retCodeObj = null;
			String return_code = "";
			int retCodeInt = 0;
			Double retCodeDou = 0.0;
			try {
				retCodeObj = resJson.get("return_code");
				if (retCodeObj instanceof String) {
					return_code = (String) retCodeObj;
				} else if (retCodeObj instanceof Integer) {
					retCodeInt = (Integer) retCodeObj;
					return_code = retCodeInt + "";
				} else if (retCodeObj instanceof Double) {
					retCodeDou = (Double) retCodeObj;
					return_code = retCodeDou + "";
					if (return_code.indexOf('.') != -1) {
						return_code = return_code.substring(0,
								return_code.indexOf('.'));
					}
				} else {
					return_code = "";
				}
			} catch (Exception e) {
				return_code = "";
			}
			String return_msg = "";
			try {
				return_msg = (String) resJson.get("return_msg");
			} catch (Exception e) {
				return_msg = "";
			}
			String tran_error_code = "";
			try {
				tran_error_code = (String) bank.get("tran_error_code");
			} catch (Exception e) {
				tran_error_code = "";
			}
			String resp_code = "";
			try {
				resp_code = (String) bank.get("resp_code");
			} catch (Exception e) {
				resp_code = "";
			}
			return_msg = URLDecoder.decode(return_msg, "UTF-8");
			out.write("返回代码：" + return_code);
			out.write("<br/>");
			out.write("返回信息：" + return_msg);
			out.write("<br/>");
			out.write("错误代码：" + tran_error_code);
			out.write("<br/>");
			out.write("银联应答码：" + resp_code);
			out.write("<br/>");
			out.write("<br/>");
			out.write("返回报文：" + res);*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

	}

	public String dealPostData(HttpServletRequest request)
			throws UnsupportedEncodingException {

		String order_date = request.getParameter("order_date");
		String order_id = request.getParameter("order_id");
		String amount = request.getParameter("amount");
		String installment_times = request.getParameter("installment_times");
		String cur_type = request.getParameter("cur_type");
		String mer_id = request.getParameter("mer_id");
		String mer_acct = request.getParameter("mer_acct");
		
		String verify_join_flag = request.getParameter("verify_join_flag");
		String language = request.getParameter("language");
		
		String goods_id = request.getParameter("goods_id");
		String goods_name = request.getParameter("goods_name");
		String goods_num = request.getParameter("goods_num");
		String carriage_amt = request.getParameter("carriage_amt");
		String mer_hint = request.getParameter("mer_hint");
		String remark1 = request.getParameter("remark1");
		String remark2 = request.getParameter("remark2");
		
		String credit_type = request.getParameter("credit_type");
		String mer_reference = request.getParameter("mer_reference");
		String mer_custom_ip = request.getParameter("mer_custom_ip");
		String goods_type = request.getParameter("goods_type");
		String mer_custom_id = request.getParameter("mer_custom_id");
		String mer_custom_phone = request.getParameter("mer_custom_phone");
		String goods_address = request.getParameter("goods_address");
		String mer_order_remark = request.getParameter("mer_order_remark");
		
		String orderFlag_ztb = request.getParameter("orderFlag_ztb");
		String o2o_mer_id = request.getParameter("o2o_mer_id");
		String elife_mer_id = request.getParameter("elife_mer_id");
		String pay_expire = request.getParameter("pay_expire");
		String return_url = request.getParameter("return_url");
		
		String mer_var = request.getParameter("mer_var");
		String notify_type = request.getParameter("notify_type");
		String result_type = request.getParameter("result_type");
		String backup1 = request.getParameter("backup1");
		String backup2 = request.getParameter("backup2");
		String backup3 = request.getParameter("backup3");
		String backup4 = request.getParameter("backup4");
		String auto_refer_sec = request.getParameter("auto_refer_sec");

		Map<String, Object> bean = new HashMap<String, Object>();
		ApiJsonUtils.setValueAt(bean, "order_info.order_date", order_date);
		ApiJsonUtils.setValueAt(bean, "order_info.order_id", order_id);
		ApiJsonUtils.setValueAt(bean, "order_info.amount", amount);
		ApiJsonUtils.setValueAt(bean, "order_info.installment_times", installment_times);
		ApiJsonUtils.setValueAt(bean, "order_info.cur_type", cur_type);
		ApiJsonUtils.setValueAt(bean, "order_info.mer_id", mer_id);
		ApiJsonUtils.setValueAt(bean, "order_info.mer_acct", mer_acct);
		
		ApiJsonUtils.setValueAt(bean, "custom.verify_join_flag", verify_join_flag);
		ApiJsonUtils.setValueAt(bean, "custom.language", language);
		
		ApiJsonUtils.setValueAt(bean, "message.goods_id", goods_id);
		ApiJsonUtils.setValueAt(bean, "message.goods_name", goods_name);
		ApiJsonUtils.setValueAt(bean, "message.goods_num", goods_num);
		ApiJsonUtils.setValueAt(bean, "message.carriage_amt", carriage_amt);
		ApiJsonUtils.setValueAt(bean, "message.mer_hint", mer_hint);
		ApiJsonUtils.setValueAt(bean, "message.remark1", remark1);
		ApiJsonUtils.setValueAt(bean, "message.remark2", remark2);
		
		ApiJsonUtils.setValueAt(bean, "message.credit_type", credit_type);
		ApiJsonUtils.setValueAt(bean, "message.mer_reference", mer_reference);
		ApiJsonUtils.setValueAt(bean, "message.mer_custom_ip", mer_custom_ip);
		ApiJsonUtils.setValueAt(bean, "message.goods_type", goods_type);
		ApiJsonUtils.setValueAt(bean, "message.mer_custom_id", mer_custom_id);
		ApiJsonUtils.setValueAt(bean, "message.mer_custom_phone", mer_custom_phone);
		ApiJsonUtils.setValueAt(bean, "message.goods_address", goods_address);
		ApiJsonUtils.setValueAt(bean, "message.mer_order_remark", mer_order_remark);
		
		ApiJsonUtils.setValueAt(bean, "message.orderFlag_ztb", orderFlag_ztb);
		ApiJsonUtils.setValueAt(bean, "message.o2o_mer_id", o2o_mer_id);
		ApiJsonUtils.setValueAt(bean, "message.elife_mer_id", elife_mer_id);
		ApiJsonUtils.setValueAt(bean, "message.pay_expire", pay_expire);
		ApiJsonUtils.setValueAt(bean, "message.return_url", return_url);
		
		ApiJsonUtils.setValueAt(bean, "message.mer_var", mer_var);
		ApiJsonUtils.setValueAt(bean, "message.notify_type", notify_type);
		ApiJsonUtils.setValueAt(bean, "message.result_type", result_type);
		ApiJsonUtils.setValueAt(bean, "message.backup1", backup1);
		ApiJsonUtils.setValueAt(bean, "message.backup2", backup2);
		ApiJsonUtils.setValueAt(bean, "message.backup3", backup3);
		ApiJsonUtils.setValueAt(bean, "message.backup4", backup4);
		ApiJsonUtils.setValueAt(bean, "message.auto_refer_sec", auto_refer_sec);

		String input = "";
		input = ApiJsonUtils.beanToJson(bean);
		return input;
	}
}

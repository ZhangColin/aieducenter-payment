package com.icbc.api;
import com.icbc.api.BizContent;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.B2c1006PayTransferRequestV1;
import com.icbc.api.request.B2c1006PayTransferRequestV1.B2CPayTransferRequestV1Biz;
import com.icbc.api.request.B2cPassfreePaymentPayRequestV2.B2cPassfreePaymentPayRequestV2Biz;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class B2c1006PayTransferV1Test extends HttpServlet
{
  private static final long serialVersionUID = 1588786734333856129L;

  public void init()
    throws ServletException
  {
  }

  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    request.setCharacterEncoding("GBK");

    String service_url = request.getParameter("service_url");

    String app_id = request.getParameter("app_id");

    String charset = request.getParameter("charset");

    String ca = request.getParameter("ca");
    String private_key = request.getParameter("private_key");
    String password = request.getParameter("password");

    String notify_url = request.getParameter("notify_url");

    String interfaceName = request.getParameter("interfaceName");
    String interfaceVersion = request.getParameter("interfaceVersion");
    String clientType = request.getParameter("clientType");

    String order_date = request.getParameter("order_date");
    String order_id = request.getParameter("order_id");
    String amount = request.getParameter("amount");
    String installment_times = request.getParameter("installment_times");
    String cur_type = request.getParameter("cur_type");
    String mer_id = request.getParameter("mer_id");
    String mer_acct = request.getParameter("mer_acct");
    String wallet_flag = request.getParameter("wallet_flag");
    String external_app_id = request.getParameter("external_app_id");

    String verify_join_flag = request.getParameter("verify_join_flag");
    String language = request.getParameter("language");
    String cust_id = request.getParameter("cust_id");
    String mobile = request.getParameter("mobile");

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

    String mer_var = request.getParameter("mer_var");
    String notify_type = request.getParameter("notify_type");
    String result_type = request.getParameter("result_type");
    String autoReferSec = request.getParameter("autoReferSec");
    String is_real = request.getParameter("is_real");
    String subidno = request.getParameter("subidno");
    String prompt_text = request.getParameter("prompt_text");
    String backup1 = request.getParameter("backup1");
    String backup2 = request.getParameter("backup2");
    String backup3 = request.getParameter("backup3");
    String backup4 = request.getParameter("backup4");

    String test = "";

    ca = ca.replaceAll(" ", "+");
    private_key = private_key.replaceAll(" ", "+");

    UiIcbcClient client = new UiIcbcClient(app_id, private_key, charset, ca, password);

    response.setHeader("Content-Type", "text/html; charset=" + charset);
    PrintWriter out = response.getWriter();
    try {
      B2c1006PayTransferRequestV1 requestApi = new B2c1006PayTransferRequestV1();

      B2CPayTransferRequestV1Biz bizContent = new B2CPayTransferRequestV1Biz();
      bizContent.setAmount(amount);
      bizContent.setAutoReferSec(autoReferSec);
      bizContent.setBackup1(backup1);
      bizContent.setBackup2(backup2);
      bizContent.setBackup3(backup3);
      bizContent.setBackup4(backup4);
      bizContent.setCarriageAmt(carriage_amt);
      bizContent.setCreditType(credit_type);
      bizContent.setCurType(cur_type);
      bizContent.setCustId(cust_id);
      bizContent.setExternalAppId(external_app_id);
      bizContent.setGoodsAddress(goods_address);
      bizContent.setGoodsID(goods_id);
      bizContent.setGoodsName(goods_name);
      bizContent.setGoodsNum(goods_num);
      bizContent.setGoodsType(goods_type);
      bizContent.setInstallmentTimes(installment_times);
      bizContent.setIsReal(is_real);
      bizContent.setLanguage(language);
      bizContent.setMerAcct(mer_acct);
      bizContent.setMerCustomID(mer_custom_id);
      bizContent.setMerCustomIp(mer_custom_ip);
      bizContent.setMerCustomPhone(mer_custom_phone);
      bizContent.setMerHint(mer_hint);
      bizContent.setMerID(mer_id);
      bizContent.setMerOrderRemark(mer_order_remark);
      bizContent.setMerReference(mer_reference);
      bizContent.setMerVAR(mer_var);
      bizContent.setMobile(mobile);
      bizContent.setNotifyType(notify_type);
      bizContent.setOrderDate(order_date);
      bizContent.setOrderId(order_id);
      bizContent.setPromptText(prompt_text);
      bizContent.setRemark1(remark1);
      bizContent.setRemark2(remark2);
      bizContent.setResultType(result_type);
      bizContent.setSubidno(subidno);
      bizContent.setVerifyJoinFlag(verify_join_flag);
      bizContent.setWalletFlag(wallet_flag);
      requestApi.setServiceUrl(service_url);
      requestApi.setNotifyUrl(notify_url);
      requestApi.setInterfaceName(interfaceName);
      requestApi.setInterfaceVersion(interfaceVersion);
      requestApi.setClientType(clientType);
      

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
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      out.flush();
      out.close();
    }
  }

}

/* Location:           C:\Users\kfzx-gaox01\Desktop\applog\
 * Qualified Name:     com.icbc.servlet.b2c.B2C1006PayServlet
 * JD-Core Version:    0.6.0
 */

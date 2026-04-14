package com.icbc.ndf.jft.pay.api.impl.pay;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiMultiPayRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JftUiMultiPayTestServletV1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        try {
            String APP_ID = "[需替换]APP的编号";

            String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
			String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
            UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key);

            JftUiMultiPayRequestV1 request = new JftUiMultiPayRequestV1();

            request.setServiceUrl("[需替换]工行API网关地址/ui/jft/ui/multipay/V1");
            JftUiMultiPayRequestV1.JftUiMultiPayV1Biz bizContent = new JftUiMultiPayRequestV1.JftUiMultiPayV1Biz();

            bizContent.setAppId(APP_ID);//平台商户标识：提交支付请求的平台商户编号	10000000000000001
            bizContent.setOutVendorId("20180426");//子商户标识：提交支付请求的子商户编号
            bizContent.setOutUserId("M201801112326"); //用户标识
            bizContent.setOutOrderId("201804102908"); //平台对接方订单ID
            bizContent.setPayAmount("0.02");//支付金额(元)整数长度不能超过8位，小数位不能超过2位
            bizContent.setPayType("02");//合并支付标识（01为单订单支付，02为合并支付）
            bizContent.setTrxChannel("02");//交易渠道
            bizContent.setNotifyUrl("com");//商户通知URL：商户接收支付成功通知消息URL，平台商户建档时需要维护
            bizContent.setJumpUrl("com");//商户跳转URL:支付完成后回调地址（跳转商户页面），不支持传参
            bizContent.setQuitUrl("com");//回退URL：用来回退到商户的页面
            bizContent.setMac("a3-b4-c5");//PC mac:支付提交用户PC的Mac地址
            bizContent.setTrxIp("ip:port");//交易IP：支付提交用户IP地址
            bizContent.setTrxIpCity("北京");//交易IP所在地：支付提交用户IP所在地
            bizContent.setGoodsId("3212");//主商品编号（长度单位：字节）
            bizContent.setGoodsName("测试商品");//主商品名称（长度单位：字节）
            bizContent.setGoodsBody("商品描述");//主商品描述（长度单位：字节）
            bizContent.setVarNote("3212");//备注信息
            bizContent.setTpAppId("wx0f6flal9af93dbe6");//第三方应用ID,上送微信分配的公众账号ID
            bizContent.setTpOpenId("odg9h43-kX46sAmzGsk3tAvMPbU4");//第三方用户标识,上送用户在商户appid下的唯一标识
            bizContent.setWalletFlag("0");//是否使用e钱包标识
            bizContent.setCustCheckFlag("0");//是否客户实名认证检查

            //以下为子订单设置
            List<JftUiMultiPayRequestV1.JftUiMultiPayV1Biz.SubOrderInfo> subOrders = new ArrayList<SubOrderInfo>();
            JftUiMultiPayRequestV1.JftUiMultiPayV1Biz.SubOrderInfo sub1 = new JftUiMultiPayRequestV1.JftUiMultiPayV1Biz.SubOrderInfo();
            sub1.setOV("1129102"); //子商户标识
            sub1.setGI("good1"); //子商品编号（长度单位：字节）
            sub1.setGN("xx商品"); //子商品名称（长度单位：字节）
            sub1.setGB("商品1");// 子商品描述
            sub1.setOI("154529277181301"); //外部订单id
            sub1.setPA("1.23"); //子订单金额
            sub1.setVN("备注"); //商户备注
            subOrders.add(sub1);

            JftUiMultiPayRequestV1.JftUiMultiPayV1Biz.SubOrderInfo sub2 = new JftUiMultiPayRequestV1.JftUiMultiPayV1Biz.SubOrderInfo();
            sub2.setOV("1129103"); //子商户标识
            sub2.setGI("good2"); //子商品编号（长度单位：字节）
            sub2.setGN("xx商品"); //子商品名称（长度单位：字节）
            sub2.setGB("商品1");// 子商品描述
            sub2.setOI("154529277181302"); //外部订单id
            sub2.setPA("2"); //子订单金额
            sub2.setVN("备注"); //商户备注
            subOrders.add(sub2);

            bizContent.setSubOrders(subOrders);

            request.setBizContent(bizContent);

            resp.setHeader("Content-Type", "text/html;charset="
                    + IcbcConstants.CHARSET_UTF8);
            PrintWriter out = resp.getWriter();
            System.out.println(client.buildPostForm(request));

            out.write("<html>");
            out.write("<head>");
            out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
                    + IcbcConstants.CHARSET_UTF8 + "\">");
            out.write("</head>");
            out.write("<body>");
            out.write(client.buildPostForm(request));
            out.write("</body>");
            out.write("</html>");

        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

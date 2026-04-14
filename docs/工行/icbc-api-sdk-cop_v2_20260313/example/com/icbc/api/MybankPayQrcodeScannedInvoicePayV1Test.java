package com.icbc.api;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayQrcodeScannedInvoicePayRequestV1;
import com.icbc.api.request.MybankPayQrcodeScannedInvoicePayRequestV1.MybankPayQrcodeScannedInvoicePayRequestV1Biz;
import com.icbc.api.response.MybankPayQrcodeScannedInvoicePayResponseV1;


/**
 * API聚合支付被扫接口
 *
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankPayQrcodeScannedInvoicePayV1Test {

    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "请使用正确的公钥";
    //2、appid
    protected static final String APP_ID = "100000000****7354649";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankPayQrcodeScannedInvoicePayRequestV1 request = new MybankPayQrcodeScannedInvoicePayRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("[需替换]工行API网关地址/api/mybank/pay/qrcode/scanned/invoicepay/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MybankPayQrcodeScannedInvoicePayRequestV1Biz bizContent = new MybankPayQrcodeScannedInvoicePayRequestV1Biz();
        bizContent.setMerId("020*****460001"); //商户编号
        bizContent.setQrCode("134688****540238"); //付款码
        bizContent.setOutTradeNo("1111116*****666666555"); //外部订单号
        bizContent.setTradeDate("20230515"); //交易日期  格式:YYYYMMDD
        bizContent.setTradeTime("091001"); //交易时间hhmmss
        bizContent.setAttach("1231"); //商户附加信息
        bizContent.setOrderAmt("1"); //交易金额
        bizContent.setSubAppId("wxfb72f1a7d061d631"); //子商户应用号
        bizContent.setGoodsTag("DP");  //优惠标志

        JSONArray goodsDetailArray = new JSONArray();
        JSONObject gd1 = new JSONObject();
        gd1.put("goods_id", "123456");
        gd1.put("goods_name", "FOOD");
        gd1.put("quantity", 1);
        gd1.put("price", 100);
        goodsDetailArray.add(gd1);

        bizContent.setGoodsDetail(goodsDetailArray);
        bizContent.setSubject("订单标题，128字节长度");  //订单标题，128字节长度
        bizContent.setLocation("+37.12/-121.213");  //终端实时经纬度信息
        bizContent.setNetwork_license("P3100");  //终端入网认证编号
        bizContent.setDevice_type("01");  //设备类型
        bizContent.setSerial_num("");  //终端序列号
        bizContent.setDevice_id("");  //终端设备号
        bizContent.setEncrypt_rand_num("");  //加密随机因子
        bizContent.setSecret_text("");  //密文数据
        bizContent.setApp_version("");  //应用程序版本号
        bizContent.setDevice_ip("");  //商户端设备IP
        bizContent.setMobile_country_cd("460");  //移动国家代码
        bizContent.setMobile_net_num("");  //移动网络号码
        bizContent.setIcc_id("");  //ICCID
        bizContent.setLocation_cd1("");  //位置区域码1
        bizContent.setLbs_num1("");  //基站编号1
        bizContent.setLbs_signal1("");  //基站信号1
        bizContent.setLocation_cd2("");  //位置区域码2
        bizContent.setLbs_num2("");  //基站编号2
        bizContent.setLbs_signal2("");  //基站信号2
        bizContent.setLocation_cd3("");  //位置区域码3
        bizContent.setLbs_num3("");  //基站编号3
        bizContent.setLbs_signal3("");  //基站信号3
        bizContent.setTelecom_sys_id("");  //电信系统识别码
        bizContent.setTelecom_net_id("");  //电信网络识别码
        bizContent.setTelecom_lbs("");  //电信基站
        bizContent.setTelecom_lbs_signal("");  //电信基站信号
        bizContent.setDeviceInfo("");  //设备编号，商户自定义

        JSONObject invoiceInfo = new JSONObject();    // 发票信息
        invoiceInfo.put("trxIp", "xx.xx.xx.xx"); // 交易IP:支付提交的用户的IP地址
        invoiceInfo.put("invoiceNotifyUrl", "xx.xx.xx.xx");  // 发票状态变更通知地址
        invoiceInfo.put("invoiceType", "02");   // 发票类型
        invoiceInfo.put("buyerPersonType", "Y");   // 购买方自然人标识
        invoiceInfo.put("taxpayerName", "xxx");  // (购买方)名称
        invoiceInfo.put("drawerName", "xxx");    // 开票人
        invoiceInfo.put("drawerCardType", "201");    // 开票人证件类型
        invoiceInfo.put("drawerCardNumber", "xxxxxxxxxx");  // 开票人证件号码
        invoiceInfo.put("specificElements", "01");  // 特定要素
        invoiceInfo.put("mac", "xx.xx.xx.xx");   // mac地址

        JSONArray invoiceDetailInfoArray = new JSONArray();
        JSONObject invoiceDetailInfo1 = new JSONObject();
        invoiceDetailInfo1.put("goodsSeqno", "xxxxxx"); // 商品信息子序号
        invoiceDetailInfo1.put("projectName", "xxx");    // 项目名称
        invoiceDetailInfo1.put("goodsNum", "");   // 数量
        invoiceDetailInfo1.put("goodsAmt", "888.88");   // 商品金额(元)
        invoiceDetailInfo1.put("weight", "");     // 订单商品规格
        invoiceDetailInfo1.put("price", "xxx");  // 含税单价(元)
        invoiceDetailInfo1.put("units", "吨");  // 计量单位
        invoiceDetailInfo1.put("taxRate", "0.03");    // 增值税税率
        invoiceDetailInfo1.put("mergedCode", "xxxxxxx"); // 商品和服务税收分类合并编码
        invoiceDetailInfo1.put("invoiceBankNature", "00");  // 发票行性质
        invoiceDetailInfo1.put("preferentialType", "01");   // 优惠政策标识
        invoiceDetailInfoArray.add(invoiceDetailInfo1);
        invoiceInfo.put("invoiceDetailInfo", invoiceDetailInfoArray); // 商品信息列表参数
        bizContent.setInvoiceInfo(invoiceInfo);   // 发票信息

        request.setBizContent(bizContent);
        MybankPayQrcodeScannedInvoicePayResponseV1 response;
        try {
            response = client.execute(request, "msgId3");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.getReturnCode() == 0) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                //System.out.println("response:" + JSON.toJSONString(response));
                //System.out.println("ReturnCode:"+response.getReturnCode());
            } else {
                // 失败
                //System.out.println("ReturnCode:"+response.getReturnCode());
                //System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}

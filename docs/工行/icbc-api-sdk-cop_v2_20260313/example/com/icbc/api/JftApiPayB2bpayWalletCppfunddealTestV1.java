
public class JftApiPayB2bpayWalletcppfunddealTestV1 {



    public void test() {
        String APP_ID = "[需替换]APPID";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String icbcPulicKey = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON,
                icbcPulicKey, IcbcConstants.ENCRYPT_TYPE_AES, AES_Key, null, null);
        JftApiPayB2bpayWalletcppfunddealRequestV1 request = new JftApiPayB2bpayWalletcppfunddealRequestV1();
        String host = "[需替换]工行API网关地址";
        request.setServiceUrl(host + "/api/jft/api/pay/b2bpay/walletcppfunddeal/V1");
        JftApiPayB2bpayWalletcppfunddealRequestV1Biz bizContent = new JftApiPayB2bpayWalletcppfunddealRequestV1Biz();
        bizContent.setAppId(APP_ID);//平台商户标识
        bizContent.setOrderCode("orderCode1");//订单编号，合作方系统生成订单编号
        bizContent.setOutOrderId("out20220101a");//合作方交易流水号（每笔交易必须唯一）
        bizContent.setOutVendorId("123");//商户编号
        bizContent.setPayAmount("2000");//交易金额
        bizContent.setOrderCurr("001");//交易币种
        bizContent.setFundDealflag("2");//资金处理标志 1-充值 2-提现 ；入金只支持线上入金
        bizContent.setOperType("8");//业务种类（8-保证支付）
        bizContent.setSubmitTime("2021-01-01 23:59:59");//交易平台提交时间
        bizContent.setChannelType("1");//渠道类型  1-PC端
        bizContent.setOrderRemark("订单备注");//登记到交易明细的备注信息字段或跨行交易送给他行明细
        bizContent.setReceiptRemark("回单补充信息备注");//当需要打印在回单信息中时上送
        bizContent.setPurpose("用途");//打印到回单的用途栏
        bizContent.setSummary("摘要");//打印到回单的摘要栏
        request.setBizContent(bizContent);
        JftApiPayB2bpayWalletcppfunddealResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

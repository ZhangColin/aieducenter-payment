package com.icbc.api;

import com.icbc.api.request.JftApiPayB2bpayWalletCpdetailQueryRequestV1;
import com.icbc.api.response.JftApiPayB2bpayWalletCpdetailQueryResponseV1;

public class JftApiPayB2bpayWalletCpdetailQueryTestV1 {


    public void test() {
        String APP_ID = "[需替换]APPID";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String icbcPulicKey = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON,
                icbcPulicKey, IcbcConstants.ENCRYPT_TYPE_AES, AES_Key, null, null);
        JftApiPayB2bpayWalletCpdetailQueryRequestV1 request = new JftApiPayB2bpayWalletCpdetailQueryRequestV1();
        String host = "[需替换]工行API网关地址"; 
        request.setServiceUrl(host + "/api/jft/api/pay/b2bpay/walletcpdetailquery/V1");
        JftApiPayB2bpayWalletCpdetailQueryRequestV1.JftApiPayB2bpayWalletCpdetailQueryRequestV1Biz bizContent = new JftApiPayB2bpayWalletCpdetailQueryRequestV1.JftApiPayB2bpayWalletCpdetailQueryRequestV1Biz();
        bizContent.setAppId(APP_ID);//合作方编号，同通用请求参数中的app_id
        bizContent.setQueryType("001");//查询类型，001-根据市场和订单号查询 002-根据市场+会员编号查询会员自有资金余额
        bizContent.setOutVendorId("123");//商户编号 查询类型002/004-必输,合作方平台在聚富通入驻的商户或会员编号
        bizContent.setOrderCode("1234");//订单编号
        bizContent.setBeginDate("2021-01-01 23:59:59");//查询开始时间 查询类型001-必输
        bizContent.setEndDate("2021-01-01 23:59:59");//查询截止时间-最大跨度为1日 查询类型001-必输
        bizContent.setBegNum("1");//查询开始条数 查询类型001-必输 
        bizContent.setFetchNum("10");//查询条数，最大支持100，查询类型001-必输
        bizContent.setStatus("0");//支付状态，0-成功，1-失败，9-疑账,送空值查询所有状态，查询类型001-选输
        bizContent.setCardNo("123");//订单卡号
        request.setBizContent(bizContent);
        JftApiPayB2bpayWalletCpdetailQueryResponseV1 response;
        try {
            response = (JftApiPayB2bpayWalletCpdetailQueryResponseV1) client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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



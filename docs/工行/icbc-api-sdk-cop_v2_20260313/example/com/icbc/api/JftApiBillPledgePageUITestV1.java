package com.icbc.ndf.jft.b2bpay.bmsbill.api.impl;


import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftApiBillPledgePageUIRequestV1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JftApiBillPledgePageUITestV1 {

    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static final String APP_ID = "10000000000000005452";
    String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
    String host = "[需替换]工行API网关地址";
    String apiUrl= host+"/ui/jft/ui/bill/pledge/page/V1";

    @SuppressWarnings("unchecked")

    public void test_cop() throws Exception {

        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,
                IcbcConstants.ENCRYPT_TYPE_AES, AES_Key);


        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiBillPledgePageUIRequestV1.BillList bill = new JftApiBillPledgePageUIRequestV1.BillList();
        bill.setPackNo(""); //票包号
        bill.setRangeBgn(""); //起始子区间
        bill.setRangeEnd(""); //截止子区间
        bill.setPledgeAmt(""); //质押金额
        bill.setHolderAcctId(""); //持票人账号
        bill.setBusiSeq(""); //业务序列号

        List<JftApiBillPledgePageUIRequestV1.BillList> billList = new ArrayList<>();
        billList.add(bill);

        JftApiBillPledgePageUIRequestV1.JftApiBillPledgePageUIRequestV1Biz bizContent = new JftApiBillPledgePageUIRequestV1.JftApiBillPledgePageUIRequestV1Biz();
        bizContent.setAppId(APP_ID);//合作方编号
        bizContent.setOutVendorId("");//子商户编号
        bizContent.setChanSerialNo("");//渠道请求序列号
        bizContent.setNotifyfUrl("");//通知地址
        bizContent.setPledgeeRoleCode("");//质权人类型
        bizContent.setPledgeeAcctSvcr("");//质权人行号
        bizContent.setPledgeeIsIcbc("");//是否为工行客户标识
        bizContent.setPledgeeAcctId("");//质权人账号
        bizContent.setPledgeeAcctNm("");//质权人账号全称
        bizContent.setRemark(""); //备注
        bizContent.setBillList(billList);//质押票据列表

        JftApiBillPledgePageUIRequestV1 request = new JftApiBillPledgePageUIRequestV1();
        request.setServiceUrl(apiUrl);
        request.setBizContent(bizContent);
        String form = client.buildPostForm(request);
        System.out.println(form);
    }

}

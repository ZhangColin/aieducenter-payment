/**
 * 
 */
package com.icbc.api;

import com.icbc.api.crypt.AES;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemonlinePaymentRequestV1;
import com.icbc.api.request.BcssMemonlinePaymentRequestV1.BcssMemonlinePaymentGoodsInfoDto;
import com.icbc.api.response.BcssMemonlinePaymentResponseV1;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author kfzx-lizm
 *
 */
public class BcssMemonlinePaymentTestV1 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "xxxxxxxxxxxxxxxxx";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "xxxxxxxxxxxxxxxxx";
//    APP_ID
    protected static final String APP_ID = "xxxxxxxxxxxxxxxxxxxxxxx";
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    protected static final String STR_CODE = "UTF-8";

    public static void main(String[] args) throws Exception {
        BcssMemonlinePaymentTestV1 a = new BcssMemonlinePaymentTestV1();
        for (int i=0;i<1;i++){
            a.testCop();
        }

    }

    public void testCop() throws IcbcApiException {
        BcssMemonlinePaymentRequestV1 request = new BcssMemonlinePaymentRequestV1();
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/memonline/payment/V1");
        BcssMemonlinePaymentRequestV1.BcssMemonlinePaymentRequestBizV1 bizContent = new BcssMemonlinePaymentRequestV1.BcssMemonlinePaymentRequestBizV1();
        String clientTransNo = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        bizContent.setCorpId("xxxxxxxxxxxxxxxxxxxxxx");
        bizContent.setOrdersrc("3");
        bizContent.setIndustry("005");
        bizContent.setTrademode("1");
        bizContent.setClientTransNo(clientTransNo);
        bizContent.setPayAmt(new BigDecimal(3));
        bizContent.setGoodsType("001");
        bizContent.setOptionType("999");
        bizContent.setTimeStamp("2019-08-21 10:58:41");
        bizContent.setMerNo("xxxxxxxxxxxxxxxxxxxxxx");
        bizContent.setPayType("15");
        bizContent.setTotalAmt(new BigDecimal(3));
        bizContent.setMsgFlag("0");
        bizContent.setDataSrc("4");
        bizContent.setOtherData(null);
        String userInfo=construct(clientTransNo);
        bizContent.setUserInfo(userInfo);
        List<BcssMemonlinePaymentGoodsInfoDto> goods = new ArrayList<BcssMemonlinePaymentGoodsInfoDto>();
        BcssMemonlinePaymentGoodsInfoDto good1 = new BcssMemonlinePaymentGoodsInfoDto();
        good1.setGoodsId("201904230000001149");
        try {
            good1.setGoodsName(URLEncoder.encode("北方大馍",STR_CODE));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("BEI FAGN DA MO");
        }
        good1.setGoodsNum(new BigDecimal(1));
        good1.setGoodsAmt(new BigDecimal(1));
        good1.setGoodstype("001");
        try {
            good1.setGoodsName(URLEncoder.encode("特大",STR_CODE));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("TE DA");
        }
        good1.setGroupNo(null);
        good1.setPeriodid(null);
        good1.setTypeid(null);
        good1.setGoodsback(null);
        good1.setDistributeDate(null);
        goods.add(good1);
        BcssMemonlinePaymentGoodsInfoDto good2 = new BcssMemonlinePaymentGoodsInfoDto();
        good2.setGoodsId("201904230000001141");
        try {
            good2.setGoodsName(URLEncoder.encode("糯米发糕",STR_CODE));
        } catch (UnsupportedEncodingException e) {
            good2.setGoodsName("LAO MAIN MAN TOU");
        }
        good2.setGoodsNum(new BigDecimal(1));
        good2.setGoodsAmt(new BigDecimal(1));
        good2.setGoodstype("001");
        try {
            good2.setGoodsName(URLEncoder.encode("特大",STR_CODE));
        } catch (UnsupportedEncodingException e) {
            good2.setGoodsName("TE DA");
        }
        good2.setGroupNo(null);
        good2.setPeriodid(null);
        good2.setTypeid(null);
        good2.setGoodsback(null);
        good2.setDistributeDate(null);
        goods.add(good2);
        bizContent.setGoods(goods);
        request.setBizContent(bizContent);


        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemonlinePaymentResponseV1 response = client.execute(request);
        System.out.println(JSONObject.toJSONString(response));
    }

    public static String construct(String clientTransNo) throws IcbcApiException {
        String key = "xxxxxxxxxxxxxxxxxxxx";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dt", "8");
        jsonObject.put("clientTransNo", clientTransNo);
        jsonObject.put("mobile", "");
        jsonObject.put("staffno", "");
        jsonObject.put("name", "");
        jsonObject.put("custcode", "");
        jsonObject.put("custsort", "");
        jsonObject.put("personId", "");//312112185478410240
        jsonObject.put("memCardNo", "xxxxxx");//0000230000005586
        jsonObject.put("outCardNo","xx");
        String d= AES.aesEncrypt(jsonObject.toJSONString(),key);
        System.out.println(jsonObject.toJSONString());
        return d;
    }
}

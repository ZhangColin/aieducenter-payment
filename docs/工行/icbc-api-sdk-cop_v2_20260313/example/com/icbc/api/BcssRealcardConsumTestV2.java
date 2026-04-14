/**
 * 
 */
package com.icbc.api;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.BcssRealcardConsumRequestV2;
import com.icbc.api.request.BcssRealcardConsumRequestV2.BcssRealcardConsumRequestBizV2;
import com.icbc.api.response.BcssRealcardConsumResponseV2;

public class BcssRealcardConsumTestV2 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        BcssRealcardConsumTestV2 a = new BcssRealcardConsumTestV2();
        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
    	BcssRealcardConsumRequestV2 request = new BcssRealcardConsumRequestV2();
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/realcard/consum/V2");
        BcssRealcardConsumRequestBizV2 bizContent = new BcssRealcardConsumRequestBizV2();               
        bizContent.setCorpId("0***********3");
        bizContent.setOrdersrc("3");
        bizContent.setManufacturerId("L************1");
        bizContent.setTrademode("1");
        bizContent.setClientTransNo("2*******************1");
        bizContent.setPayAmt(new BigDecimal(1000));
        bizContent.setTimeStamp("2019-06-05 13:47:49");
        bizContent.setPayType("2");
        bizContent.setTotalAmt(new BigDecimal(1000));
        bizContent.setClientType("7");
        
        bizContent.setMerNo("");
        bizContent.setImeiNo("0*************2");
        bizContent.setGoodsType("002");
        bizContent.setIndustry("002");
        bizContent.setDataSrc("5");
        List<BcssRealcardConsumRequestV2.RealCardGoodsInfoDto> goods = new ArrayList<BcssRealcardConsumRequestV2.RealCardGoodsInfoDto>();
        BcssRealcardConsumRequestV2.RealCardGoodsInfoDto good1 = new BcssRealcardConsumRequestV2.RealCardGoodsInfoDto();
        good1.setGoodsId("2**********************1");
        try {
            good1.setGoodsName(URLEncoder.encode("北方大馍","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("BEI FAGN DA MO");
        }
        good1.setGoodsNum(new BigDecimal(1));
        good1.setGoodsAmt(new BigDecimal(500));
        good1.setGoodstype("001");
        try {
            good1.setGoodsName(URLEncoder.encode("特大","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("TE DA");
        }
        good1.setGroupNo(null);
        good1.setPeriodid(null);
        good1.setTypeid(null);
        good1.setGoodsback(null);
        good1.setDistributeDate(null);
        goods.add(good1);
        BcssRealcardConsumRequestV2.RealCardGoodsInfoDto good2 = new BcssRealcardConsumRequestV2.RealCardGoodsInfoDto();
        good2.setGoodsId("2**********************1");
        try {
            good2.setGoodsName(URLEncoder.encode("糯米发糕","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good2.setGoodsName("LAO MAIN MAN TOU");
        }
        good2.setGoodsNum(new BigDecimal(1));
        good2.setGoodsAmt(new BigDecimal(500));
        good2.setGoodstype("001");
        try {
            good2.setGoodsName(URLEncoder.encode("特大","UTF-8"));
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
        /*实体卡相关数据*/
        bizContent.setCardNo("0****************9");
        bizContent.setTerminalNo("0*********6");
        bizContent.setTradeTag("06");
        bizContent.setTerminalTradeNo("0*****A");
        bizContent.setTradeDate("2********4");
        bizContent.setTradeTime("1******5");
        bizContent.setTAC("B*********3");
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssRealcardConsumResponseV2 responseV2=client.execute(request);
    }
    
}

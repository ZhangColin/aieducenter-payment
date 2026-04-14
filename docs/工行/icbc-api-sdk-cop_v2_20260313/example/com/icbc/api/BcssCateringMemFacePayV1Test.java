package com.icbc.api;

import com.icbc.api.request.BcssCateringMemFacePayRequestV1;
import com.icbc.api.request.BcssCateringMemFacePayRequestV1.BcssCateringMemFacePayRequestBizV1;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BcssCateringMemFacePayV1Test {
    // 网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";
    protected static final String APP_ID = "XXXX";
    // 密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXX";
    // 加密类型和秘钥
    protected static final String encryptType = "xxxx";
    protected static final String encryptKey = "xxxx";

    public static void main(String[] args) throws IcbcApiException {
        BcssCateringMemFacePayRequestV1 request = new BcssCateringMemFacePayRequestV1();
        // 根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/consume/memface/pay/V1");
        BcssCateringMemFacePayRequestBizV1 bizContent = new BcssCateringMemFacePayRequestBizV1();
        bizContent.setCorpId("0***********3");
        bizContent.setOrdersrc("3");
        bizContent.setManufacturerId("V***********1");
        bizContent.setCouponNo("");
        bizContent.setIndustry("001");
        bizContent.setTrademode("1");
        bizContent.setClientTransNo("0*************************2");
        bizContent.setPayAmt(new BigDecimal(3));
        bizContent.setParam1("**********************");
        bizContent.setGoodsType("001");
        bizContent.setOptionType("");
        bizContent.setTimeStamp("2014-07-21 10:58:41");
        bizContent.setPayType("1");
        bizContent.setTotalAmt(new BigDecimal(3));
        bizContent.setClientType("7");
        bizContent.setMerNo("");
        bizContent.setImeiNo("0*************2");
        bizContent.setMsgFlag("0");
        bizContent.setDataSrc("4");
        bizContent.setOtherData(null);
        bizContent.setCouponAmt(null);
        List<BcssCateringMemFacePayRequestBizV1.BcssCateringMemFacePayGoodsInfoDto> goods = new ArrayList<>();
        BcssCateringMemFacePayRequestBizV1.BcssCateringMemFacePayGoodsInfoDto good1 = new BcssCateringMemFacePayRequestBizV1.BcssCateringMemFacePayGoodsInfoDto();
        good1.setGoodsId("2**********************9");
        try {
            good1.setGoodsName(URLEncoder.encode("北方大馍", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("BEI FAGN DA MO");
        }
        good1.setGoodsNum(new BigDecimal(1));
        good1.setGoodsAmt(new BigDecimal(1));
        good1.setGoodstype("001");
        good1.setGroupNo(null);
        good1.setPeriodid(null);
        good1.setTypeid(null);
        good1.setGoodsback(null);
        good1.setDistributeDate(null);
        goods.add(good1);
        BcssCateringMemFacePayRequestBizV1.BcssCateringMemFacePayGoodsInfoDto good2 = new BcssCateringMemFacePayRequestBizV1.BcssCateringMemFacePayGoodsInfoDto();
        good2.setGoodsId("2**********************1");
        try {
            good2.setGoodsName(URLEncoder.encode("糯米发糕", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good2.setGoodsName("LAO MAIN MAN TOU");
        }
        good2.setGoodsNum(new BigDecimal(1));
        good2.setGoodsAmt(new BigDecimal(1));
        good2.setGoodstype("001");
        good2.setGroupNo(null);
        good2.setPeriodid(null);
        good2.setTypeid(null);
        good2.setGoodsback(null);
        good2.setDistributeDate(null);
        goods.add(good2);
        bizContent.setGoods(goods);
        request.setBizContent(bizContent);
        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
                APIGW_PUBLIC_KEY);
        client.execute(request);
    }
}

package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssRealcardConsumRequestV3;
import com.icbc.api.response.BcssRealcardConsumResponseV3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author kfzx-ouhz
 */
public class BcssRealcardConsumTestV3 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";

    protected static final String SIGN_TYPE = "RSA2";


    public static void main(String[] args) {

        try {
            test_realcardPay();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void test_realcardPay() throws Exception {
        BcssRealcardConsumRequestV3 request = new BcssRealcardConsumRequestV3();
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/realcard/consum/V3");
        BcssRealcardConsumRequestV3.BcssRealcardConsumRequestBizV3 bizContent = new BcssRealcardConsumRequestV3.BcssRealcardConsumRequestBizV3();
        //商品
        List<BcssRealcardConsumRequestV3.GoodsInfoDto> goods = new ArrayList<>();
        BcssRealcardConsumRequestV3.GoodsInfoDto goodsInfo = new BcssRealcardConsumRequestV3.GoodsInfoDto();
        goodsInfo.setGoodsId("202107150000002056");
        goodsInfo.setGoodsName("%E5%9B%9E%E5%BD%920715");
        goodsInfo.setGoodsNum(BigDecimal.ONE);
        goodsInfo.setGoodsAmt(BigDecimal.ONE);
        goodsInfo.setGoodsback("");
        goodsInfo.setGoodstype("001");
        goodsInfo.setGroupNo("");
        goodsInfo.setDistributeDate("");
        goodsInfo.setTypeid("202107150000002053");
        goodsInfo.setPeriodid("202001080000000493");
        goods.add(goodsInfo);

        //其他JSON参数
        HashMap<String, String> otherDataMap = new HashMap<>(2);
        otherDataMap.put("setUser", "0000000007");

        bizContent.setCorpId("2000000067");
        bizContent.setManufacturerId("Vanstone01");
        bizContent.setImeiNo("8242C190571004");
        bizContent.setClientType("3");
        bizContent.setTimeStamp("2021-07-08 17:06:45");
        bizContent.setMerNo("");
        bizContent.setClientTransNo("20210720103527234");
        bizContent.setOutCardNo("odi2021");
        bizContent.setTotalAmt(new BigDecimal("2"));
        bizContent.setPayAmt(new BigDecimal("2"));
        bizContent.setPayType("10");
        bizContent.setCouponNo("");
        bizContent.setCouponAmt(BigDecimal.ZERO);
        bizContent.setOrdersrc("3");
        bizContent.setDataSrc("4");
        bizContent.setIndustry("5");
        bizContent.setTrademode("1");
        bizContent.setOtherData(JSONObject.toJSONString(otherDataMap));
        bizContent.setGoods(goods);

        request.setBizContent(bizContent);
        DefaultIcbcClient client = getIcbcClient();
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        BcssRealcardConsumResponseV3 response = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }

    /**
     * api
     *
     * @return
     */
    private static DefaultIcbcClient getIcbcClient() {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        return client;
    }

    /**
     * UI
     *
     * @return
     */
    private static UiIcbcClient getUiClinet() {
        UiIcbcClient client = new UiIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        return client;
    }
}


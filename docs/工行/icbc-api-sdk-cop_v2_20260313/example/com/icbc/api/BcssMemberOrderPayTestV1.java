package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BcssMemberOrderPayRequestV1;
import com.icbc.api.utils.IcbcEncrypt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class BcssMemberOrderPayTestV1 {

    protected static final String MY_PRIVATE_KEY = "xxxx";

    protected static final String APIGW_PUBLIC_KEY = "xxxx";

    protected static final String APP_ID = "xxxxx";
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = "http://apip:8081";

    private final static String SIGN_TYPE = "RSA2";
    public final static String URL = "/ui/bcss/member/order/pay/V1";

    public final static String MEITUAN_URL = "http://apip:8081";


    private static final Log logger = LogFactory.getLog(BcssMemberOrderPayTestV1.class);



    public static void main(String[] args) throws Exception {
        testCop();
    }

    public static void testCop() throws Exception {

        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        BcssMemberOrderPayRequestV1 request = new BcssMemberOrderPayRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssMemberOrderPayRequestV1.BcssMemberOrderPayRequestV1Biz bizContent = new BcssMemberOrderPayRequestV1.BcssMemberOrderPayRequestV1Biz();

        //6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        bizContent.setCorpId("2000000067");
        bizContent.setClientTransNo("20000000672025060215422401");
        bizContent.setTimeStamp("2025-05-22 10:10:10");
        bizContent.setOrderDate("20250623101111");
        bizContent.setMerOrderNo("1111111111323");
        bizContent.setMerTradeNo("1111111111323");
        bizContent.setTotalAmt("2");
        bizContent.setPayAmt("2");
        bizContent.setServiceFeeAmt("0");
        bizContent.setDataSrc("16");
        bizContent.setThirdNo("87765");
//        bizContent.setAttach("{'sfTotalAmt':'0'}");
        bizContent.setUserType("4C");
//        bizContent.setUserInfo(parseByet2HexStr(aesEncryptToBytes("698419363090452480","cf030c6b275b56f2")));
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        bizContent.setUserInfo(IcbcEncrypt.encryptContent("{'phone':'15208198057','timestamp':'2028-01-01 11:11:11'}", "AES", "645r6z1azEItiR+ghwF4+g==", "UTF-8"));

            bizContent.setNotifyUrl(MEITUAN_URL+"/api/sqt/open/standardThird/v2/pay/callback?tradeModel=ORDER");
        bizContent.setReturnUrl("");
        bizContent.setDetailUrl("");
        bizContent.setOrderInterval("7200");

        /*ArrayList<BcssMemberOrderPayRequestV1.GoodsInfoDto> orderDetail = new ArrayList<>();
        BcssMemberOrderPayRequestV1.GoodsInfoDto goodsInfoDto = new BcssMemberOrderPayRequestV1.GoodsInfoDto();
//        goodsInfoDto.setDetailNo("11111111");
        goodsInfoDto.setGoodsNo("1123123");
        goodsInfoDto.setGoodsName("测试1");
        goodsInfoDto.setGoodsAmt("2");
        goodsInfoDto.setGoodsNum("2");
        orderDetail.add(goodsInfoDto);

        BcssMemberOrderPayRequestV1.GoodsInfoDto goodsInfoDto1 = new BcssMemberOrderPayRequestV1.GoodsInfoDto();
//        goodsInfoDto.setDetailNo("11111111");
        goodsInfoDto1.setGoodsNo("51536236");
        goodsInfoDto1.setGoodsName("测试2");
        goodsInfoDto1.setGoodsAmt("0");
        goodsInfoDto1.setGoodsNum("2");
        orderDetail.add(goodsInfoDto1);

        BcssMemberOrderPayRequestV1.GoodsInfoDto goodsInfoDto2 = new BcssMemberOrderPayRequestV1.GoodsInfoDto();
//        goodsInfoDto.setDetailNo("11111111");
        goodsInfoDto2.setGoodsNo("12512566");
        goodsInfoDto2.setGoodsName("测试3");
        goodsInfoDto2.setGoodsAmt("1");
        goodsInfoDto2.setGoodsNum("3");
        orderDetail.add(goodsInfoDto2);*/
//        bizContent.setOrderDetail(orderDetail);
        String res=URLEncoder.encode(JSON.toJSONString(bizContent), "utf-8");
//        System.out.println(res);
        request.setBizContent(bizContent);

        try {

            String s = client.buildPostForm(request);
//            System.out.println(s);
        } catch (IcbcApiException e) {
            logger.error(e);
        }
    }
    public static byte[] aesEncryptToBytes(String content,String encryptKey) throws Exception{
        //加密器
        SecretKey secretkey = new SecretKeySpec(encryptKey.getBytes("utf-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //密钥
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);
        return cipher.doFinal(content.getBytes("utf-8"));

    }
    public static String parseByet2HexStr(byte buf[]){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if(hex.length() == 1){
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}

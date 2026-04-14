package com.icbc.api;

import com.icbc.api.request.BusinessopThirdpartySubmitorderRequestV1;


import java.util.ArrayList;
import java.util.List;


//B2B商城垂直电商第三方订单提交页（页面）

/**
 * Servlet implementation class SubmitOrderServlet
 */
public class BusinessopThirdpartySubmitorderTestV1 {


    public static void main(String[] args) {

        try {
            String APP_ID = "";//测试环境 联系工行测试人员
            String MY_PRIVATE_KEY = "";//测试环境 联系工行测试人员
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            BusinessopThirdpartySubmitorderRequestV1 request = new BusinessopThirdpartySubmitorderRequestV1();
            request.setServiceUrl("http://测试apipip和端口/ui/businessop/thirdparty/submitorder/V1");//测试环境 联系工行测试人员

            BusinessopThirdpartySubmitorderRequestV1.BusinessopThirdpartySubmitorderRequestV1Biz bizContent = new BusinessopThirdpartySubmitorderRequestV1.BusinessopThirdpartySubmitorderRequestV1Biz();
            BusinessopThirdpartySubmitorderRequestV1.ThirdPartyOrderRequestDTO  thirdPartyOrderRequestDTO= new BusinessopThirdpartySubmitorderRequestV1.ThirdPartyOrderRequestDTO();

            BusinessopThirdpartySubmitorderRequestV1.StoreVO store = new BusinessopThirdpartySubmitorderRequestV1.StoreVO();
            store.setMercId("10001850");
            store.setStoreId("010093");
            store.setStoreName("测试商铺");

            BusinessopThirdpartySubmitorderRequestV1.Prods prod = new BusinessopThirdpartySubmitorderRequestV1.Prods();
            prod.setProdName("垂直电商商品测试");
            prod.setSkuId("00000000000000004211");
            prod.setProdId("B000014671");
            prod.setPrice("123.50");

            List<BusinessopThirdpartySubmitorderRequestV1.Prods> prods = new ArrayList<BusinessopThirdpartySubmitorderRequestV1.Prods>();
            prods.add(prod);
            store.setProds(prods);

            thirdPartyOrderRequestDTO.setThirdPartyOrderId("37858562345435");
            thirdPartyOrderRequestDTO.setThirdPartyParentOrderId("37858562345435");
            thirdPartyOrderRequestDTO.setOutUserId("22");
            thirdPartyOrderRequestDTO.setOrderType("15");
            thirdPartyOrderRequestDTO.setOrderInvalidTime("30");
            thirdPartyOrderRequestDTO.setOrderPayAmout("123.50");
            thirdPartyOrderRequestDTO.setOrderPrice("123.50");
            thirdPartyOrderRequestDTO.setParentOrderPrice("123.50");
            thirdPartyOrderRequestDTO.setOrderMerchantRemark("备注");
            thirdPartyOrderRequestDTO.setNoticeUrl("");//第三方url
            thirdPartyOrderRequestDTO.setPayBackUrl("");//第三方url
            thirdPartyOrderRequestDTO.setPayFailUrl("");//第三方url
            thirdPartyOrderRequestDTO.setPcOrderUrl("");//第三方url
            thirdPartyOrderRequestDTO.setMobOrderUrl("");//第三方url
            thirdPartyOrderRequestDTO.setRemark1("");
            thirdPartyOrderRequestDTO.setRemark2("");
            thirdPartyOrderRequestDTO.setRemark3("");
            thirdPartyOrderRequestDTO.setStoreVO(store);


            List<BusinessopThirdpartySubmitorderRequestV1.ThirdPartyOrderRequestDTO> list = new ArrayList<BusinessopThirdpartySubmitorderRequestV1.ThirdPartyOrderRequestDTO>();

            list.add(thirdPartyOrderRequestDTO);
            bizContent.setThirdPartyOrderRequestDTO(list);
            bizContent.setAppId(APP_ID);
            request.setBizContent(bizContent);

            String message = "";
            message = client.buildPostForm(request);
            System.out.println(message);


        } catch (IcbcApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

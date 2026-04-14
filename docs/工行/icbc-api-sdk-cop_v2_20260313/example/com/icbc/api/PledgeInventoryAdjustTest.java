package com.icbc.api;


import com.icbc.api.request.PledgeInventoryAdjustRequestV1;
import com.icbc.api.response.PledgeInventoryAdjustResponseV1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author kfzx-huangl2
 * @Date 2025/7/10
 * @Description TODO
 **/
public class PledgeInventoryAdjustTest {

    public static void main(String[] args) {
        /**
         * 	/investment/custody/cisc/issuercodeqry/V1
         */
        String APP_ID = "xxx";
        String APIGW_PUBLIC_KEY = "xxx";
        String PRI_KEY = "xxx";
        String URL = "https://gw.open.icbc.com.cn/api/pledge/inventory/adjust/V1";
        try {
            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
            PledgeInventoryAdjustRequestV1 request = new PledgeInventoryAdjustRequestV1();
            PledgeInventoryAdjustRequestV1.PledgeInventoryAdjustRequestV1Biz bizContent = new PledgeInventoryAdjustRequestV1.PledgeInventoryAdjustRequestV1Biz();
            PledgeInventoryAdjustRequestV1.PublicRequest publicRequest = new PledgeInventoryAdjustRequestV1.PublicRequest();

            publicRequest.setAppId("F-GCCM");
            publicRequest.setServiceAlias("Gccm_Pledge_Inventory_Adjust_V1.0");
            publicRequest.setTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
            publicRequest.setAppSeqNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

            PledgeInventoryAdjustRequestV1.PrivateRequest privateRequest = new PledgeInventoryAdjustRequestV1.PrivateRequest();
            List<PledgeInventoryAdjustRequestV1.Goods> goodList = new ArrayList<>();
            PledgeInventoryAdjustRequestV1.Goods good = new PledgeInventoryAdjustRequestV1.Goods();
            good.setGrn("1231231");
            goodList.add(good);

            List<PledgeInventoryAdjustRequestV1.WareHouse> wareHouseList = new ArrayList<>();
            PledgeInventoryAdjustRequestV1.WareHouse wareHouse = new PledgeInventoryAdjustRequestV1.WareHouse();
            wareHouse.setGrn("1231231");
            wareHouseList.add(wareHouse);

            privateRequest.setSerialNo("428767198408147542");
            privateRequest.setGoodsList(goodList);
            privateRequest.setWareHouseList(wareHouseList);

            bizContent.setPrivateRequest(privateRequest);
            bizContent.setPublicRequest(publicRequest);
            request.setServiceUrl(URL);
            request.setBizContent(bizContent);

            PledgeInventoryAdjustResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
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

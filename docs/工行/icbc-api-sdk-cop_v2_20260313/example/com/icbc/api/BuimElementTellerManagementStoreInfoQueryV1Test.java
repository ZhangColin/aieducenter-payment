package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BuimElementTellerManagementStoreInfoQueryRequestV1;
import com.icbc.api.response.BuimElementTellerManagementStoreInfoQueryResponseV1;

public class BuimElementTellerManagementStoreInfoQueryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BuimElementTellerManagementStoreInfoQueryRequestV1 request = new BuimElementTellerManagementStoreInfoQueryRequestV1();
        BuimElementTellerManagementStoreInfoQueryRequestV1.BuimElementTellerManagementStoreInfoQueryRequestV1Biz bizContent = new BuimElementTellerManagementStoreInfoQueryRequestV1.BuimElementTellerManagementStoreInfoQueryRequestV1Biz();

        BuimElementTellerManagementStoreInfoQueryRequestV1.ChanCommV10 chanCommV10 = new BuimElementTellerManagementStoreInfoQueryRequestV1.ChanCommV10();
        BuimElementTellerManagementStoreInfoQueryRequestV1.InfoCommV10 infoCommV10 = new BuimElementTellerManagementStoreInfoQueryRequestV1.InfoCommV10();
        BuimElementTellerManagementStoreInfoQueryRequestV1.TranComV10 tranComV10 = new BuimElementTellerManagementStoreInfoQueryRequestV1.TranComV10();
        BuimElementTellerManagementStoreInfoQueryRequestV1.InqCommV10 inqCommV10 = new BuimElementTellerManagementStoreInfoQueryRequestV1.InqCommV10();
        BuimElementTellerManagementStoreInfoQueryRequestV1.Row row = new BuimElementTellerManagementStoreInfoQueryRequestV1.Row();
        BuimElementTellerManagementStoreInfoQueryRequestV1.Cond cond = new BuimElementTellerManagementStoreInfoQueryRequestV1.Cond();

        chanCommV10.setChantype("101");
        chanCommV10.setOapp("F-SOCT");
        chanCommV10.setSerialno("150202005162835750018916647");
        chanCommV10.setMserialn("150202005162835750018916647");

        infoCommV10.setTrxtype("1");
        infoCommV10.setTrxcode("1325");
        infoCommV10.setZoneno("00200");
        infoCommV10.setBrno("00035");
        infoCommV10.setTellerno("93188");
        infoCommV10.setAuthcode("");
        infoCommV10.setAuthlevel("");
        infoCommV10.setAuthcardno("");
        infoCommV10.setAuthpass("");
        infoCommV10.setAuthdutyno("");

        tranComV10.setField5("1325");
        tranComV10.setField7("");
        tranComV10.setField8("");
        tranComV10.setTrxtype("1");
        tranComV10.setOperflag("1");

        inqCommV10.setInitflag("1");
        inqCommV10.setRowReq("5");

        row.setZoneno("0");
        row.setVouhtype("0");
        row.setVoubgno("0");
        row.setPreaceno("");
        row.setAcenolen("0");

        cond.setOperflag("2");
        cond.setBrno("00035");
        cond.setTellerno("93188");
        cond.setVouhtype("000000108");
        cond.setVouno("");
        cond.setStatus("12");
        cond.setPreaceno("");
        cond.setAcenolen("0");

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setTranComV10(tranComV10);
        bizContent.setInqCommV10(inqCommV10);
        bizContent.setRow(row);
        bizContent.setCond(cond);
        request.setBizContent(bizContent);

        try {
            BuimElementTellerManagementStoreInfoQueryResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

package icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import icbc.api.request.BuimHgPreciousMetalStockSHQueryRequestV1;
import icbc.api.response.BuimHgPreciousMetalStockSHQueryResponseV1;
import icbc.api.response.BuimPreciousMetalAllocationInfoQueryResponseV1;

public class BuimHgPreciousMetalStockSHQueryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BuimHgPreciousMetalStockSHQueryRequestV1 request = new BuimHgPreciousMetalStockSHQueryRequestV1();
        BuimHgPreciousMetalStockSHQueryRequestV1.BuimHgPreciousMetalStockSHQueryRequestV1Biz bizContent = new BuimHgPreciousMetalStockSHQueryRequestV1.BuimHgPreciousMetalStockSHQueryRequestV1Biz();

        BuimHgPreciousMetalStockSHQueryRequestV1.ChanCommV10 chanCommV10 = new BuimHgPreciousMetalStockSHQueryRequestV1.ChanCommV10();
        BuimHgPreciousMetalStockSHQueryRequestV1.InfoCommV10 infoCommV10 = new BuimHgPreciousMetalStockSHQueryRequestV1.InfoCommV10();
        BuimHgPreciousMetalStockSHQueryRequestV1.TranComV10 tranComV10 = new BuimHgPreciousMetalStockSHQueryRequestV1.TranComV10();
        BuimHgPreciousMetalStockSHQueryRequestV1.InqCommV10 inqCommV10 = new BuimHgPreciousMetalStockSHQueryRequestV1.InqCommV10();
        BuimHgPreciousMetalStockSHQueryRequestV1.Row row = new BuimHgPreciousMetalStockSHQueryRequestV1.Row();
        BuimHgPreciousMetalStockSHQueryRequestV1.Icom cond = new BuimHgPreciousMetalStockSHQueryRequestV1.Icom();

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

        tranComV10.setTrxsqns("1325");

        inqCommV10.setInitflag("1");
        inqCommV10.setRowReq("5");

        row.setOrderno("0");

        cond.setOrderno("12");

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setTranComV10(tranComV10);
        bizContent.setInqCommV10(inqCommV10);
        bizContent.setRow(row);
        bizContent.setIcom(cond);
        request.setBizContent(bizContent);

        BuimHgPreciousMetalStockSHQueryResponseV1 response = client.execute(request);
        if (response.getReturnCode() == 0) {
//            System.out.println("ReturnCode:" + response.getReturnCode());
//            System.out.println("response:" + JSON.toJSONString(response));
        } else {
//            System.out.println("response:" + JSON.toJSONString(response));
//            System.out.println("ReturnCode:" + response.getReturnCode());
//            System.out.println("ReturnMsg:" + response.getReturnMsg());
        }
    }
}

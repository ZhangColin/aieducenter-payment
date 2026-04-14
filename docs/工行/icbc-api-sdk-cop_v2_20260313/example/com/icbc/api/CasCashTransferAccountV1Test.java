package icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import icbc.api.request.CasCashTransferAccountRequestV1;
import icbc.api.response.CasCashTransferAccountResponseV1;

public class CasCashTransferAccountV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        CasCashTransferAccountRequestV1 request = new CasCashTransferAccountRequestV1();
        CasCashTransferAccountRequestV1.BuimPreciousMetalAllocationInfoQueryRequestV1Biz bizContent = new CasCashTransferAccountRequestV1.CasCashTransferAccountRequestV1Biz();

        CasCashTransferAccountRequestV1.ChanCommV10 chanCommV10 = new CasCashTransferAccountRequestV1.ChanCommV10();
        CasCashTransferAccountRequestV1.InfoCommV10 infoCommV10 = new CasCashTransferAccountRequestV1.InfoCommV10();
        CasCashTransferAccountRequestV1.TranComV10 tranComV10 = new CasCashTransferAccountRequestV1.TranComV10();
        CasCashTransferAccountRequestV1.Icom icom = new CasCashTransferAccountRequestV1.Icom();

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

        tranComV10.setCkdate_f("1325");

        Icom.funtype("1");

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setTranComV10(tranComV10);
        bizContent.setIcom(icom);
        request.setBizContent(bizContent);

        CasCashTransferAccountResponseV1 response = client.execute(request);
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

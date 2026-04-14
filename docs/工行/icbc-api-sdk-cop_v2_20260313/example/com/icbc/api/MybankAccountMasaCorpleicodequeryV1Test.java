package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankAccountMasaCorpleicodequeryRequestV1;
import com.icbc.api.response.MybankAccountMasaCorpleicodequeryResponseV1;

import java.util.ArrayList;

public class MybankAccountMasaCorpleicodequeryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankAccountMasaCorpleicodequeryRequestV1 request = new MybankAccountMasaCorpleicodequeryRequestV1();

        MybankAccountMasaCorpleicodequeryRequestV1.MybankAccountMasaCorpleicodequeryRequestV1Biz bizContent = new MybankAccountMasaCorpleicodequeryRequestV1.MybankAccountMasaCorpleicodequeryRequestV1Biz();

        MybankAccountMasaCorpleicodequeryRequestV1.ChanCommV10 chanCommV10 = new MybankAccountMasaCorpleicodequeryRequestV1.ChanCommV10();

        MybankAccountMasaCorpleicodequeryRequestV1.InfoCommV10 infoCommV10 = new MybankAccountMasaCorpleicodequeryRequestV1.InfoCommV10();

        MybankAccountMasaCorpleicodequeryRequestV1.MasaCtrlComm masaCtrlComm = new MybankAccountMasaCorpleicodequeryRequestV1.MasaCtrlComm();

        MybankAccountMasaCorpleicodequeryRequestV1.PrivateComm privateComm = new MybankAccountMasaCorpleicodequeryRequestV1.PrivateComm();

        chanCommV10.setChantype("0");
        chanCommV10.setChanlno("0");
        chanCommV10.setMac("0");
        chanCommV10.setIp("0");
        chanCommV10.setTermid("0");
        chanCommV10.setOapp("0");
        chanCommV10.setSevlevel("0");
        chanCommV10.setSerialno("0");
        chanCommV10.setMserialn("0");
        chanCommV10.setOserialn("0");
        chanCommV10.setPreflag("0");
        chanCommV10.setProdid("0");
        chanCommV10.setCobprodid("0");
        chanCommV10.setCino("0");
        chanCommV10.setTrxsqnb("0");
        chanCommV10.setDisrecflag("0");
        chanCommV10.setComrolflag("0");
        chanCommV10.setPaperlessflag("0");
        chanCommV10.setField1("0");
        chanCommV10.setTermtype("0");
        chanCommV10.setLaunbankzoneno("0");
        chanCommV10.setFingerprinflag("0");
        chanCommV10.setDistransinfo("0");

        infoCommV10.setTrxtype("0");
        infoCommV10.setTrxcode("0");
        infoCommV10.setZoneno("0");
        infoCommV10.setBrno("0");
        infoCommV10.setTellerno("0");
        infoCommV10.setWorkdate("0");
        infoCommV10.setWorktime("0");
        infoCommV10.setRevtranf("0");

        masaCtrlComm.setScenecode("0");
        masaCtrlComm.setTrancode("0");
        masaCtrlComm.setOperflag("0");
        masaCtrlComm.setCino("0");

        privateComm.setLei_code("0");

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setMasaCtrlComm(masaCtrlComm);
        bizContent.setPrivateComm(privateComm);

        request.setBizContent(bizContent);

        try {
            MybankAccountMasaCorpleicodequeryResponseV1 response = client.execute(request);
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

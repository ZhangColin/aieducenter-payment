package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankAccountMasaCorpleicodeaddRequestV1;
import com.icbc.api.response.MybankAccountMasaCorpleicodeaddResponseV1;

import java.util.ArrayList;

public class MybankAccountMasaCorpleicodeaddV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankAccountMasaCorpleicodeaddRequestV1 request = new MybankAccountMasaCorpleicodeaddRequestV1();

        MybankAccountMasaCorpleicodeaddRequestV1.MybankAccountMasaCorpleicodeaddRequestV1Biz bizContent = new MybankAccountMasaCorpleicodeaddRequestV1.MybankAccountMasaCorpleicodeaddRequestV1Biz();

        MybankAccountMasaCorpleicodeaddRequestV1.ChanCommV10 chanCommV10 = new MybankAccountMasaCorpleicodeaddRequestV1.ChanCommV10();

        MybankAccountMasaCorpleicodeaddRequestV1.InfoCommV10 infoCommV10 = new MybankAccountMasaCorpleicodeaddRequestV1.InfoCommV10();

        MybankAccountMasaCorpleicodeaddRequestV1.MasaCtrlComm masaCtrlComm = new MybankAccountMasaCorpleicodeaddRequestV1.MasaCtrlComm();

        MybankAccountMasaCorpleicodeaddRequestV1.PrivateComm privateComm = new MybankAccountMasaCorpleicodeaddRequestV1.PrivateComm();

        MybankAccountMasaCorpleicodeaddRequestV1.Basic basic = new MybankAccountMasaCorpleicodeaddRequestV1.Basic();
        MybankAccountMasaCorpleicodeaddRequestV1.LegalForm legalForm = new MybankAccountMasaCorpleicodeaddRequestV1.LegalForm();
        MybankAccountMasaCorpleicodeaddRequestV1.RegAddr regAddr = new MybankAccountMasaCorpleicodeaddRequestV1.RegAddr();
        MybankAccountMasaCorpleicodeaddRequestV1.RegAddrOther regAddrOther = new MybankAccountMasaCorpleicodeaddRequestV1.RegAddrOther();
        MybankAccountMasaCorpleicodeaddRequestV1.BusAddr BusAddr = new MybankAccountMasaCorpleicodeaddRequestV1.BusAddr();
        MybankAccountMasaCorpleicodeaddRequestV1.BusAddrOther busAddrOther = new MybankAccountMasaCorpleicodeaddRequestV1.BusAddrOther();
        MybankAccountMasaCorpleicodeaddRequestV1.RaInfo raInfo = new MybankAccountMasaCorpleicodeaddRequestV1.RaInfo();
        MybankAccountMasaCorpleicodeaddRequestV1.UserInfo userInfo = new MybankAccountMasaCorpleicodeaddRequestV1.UserInfo();

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

        basic.setLegal_name("0");
        basic.setLegal_name_language("0");
        basic.setEntity_legal_category("0");
        basic.setCorporation("0");
        basic.setHead_lei("0");
        basic.setEmail("0");
        basic.setBmscode("0");
        basic.setOther_legal_name("0");
        basic.setOther_legal_name_language("0");
        basic.setEntity_creation_date("0");

        legalForm.setLegal_form_code("0");
        legalForm.setOther_legal_form_name("0");
        legalForm.setLegal_form_country("0");
        legalForm.setLegal_form_language("0");

        regAddr.setCountry("0");
        regAddr.setProvince("0");
        regAddr.setCity("0");
        regAddr.setAddress("0");
        regAddr.setPost_code("0");
        regAddr.setLanguage("0");

        regAddrOther.setCountry("0");
        regAddrOther.setProvince("0");
        regAddrOther.setCity("0");
        regAddrOther.setAddress("0");
        regAddrOther.setPost_code("0");
        regAddrOther.setLanguage("0");

        busAddr.setCountry("0");
        busAddr.setProvince("0");
        busAddr.setCity("0");
        busAddr.setAddress("0");
        busAddr.setPost_code("0");
        busAddr.setLanguage("0");

        busAddrOther.setCountry("0");
        busAddrOther.setProvince("0");
        busAddrOther.setCity("0");
        busAddrOther.setAddress("0");
        busAddrOther.setPost_code("0");
        busAddrOther.setLanguage("0");

        raInfo.setCredit_code("0");
        raInfo.setRa_code("0");
        raInfo.setRegister_name("0");

        userInfo.setContact_name("0");
        userInfo.setContact_department("0");
        userInfo.setContact_mail("0");
        userInfo.setContact_tel("0");

        privateComm.setBasic(basic);
        privateComm.setLegalForm(legalForm);
        privateComm.setRegAddr(regAddr);
        privateComm.setRegAddrOther(regAddrOther);
        privateComm.setBusAddr(busAddr);
        privateComm.setBusAddrOther(busAddrOther);
        privateComm.setRaInfo(raInfo);
        privateComm.setUserInfo(userInfo);

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setMasaCtrlComm(masaCtrlComm);
        bizContent.setPrivateComm(privateComm);

        request.setBizContent(bizContent);

        try {
            MybankAccountMasaCorpleicodeaddResponseV1 response = client.execute(request);
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

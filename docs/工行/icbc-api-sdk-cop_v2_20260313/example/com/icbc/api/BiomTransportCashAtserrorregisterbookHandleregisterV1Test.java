package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashAtserrorregisterbookHandleregisterRequestV1;
import com.icbc.api.response.BiomTransportCashAtserrorregisterbookHandleregisterResponseV1;

public class BiomTransportCashAtserrorregisterbookHandleregisterV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,"UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashAtserrorregisterbookHandleregisterRequestV1 request = 
        new BiomTransportCashAtserrorregisterbookHandleregisterRequestV1();

        BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.BiomTransportCashAtserrorregisterbookHandleregisterRequestV1Biz bizContent =
        new BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.BiomTransportCashAtserrorregisterbookHandleregisterRequestV1Biz();

        BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.PublicReqBean publicReqBean = 
        new BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.PublicReqBean();

        BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.NewReqBean newReqBean= 
        new BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.NewReqBean();

        BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.ChanCommV10ReqBean chanCommV10ReqBean = 
        new BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.ChanCommV10ReqBean();

        BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.IcomReqBean icomReqBean = 
        new BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.IcomReqBean();

        BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.InfoCommV10ReqBean infoCommV10ReqBean = 
        new BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.InfoCommV10ReqBean();

        BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.AppStatV10ReqBean appStatV10ReqBean = 
        new BiomTransportCashAtserrorregisterbookHandleregisterRequestV1.AppStatV10ReqBean();

        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-SOCT");
        publicReqBean.setZoneno("00200");
        publicReqBean.setOtellerno("05959");


        newReqBean.setAmount("8");
        newReqBean.setBrno("00200");
        newReqBean.setBusno("");
        newReqBean.setCurrtype("001");
        newReqBean.setErrdate("2024-05-09");
        newReqBean.setErrmach("");
        newReqBean.setErrman("5959徐华");
        newReqBean.setErrname("柜台长款");
        newReqBean.setErrsum("零星长款");
        newReqBean.setErrtype("1");
        newReqBean.setField1("90000000000000000");
        newReqBean.setField2("");
        newReqBean.setField3("110");
        newReqBean.setField4("");
        newReqBean.setField5("5959柜员柜面零星长款");
        newReqBean.setField6("");
        newReqBean.setNotes("");
        newReqBean.setTimestmp("");
        newReqBean.setZoneno("00200");
        newReqBean.setZwflag("");

        chanCommV10ReqBean.setAuthbrno("00601");
        chanCommV10ReqBean.setAuthssi("");
        chanCommV10ReqBean.setAuthtype("2");
        chanCommV10ReqBean.setAuthzoneno("00101");
        chanCommV10ReqBean.setChanlno("0");
        chanCommV10ReqBean.setChantype("101");
        chanCommV10ReqBean.setCobprodid("");
        chanCommV10ReqBean.setComrolflag("");
        chanCommV10ReqBean.setDisrecflag("");
        chanCommV10ReqBean.setDistransinfo("");
        chanCommV10ReqBean.setField1("");
        chanCommV10ReqBean.setField5("2653");
        chanCommV10ReqBean.setField7("");
        chanCommV10ReqBean.setField8("");
        chanCommV10ReqBean.setFingerprinflag("11542");
        chanCommV10ReqBean.setIp("ip:port");
        chanCommV10ReqBean.setLaunbankzoneno("");
        chanCommV10ReqBean.setMac("");
        chanCommV10ReqBean.setMserialn("");
        chanCommV10ReqBean.setOapp("F-SOCT");
        chanCommV10ReqBean.setOserialn("");
        chanCommV10ReqBean.setPaperlessflag("");
        chanCommV10ReqBean.setPreflag("0");
        chanCommV10ReqBean.setProdid("");
        chanCommV10ReqBean.setSerialno("010140004130005959216668033");
        chanCommV10ReqBean.setSevlevel("0");
        chanCommV10ReqBean.setTermtype("01000");
        chanCommV10ReqBean.setTimestamp("");
        chanCommV10ReqBean.setTrxnocheckflag("10");
        chanCommV10ReqBean.setTrxsqnb("1");

        icomReqBean.setBzflag("");
        icomReqBean.setJzchaccno("");

        infoCommV10ReqBean.setAmount("8");
        infoCommV10ReqBean.setAuthamount("8");
        infoCommV10ReqBean.setAuthcardno("");
        infoCommV10ReqBean.setAuthcode("20125");
        infoCommV10ReqBean.setAuthdutyno("00000");
        infoCommV10ReqBean.setAuthlevel("0");
        infoCommV10ReqBean.setAuthpass("");
        infoCommV10ReqBean.setAuthtellerno("02178");
        infoCommV10ReqBean.setBrno("00200");
        infoCommV10ReqBean.setCurrtype("001");
        infoCommV10ReqBean.setPretelno("0");
        infoCommV10ReqBean.setRevtranf("0");
        infoCommV10ReqBean.setSafepara("98576122");
        infoCommV10ReqBean.setServiceface("001");
        infoCommV10ReqBean.setTellerno("05959");
        infoCommV10ReqBean.setTermid("80.147:gui00001");
        infoCommV10ReqBean.setTrxcode("2653");
        infoCommV10ReqBean.setTrxsqnb("55015");
        infoCommV10ReqBean.setTrxtype("2");
        infoCommV10ReqBean.setWorkdate("2024-05-09");
        infoCommV10ReqBean.setZoneno("00200");

        appStatV10ReqBean.setTransok("0");
        appStatV10ReqBean.setTsf_stat("9");
        appStatV10ReqBean.setErr_no("0000");

        bizContent.setNewReqBean(newReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        bizContent.setChanCommV10ReqBean(chanCommV10ReqBean);
        bizContent.setIcomReqBean(icomReqBean);
        bizContent.setInfoCommV10ReqBean(infoCommV10ReqBean);
        bizContent.setAppStatV10ReqBean(appStatV10ReqBean);

        request.setBizContent(bizContent);


        try {
            BiomTransportCashAtserrorregisterbookHandleregisterResponseV1 response = client.execute(request);
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

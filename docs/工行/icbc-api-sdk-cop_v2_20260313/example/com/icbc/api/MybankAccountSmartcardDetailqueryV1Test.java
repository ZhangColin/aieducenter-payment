package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankAccountSmartcardDetailqueryRequestV1;
import com.icbc.api.request.MybankAccountSmartcardDetailqueryRequestV1.InqWork;
import com.icbc.api.response.MybankAccountSmartcardDetailqueryResponseV1;

public class MybankAccountSmartcardDetailqueryVTest {

	public static void main(String[] args) {
        // ʾ������˵������ʹ��F-APIP���µ���������SDK���п�����ǩ������ΪRSA2ʱ���贫��appid��˽Կ�����ع�Կ��ǩ������ʹ�ö�ֵIcbcConstants.SIGN_TYPE_RSA2����������ʹ��ȱʡֵ

        //1����Կ����֤��ʽ����Կ��F-APIPƽ̨�Ǽǣ��˴�Ϊ˽Կ
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQClDtj4WWl85bLkdgOc5yX9x/2G63SePhiIKjRjQkSaKk9T2fNOIHmmqPPFQHarUIHjd0bnUhcuNuBnSKFj4kkFa5UoKEdhq1C44pqSayI2k0MJZlr6XMObtWvxqO7kwsA1oX4Xbyyv87IYvji/EzRT9nIZSSYVQoNloHxvuRE//q5t9mUbKabnJM/cPa3BeoHPFpWwzEL0mPKfRMXqU2HGOjVnGn1RX2NOQpkfe0CPYDjMDR+PHNxNfGUA+1asWITuxEpNMYcgTOp8vsZjCteNFPItJuPQkAJVCQDxVY6wAVczUBRHR74XYmWcG0v1JOhwCiz1YYTOajZ/iLVSeK0VAgMBAAECggEBAILQNZz+H+Bgm1r9PPfJKg7rxT58tTwEsqSX7lUMXBpileQ6w92TV1aCaFBYOrSjZY3FJmiGYuC5lTfGFEJ5QK4PCXN+GLSLdbpRvXd1ePQP2f1FJa1uf4iUTCyGWm7q+jKG/svLzHrWhLufZpHownwYwWzQ0GHhCQouGvDgl49DGdN76fG2nVL+mqIihMIcBCk7tHOk1xRg5lyATPzDUTgZMbMNqhuBQ4pqZkXbM7yzWq3dJ4sALGNhnlUGMB/qU16SF+xH82ViRS1xc8OSGduG+qgRCl4FcbCI4MhGZRmYlBQoeaWPhuO3ZwNTNA4GjxepOgj+0IJlmaLpRz/fawECgYEA9yM5I9dsfZTIqGG+asVvnAT71XAlPurnrIJm29d2E2sSerQtVu8g0YN08Rladl1oiFglMt8+jcqQsG/l2InxsOMoBr2J02tuO0OEMuxEoH492s8OZ/LuZQVKwYEo5+E1ivYXgMlCE64UZK22oTVJ6QvSdKLRCp2EMwIGAQU4Z6UCgYEAqvodp47Zu7fBPrgKtjI8RcuurzjRtIVihLiLJKleIlreBoqhxgfQhlbeh28GTf2l9CX0FPHB64Y5FBfGT7b7YoB+ZyyvphOr3buXbZELldcF0y2J+K+3Q1eJpDxO485kvSxXmUdJj+X4ruIosmSP1pwbt12AhTm4bqinB0vltLECgYEAuabsNdCO34f1dQh4Ux0eSsYzqJMmZ61vfFg5jMy/FrdImoTfyn642Ks4HiY9ns1syX/t8dli1IFnUCO/Nnvp5EfAuc+IyhnaJQsDNH3oz+gF72m8GLqV+62OCu9MG2VDI7Da1xRZXw9Woosctt2hOEB5SMVG5AKQXQEYIYwaY0ECgYAUZ2bUkTKguUwhfPsiRRp2Rdlh5cj43xYjupY4WNH4DZoDc9XceSBEd+d0iCZbdlKwsTrQqQ04WztCj5fF+ZNEbQsoqueHTT+sZW+rzqxwREnYqs2VVPrs2Wx+ogfUHdRd0wXdL+myd4JW9Hxg1alothaEqm7k9z9wTUYpk8Ak8QKBgQCXYXM/zNK3ZGJB83M+BXjZhhj9jtYfbRipf+v9zkMkXQgamNQ3ovZVFKuGEo10B1iJGzdiIQJwxPI2tJ1dfPQwif+LPxX7TwmUh8jQMz869qkvGw+ohsfbOqQorEKMa3rKpBN6n7SLCMFQdTTIUJ6YpqDTTyOUr4gYgR9gbIQfMA==";
        //2��appid
        String APP_ID = "10000000000004096790";
        //3�����ع�Կ
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        //4��������F-APIP�������ص�����
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        //5�������������
        MybankAccountSmartcardDetailqueryRequestV1 request = new MybankAccountSmartcardDetailqueryRequestV1();
        //6����������·��������ʱ������ϵ���и�֪���滻�ɲ��Ի�����ַ��
        request.setServiceUrl("http://***/api/mybank/account/managecard/comparisonquery/V1");
        //7������ҵ�����
        MybankAccountSmartcardDetailqueryRequestV1.MybankAccountSmartcardDetailqueryRequestBizV1 bizContent = new MybankAccountSmartcardDetailqueryRequestV1.MybankAccountSmartcardDetailqueryRequestBizV1();

        //8����װ����������ֶδ���������ȡsdp�Ǳ����½ӿ��ĵ�
        MybankAccountSmartcardDetailqueryRequestV1.PrivateMap privateMap = new MybankAccountSmartcardDetailqueryRequestV1.PrivateMap();
        privateMap.setCardno("12345678901");
        privateMap.setCurrtype(001);
        privateMap.setPagecurrtype(001);
        privateMap.setAccno("123456");
        privateMap.setQuerymod(1);
        privateMap.setPagecardno("12345678901");
        privateMap.setProno("123456");
        privateMap.setStatus(0);
        privateMap.setGrade(1);
        privateMap.setSupcardno("12345678901");
        privateMap.setFlag(1);
        privateMap.setQueryflag("0");
        privateMap.setQueCardType(1);
        privateMap.setBakflag2(1);
        privateMap.setSeqno(1);
        bizContent.setPrivateMap(privateMap);
        InqWork inqWork = new MybankAccountSmartcardDetailqueryRequestV1.InqWork();
        inqWork.setInitFlag(0);
        inqWork.setRowReq(3);
        bizContent.setInqWork(inqWork);
        MybankAccountSmartcardDetailqueryRequestV1.ChanCommV11 chanCommV11 = new MybankAccountSmartcardDetailqueryRequestV1.ChanCommV11();
        chanCommV11.setChantype(101);
        chanCommV11.setChanlno(123);
        chanCommV11.setMac("");
        chanCommV11.setIp("");
        chanCommV11.setTermid("");
        chanCommV11.setOapp("");
        chanCommV11.setSevlevel(0);
        chanCommV11.setSerialno("");
        chanCommV11.setMserialn("");
        chanCommV11.setOserialn("");
        chanCommV11.setPreflag(0);
        chanCommV11.setProdid(0);
        chanCommV11.setCobprodid(0);
        chanCommV11.setCino(0);
        chanCommV11.setTrxsqnb(0);
        chanCommV11.setDisrecflag(0);
        chanCommV11.setComrolflag(0);
        chanCommV11.setPaperlessflag(0);
        chanCommV11.setField1(0);
        chanCommV11.setTermtype(0);
        chanCommV11.setLaunbankzoneno(0);
        chanCommV11.setFingerprinflag(0);
        chanCommV11.setDistransinfo("");
        chanCommV11.setTrxnocheckflag(0);
        chanCommV11.setBusSerialno("");
        chanCommV11.setFunctionCode("");
        chanCommV11.setProgramName("");
        chanCommV11.setAuthZoneNo(200);
        chanCommV11.setAuthBrNo(620);
        chanCommV11.setAuthFlag(0);
        chanCommV11.setTellPass("");
        chanCommV11.setFlag1(0);
        chanCommV11.setFlag2(0);
        chanCommV11.setFlag3(0);
        chanCommV11.setField5(0);
        chanCommV11.setField7(0);
        chanCommV11.setField8("");
        chanCommV11.setField9("");
        bizContent.setChanCommV11(chanCommV11);
        MybankAccountSmartcardDetailqueryRequestV1.InfoCommV11 infoCommV11 = new MybankAccountSmartcardDetailqueryRequestV1.InfoCommV11();
        infoCommV11.setTrxtype(1);
        infoCommV11.setTrxcode(123);
        infoCommV11.setZoneno(200);
        infoCommV11.setBrno(620);
        infoCommV11.setTellerno(1234);
        infoCommV11.setWorkdate("2021-12-02");
        infoCommV11.setWorktime("11:22:33");
        infoCommV11.setRevtranf(0);
        infoCommV11.setAuthTeller(12);
        infoCommV11.setAuthCode(123);
        infoCommV11.setAutAmt(1000);
        infoCommV11.setAutLevel(0);
        infoCommV11.setAutCardNo(0);
        infoCommV11.setAutDutyno(0);
        infoCommV11.setAutPwd(0);
        infoCommV11.setServface(1);
        infoCommV11.setTerminalId("123");
        infoCommV11.setOperFlag(1);
        infoCommV11.setNotes1(null);
        infoCommV11.setCheckWorkDateFlag(1);
        infoCommV11.setMethodName("xxx");
        bizContent.setInfoCommV11(infoCommV11);

        request.setBizContent(bizContent);

        MybankAccountSmartcardDetailqueryResponseV1 response;
        try {
            System.out.println("request:"+ JSON.toJSONString(request));
            response = client.execute(request);
            System.out.println("response:" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

package com.icbc.api;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bBillBankAcceptanceAcceptRequestV1;
import com.icbc.api.request.GyjrB2bBillQueryendorsementRequestV1;
import com.icbc.api.response.GyjrB2bBillBankAcceptanceAcceptResponseV1;
import com.icbc.api.response.GyjrB2bBillQueryendorsementResponseV1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GyjrB2bBillBankAcceptanceAcceptTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000123456";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bBillBankAcceptanceAcceptRequestV1 request = new GyjrB2bBillBankAcceptanceAcceptRequestV1();
        request.setServiceUrl("https://ip:port/api/gyjr/b2b/bill/bankAcceptanceAccept/V1");
        GyjrB2bBillBankAcceptanceAcceptRequestV1.BizContentV1Biz bizContent
                = new GyjrB2bBillBankAcceptanceAcceptRequestV1.BizContentV1Biz();
        GyjrB2bBillBankAcceptanceAcceptRequestV1.TransInfoV1Biz transInfo
                = new GyjrB2bBillBankAcceptanceAcceptRequestV1.TransInfoV1Biz();

        List<GyjrB2bBillBankAcceptanceAcceptRequestV1.Eap> eapList = new ArrayList<>();
        GyjrB2bBillBankAcceptanceAcceptRequestV1.Eap eap = new GyjrB2bBillBankAcceptanceAcceptRequestV1.Eap();
        eap.setTa200086002("s552");
        eap.setTa200086002("111.zip");
        eapList.add(eap);

        List<GyjrB2bBillBankAcceptanceAcceptRequestV1.Contract> contractList = new ArrayList<>();
        GyjrB2bBillBankAcceptanceAcceptRequestV1.Contract contract = new GyjrB2bBillBankAcceptanceAcceptRequestV1.Contract();
        contract.setCurrency("001");
        contract.setVoucherAmt("10000.99");
        contract.setReceivableCreationDate("20280401");
        contract.setReceivableRepaymentDate("20280430");
        contract.setContrNumber("cora1234567");
        contract.setSupplySellAgtRealAmt("10000.99");
        contract.setSettlementMode("03");
        contract.setPayMode("01");
        contract.setSpecificPayMode("具体付款方式");
        contract.setAdvanceLoanAmt("10000");
        contract.setBckgrdTradeDesc("贸易背景简述");
        contract.setMemo("备注");
        contract.setSupplierCis("销货方编码");
        contract.setSupplierName("他行小黄人");
        contract.setPurchaserCis("购货方代码");
        contract.setPurchaserName("委耽懂整壶捧泳酰佣禾支死sssssssssssssssssssssssssssssssssssa");
        contract.setPayableCreationDate("20240424");
        contract.setPayableRepaymentDate("20240524");
        contractList.add(contract);

        List<GyjrB2bBillBankAcceptanceAcceptRequestV1.Invoice> invoiceList = new ArrayList<>();
        GyjrB2bBillBankAcceptanceAcceptRequestV1.Invoice invoice = new GyjrB2bBillBankAcceptanceAcceptRequestV1.Invoice();
        invoice.setVoucherType("01");
        invoice.setVoucherCode("1100162350");
        invoice.setVoucherNo("09171176");
        invoice.setCurrency("001");
        invoice.setVoucherAmt("100");
        invoice.setBillTypeID("028");
        invoice.setInvoicingDate("20280401");
        invoice.setPurchaserName("委耽懂整壶捧泳酰佣禾支死sssssssssssssssssssssssssssssssssssa");
        invoice.setPurchaserTIN("购货方税号");
        invoice.setSupplierName("他行小黄人");
        invoice.setSupplierTIN("销货方税号");
        invoice.setCorpIntExchRateExchangeRmbAmt("100");
        invoice.setOrderStlAcct("100.99");
        invoice.setUnitPrice("100");
        invoice.setModel("12345678912345678901");
        invoice.setReceivableCreationDate("20240424");
        invoice.setReceivableRepaymentDate("20240824");
        invoiceList.add(invoice);

        List<GyjrB2bBillBankAcceptanceAcceptRequestV1.Voucher> voucherList  = new ArrayList<>();
        GyjrB2bBillBankAcceptanceAcceptRequestV1.Voucher voucher = new GyjrB2bBillBankAcceptanceAcceptRequestV1.Voucher();
        voucher.setVoucher("555111111202401171603340231199");
        voucher.setPayeeName("他行小黄人");
        voucher.setPayeeBank("中国建设银行湛江赤坎支行");
        voucher.setPayeeAccount("245435345345");
        voucher.setDraftAmt("100");
        voucher.setDraftDate("20280331");
        voucher.setMaturityDate("20280430");
        voucher.setPurpose("cora-20240112测试");
        voucher.setContract("DR-FH-DJ20090109");
        voucherList.add(voucher);

        transInfo.setProtocolCode("GYJR123456789");
        transInfo.setPlatVendorid("10");
        transInfo.setPlatBatSerialNo("123456789");
        transInfo.setOpType("S");
        transInfo.setTurnPageFlag("");
        transInfo.setBeginRow("");
        transInfo.setRowCount("");
        transInfo.setTa200073001("");
        transInfo.setTa200073002("qwe123456");
        transInfo.setTa200073065("cora测试20240426");
        transInfo.setTa200073016("0020000067");
        transInfo.setTa200073088("cora测试20240426");
        transInfo.setTa200073089("16671987654");
        transInfo.setTa200073023("20280401");
        transInfo.setTa200073029("");
        transInfo.setTa200073141("");
        transInfo.setTa200073149("2");
        transInfo.setTa200073124("0");
        transInfo.setTa200073130("0");
        transInfo.setTa200073144("0");
        transInfo.setTa200073139("1");
        transInfo.setPackageNo("");
        transInfo.setDraftDateED("");
        transInfo.setDraftDateST("");
        transInfo.setMaturityDateED("");
        transInfo.setMaturityDateST("");
        transInfo.setEapList(eapList);
        transInfo.setContractList(contractList);
        transInfo.setInvoiceList(invoiceList);
        transInfo.setVoucherList(voucherList);

        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);
        GyjrB2bBillBankAcceptanceAcceptResponseV1 response;
        try {
            response = client.execute(request,new Random().nextInt(99999) + "msg");
            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
            } else {
                // 失败
                System.out.println(response.getReturnCode());
                System.out.println(response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

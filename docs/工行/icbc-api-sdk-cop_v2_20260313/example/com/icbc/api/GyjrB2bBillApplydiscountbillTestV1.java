package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.GyjrB2bBillApplydiscountbillRequestV1;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GyjrB2bBillApplydiscountbillTestV1{
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000012345";


    public void GyjrB2bBillApplydiscountbillTestV1() throws IcbcApiException, UnsupportedEncodingException {
        // 构造client对象
        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

        // 设置请求对象request
        GyjrB2bBillApplydiscountbillRequestV1 request = new GyjrB2bBillApplydiscountbillRequestV1();
        // 设置请求路径
        request.setServiceUrl("http://ip:port/ui/gyjr/b2b/bill/applydiscountbill/V1");

        GyjrB2bBillApplydiscountbillRequestV1.BizContentV1Biz bizContent = new GyjrB2bBillApplydiscountbillRequestV1.BizContentV1Biz();

        GyjrB2bBillApplydiscountbillRequestV1.TicketV1Biz ticket = new GyjrB2bBillApplydiscountbillRequestV1.TicketV1Biz();
        List ticketList = new ArrayList();
        ticket.setInvcType("004");
        ticket.setInvcNo("22222222");
        ticket.setInvcCode("444444");
        ticket.setInvcAmt("19900");
        ticket.setInvcDate("20220923");
        ticket.setInvcChkNo("123456");
        ticket.setInvcTotalAmt("0");
        ticketList.add(ticket);

        GyjrB2bBillApplydiscountbillRequestV1.IntPayV1Biz intPay = new GyjrB2bBillApplydiscountbillRequestV1.IntPayV1Biz();
        List intPayList = new ArrayList();
        intPay.setOrderNo("11111");
        intPay.setPayType("01");
        intPay.setIntPayerAcc("123456");
        intPay.setIntPayerName("测试公司");
        intPay.setIntPayRatio("100");
        intPay.setIntPayerMobile("15914553866");
        intPayList.add(intPay);

        GyjrB2bBillApplydiscountbillRequestV1.PackV1Biz pack = new GyjrB2bBillApplydiscountbillRequestV1.PackV1Biz();
        List packList = new ArrayList();
        pack.setPlatBusiSeq("1234567890");
        pack.setPackNo("555511193720220616200010292119");
        pack.setRangeBegin("000000000001");
        pack.setRangeEnd("000000100000");
        pack.setCdTp("AC01");
        pack.setAccptrName("测试公司");
        pack.setDueDate("20230509");
        pack.setRemainTerm("30");
        pack.setDscntAmt("100000");
        pack.setQuoteRate("3.1415");
        pack.setQuoteCode("");
        pack.setQuoteVersion("");
        pack.setQuoteSubCode("");
        pack.setTradeRate("1");
        packList.add(pack);

        GyjrB2bBillApplydiscountbillRequestV1.ContractV1Biz contract = new GyjrB2bBillApplydiscountbillRequestV1.ContractV1Biz();
        List contractList = new ArrayList();
        contract.setTransType("TM01");
        contract.setContractNo("bianhao");
        contract.setContractAmt("10000");
        contract.setContractDate("20220923");
        contractList.add(contract);

        bizContent.setProtocolCode("GYJR20220119160206728375706");
        bizContent.setPlatVendorId("14");
        bizContent.setTradeName("applyDiscountBill");
        bizContent.setTradeVersion("V1");
        bizContent.setPlatBatSerialNo("123456789012345678");
        bizContent.setPageIndex("1");
        bizContent.setPageSize("10");
        bizContent.setTotalCount("10");
        bizContent.setTotalAmt("100");
        bizContent.setIsECDS("0");
        bizContent.setHolderAcctId("0200003309004709145");
        bizContent.setIfDscntIcbc("1");
        bizContent.setEtyAccType("1");
        bizContent.setEtyAccId("0200003309004709145");
        bizContent.setEtyAccName("测试账号");
        bizContent.setDscntBkAcc("0");
        bizContent.setDscntBkBranch("000016207");
        bizContent.setDscntBkName("测试名称");
        bizContent.setDscntBkBakno("102871000015");
        bizContent.setIfTransAllow("EM00");
        bizContent.setDscntType("RM00");
        bizContent.setRedeemOpDt("");
        bizContent.setRedeemEnDt("");
        bizContent.setRedeemRate("");
        bizContent.setLiquidMethod("SM01");
        bizContent.setIfNotify("0");
        bizContent.setContractNo("");
        bizContent.setContractAmt("");
        bizContent.setContractDate("");
        bizContent.setContractImageNo("");
        bizContent.setContractImageName("GYJR20220119160206728375706_123456789012345678_2_001.zip");
        bizContent.setInvoiceImageNo("");
        bizContent.setInvoiceImageName("");
        bizContent.setLoanUsage("555555a");
        bizContent.setQuoteArea("0290100231");
        bizContent.setIfPack("0");
        bizContent.setPackRate("");
        bizContent.setBatPackName("");
        bizContent.setDscntBKRemark("22222222");
        bizContent.setInterestType("01");
        bizContent.setIsVipRate("");
        bizContent.setIsPartAbandon("");
        bizContent.setTicketList(ticketList);
        bizContent.setIntPayList(intPayList);
        bizContent.setPackList(packList);
        bizContent.setContractList(contractList);
        bizContent.setCallBackUrl("http://****");
        bizContent.setIfContractImage("1");
        bizContent.setIfInvoiceImage("0");
        bizContent.setNotifyUrl("www.notify.com");

        request.setBizContent(bizContent);

        try{
            String s = client.buildPostForm(request);
            System.out.println(s);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

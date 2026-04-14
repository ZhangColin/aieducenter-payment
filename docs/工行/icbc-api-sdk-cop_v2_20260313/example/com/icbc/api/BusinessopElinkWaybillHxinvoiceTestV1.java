package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BusinessopElinkWaybillHxinvoiceRequestV1;
import com.icbc.api.request.BusinessopElinkWaybillHxinvoiceRequestV1.HXInvoiceRequestV1;
import com.icbc.api.request.BusinessopElinkWaybillHxinvoiceRequestV1.HXInvoiceRequestV1.Order;
import com.icbc.api.request.BusinessopElinkWaybillHxinvoiceRequestV1.HXInvoiceRequestV1.Order.Prod;
import com.icbc.api.response.BusinessopElinkWaybillHxinvoiceResponseV1;

/**
 * 航信运单开票
 *
 * @version 2019年10月版
 */
public class BusinessopElinkWaybillHxinvoiceTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkdpX4tyY3ROlwI5ilpLQ1bggzL+bLkj+8s+XlGl2ipBDBwDfAoLxiNU7V5nLPv8EYgeq7CV01IWYh9aWpoAJXF7SBW59JWwzExvu5hsBeO2RLBejO+qs/nMKCLX27lH5BbX+POD8Q5klyvxbL+BxFDos6jq4i2S9XY+9UfXTPZ7ZTVkicpxghxThClMGCcxFf4BknvKgm4g2NXikWtbe/q4wgva78VcRwiBd+ANhOx780xpZdHpD5GLSrHKj1loPUd6fF/SlVYHtkl+3/Gr8pXKIbreg0n2tZfpkS9aZ1gPqd9m/B/+Ty6KRCW0VCOwhrVhNAB9Mble8dkp/NuWDHAgMBAAECggEAUp1DPrn513+lZ/BpqKOw5roMInJolFA19jvEOy7gZGfYBwvgtcHIf+jZXjex6swfWolAeY39pUfOvzZfbnLzWf6NbQGyZS34nwx5vLuE/JEHE2y3gumGhqDfUHc7lrPELGet0Srm+z1DHgIBmJpc0Q+6PsMXlGfXESYhPib7O4fGeCw4r8O1DqBVsjHTnAeq8m/1QTuSDf5yIhPudp95/DHizvISNZQk0mTZuo6wcoehCwpLA5jj8qIEqXxTHKhfw1sKz/PMVml22DyLxwqKm9/UDZGZhqlxAktEJQeSfa4e94P++W7dFnNQo+5Y+bXYwyKnXnAgWXGON5H1UjdsgQKBgQDNPl+hxMYoW99bkMUfHQJdmizJHC7DhCtI+bapjjI9Answ7/gkzJtS9bW2Ir28Xwlb0tBsEG25aTN+5QG3L2e/X+2dpZr6NbvWpxy67Y6v/iP+J3dRmRdgJ9GHZaZkrXT5Vzrr1pgzDR3QtlayOlQ2yA/GsgOHbAaUHVoxVHiVhwKBgQDNInkfLBOBrtKpAIp7ckjLhRq3EAjjC3yH7xgR5HWx/8sAVbWg5Ui4nLiEBwD89lRc+EnONvr72XlUIs4qWpZnu+keJHgUXzMR+4BI9jQbAD8iVln6HhAVPjGOb5eYRigKeHgjedPHqSaBx0PHqt5gyDVR14N0HhdtCGpJGZOqwQKBgFTqqJB6pG3QTSJKTHRqAS+O+Vaf1W1JQstnnb6bOYfqfeOqdWVsThtN08euqgfE3k9icp8zUOJ0dzzaLRKIh4kzfFFMUkmRb80IySdB4FZKdMPTuldFWtmKv/MgCG8BITOEdrlayQ/eLmbK+iW1YmBj97sCrdkaxcIEPuvIAjgDAoGADj3kdtuShyBiE8Wr16I5l5eoISmF1G2Juc5Jt4RK/46ow18v+vmwMNEApnnuAaIAfWvng9+hUTbqC4NXGO2tH4qWRrd1dsSdQRRa9bXSLEW6HGIuYQURTMhCnIb22WWVhhSTErN0lpoRyFTTSdt9EO8VdJcn0zn7mmBJMMYL0IECgYEAkV6dKTWoFreY4cpHXRAgQxd65ZcoyYMDX2s9lRh+2FtuHCEjfH+liOsObxs7IVT0EzhxCY+9OfD2u3yZ0X89T3dVo2D/GtrHnJZscqJcz5/iHk9rnHbMvS9qVUtnBVEKxT0cSNhzjUlTmLaR/Mw8a6ahxiYR7sQE2eLRHrNwrnA=";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000004095577";

    public static void main(String[] args) throws Exception {
        test();
    }

    /**
     * @throws Exception
     */
    public static void test() throws Exception {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8",
                "json", APIGW_PUBLIC_KEY, "AES", "4p1kNKRkzJDzi/nWFhS/ig==", "", "");
        BusinessopElinkWaybillHxinvoiceRequestV1 request = new BusinessopElinkWaybillHxinvoiceRequestV1();
        request.setServiceUrl("http://ip:port/api/businessop/elink/waybill/hxinvoice/V1");
        HXInvoiceRequestV1 biz = new HXInvoiceRequestV1();
        //订单1

        List<Prod> prodList = new ArrayList<Prod>();
        Prod prod = new Prod();
        prod.setGoodsName("iphone");
        prod.setGoodsUnit("台");
        prod.setGoodsSpecification("64g");
        prod.setGoodsQuantity("10");
        prod.setPriceTaxMark("0");
        prod.setGoodsLineNature("0");
        prod.setGoodsPrice("10");
        prod.setGoodsCode("1314");
        prod.setGoodsExtendCode(null);
        prod.setPreferentialMark("0");
        prod.setFreeTaxMark("3");
        prod.setVatmanSpecialManagement(null);
        prod.setDeductibleAmount("0");
        prod.setGoodsTotalPrice("100");
        prod.setGoodsTaxRate("0");
        prod.setGoodsTotalTax("0");

        prodList.add(prod);
        List<Order> orderList = new ArrayList<Order>();
        Order order = new Order();
        order.setOrderId("20190926000000000011");
        order.setPickId("20190926000000000011");
        order.setBuyerFullName("池石镇");
        order.setReceiptAmount("100");
        order.setReceiptCurrency("014");
        order.setPlaceTime("2019-09-26");
        order.setSailTime("2019-09-26");
        order.setOriginPort("上海港");
        order.setDestinationPort("天津港");
        order.setTransferPort("日本港");
        order.setFreighter("李光洙");
        order.setLaneCode("01");
        order.setLaneName("航名");
        order.setProdList(prodList);
        orderList.add(order);


        biz.setInvoiceSerialNo("10002778190926000016");
        biz.setSellerId("B2BKJLS00100048");
        biz.setBuyerId("B2BKJLS00100048");
        biz.setPlatformId("10002778");
        biz.setCurrencyAmount("100");
        biz.setCurrencyType("014");
        biz.setDeadLine("10");
        biz.setDrawerIdentifyNumber("0102");
        biz.setDrawerERecodeNumber("0102");
        biz.setTaxAuthoritiesCode("0102");
        biz.setAgentFlag("0");
        biz.setPurchaseFlag(null);
        biz.setReceiptCode(null);
        biz.setInvoiceProject("办公用品");
        biz.setEncodeVersion("26.0");
        biz.setSellerIdentifyNumber("123456");
        biz.setSellerName("金钟国");
        biz.setBuyerIdentifyNumber("0102");
        biz.setProfessionCode(null);
        biz.setProfessionName(null);
        biz.setDrawer("李在石");
        biz.setPayee("宋智孝");
        biz.setReviewer("HAHA");
        biz.setInvoiceType("1");
        biz.setOriginalReceiptCode(null);
        biz.setOriginalReceiptNumber(null);
        biz.setSpecialCreditFlag(null);
        biz.setOperationCode("10");
        biz.setBillFlag("0");
        biz.setBillProjectName(null);
        biz.setCreditReason(null);
        biz.setTotalAmount("100");
        biz.setTaxRemovalAmount("100");
        biz.setTaxAmount("0");
        biz.setRemark("running man");
        biz.setOrderList(orderList);
        request.setBizContent(biz);

        BusinessopElinkWaybillHxinvoiceResponseV1 response;
        try {
            System.out.println("Request: " + JSONObject.toJSONString(biz));
            response = client.execute(request, System.currentTimeMillis() + "");
            System.out.println("Response: " + JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                System.out.println("execute success");
            } else {
                System.out.println("execute failue");
            }
        } catch (IcbcApiException e) {
            System.out.println("execute exception" + e);
            e.printStackTrace();
        }

    }

}


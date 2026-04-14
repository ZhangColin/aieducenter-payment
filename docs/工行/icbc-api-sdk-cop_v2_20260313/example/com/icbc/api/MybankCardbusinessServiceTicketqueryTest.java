package com.icbc.api;

import com.icbc.api.request.MybankCardbusinessServiceTicketqueryRequestV1;
import com.icbc.api.response.MybankCardbusinessServiceTicketqueryResponseV1;

import java.util.HashMap;
import java.util.Map;

public class MybankCardbusinessServiceTicketqueryTest {

    public static void main(String[] args) {
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCzYUaZG/h8xTBwy"
                + "4FiUFwKrTnyBqbQNNa4zfWzMWK+cQ/eEHuPxKcWrv5lwcav+NXE1I3IMUuXIX7zee"
                + "vaIgNs0GnCgcxBk4il/NMoVxlfrUSU2I/cBSMLGcOoD95IeA1kSGO1gQGnKq+g76r"
                + "A0dt87+Jdh4Zjfc9wlryDnDhRRb4XF5t7KBehe8ESMR4K9oLcD2MXfxoXyJE7HiWY"
                + "lhHA3M2A1EDaIZKOcW817kZbJSL+3IeidfItGIVt4cG/GYWWbGnkwmoP2YvHRZcTq"
                + "ncNfcn2VieyWQB6IGyId1eSLDqvAQAHwe63dTO3ca5THlnJyM+4yTbYABBkTZJGhR"
                + "SLAgMBAAECggEAGFiom1lXhZ/B7l1Ap1Ze17ghOD883t99EUIrCfI3A8AWtj4odx/"
                + "qycJglvERBYtRgFCVGcnPB6ktMOCoaUfajSdQY2z4iyH6FwVUp5ICyKCcwVMPW356"
                + "/iO2EZZtxgo9TxIgLl9fGRBLHB3LYDZbgv6QC7+8Nr65q/9ZgMdwQV9qr5bfKyk1Q"
                + "fOdS2aOihSMqm6rB0L4UyMzLXe66QOwevXr1aje2t63yRDyzILoHhpx7BFTmAD43X"
                + "IVBYusbRS6rYI6NSt79Fh73sDFRqDTaNeJUFm4VqrN09vZv3TRasvgWoziYN3Qu4y"
                + "qrSbhxdyNUa/a5aCdfbrSXg5PDMflGQKBgQDfhLFWDssJCiRysmCpOZGjkyd7fivY"
                + "hOZedota7rw/rXjyAi+2Im2oZSq+v0gs4wSMKAEX5mpFrqXd+yEfkfpzczErcw8FA"
                + "UaWMXP47Gw8tl6FAbWuHUh5TzTYuiOa0ZJ3Nde9lr2/68s13DSULtp+nqtKa0URcR"
                + "xQBZcIFsl8vQKBgQDNco1ysZGvGAVj/N9HpLl+S8Y39I78q7wOMqGwXZ+kPSrZhVt"
                + "lgyI13AJIWio2GvMWQ2izEhxmHUI0G51g6OhlOCDrDnJZD3t/Sq8cBsasfgF6M8UI"
                + "5Yep8aDJ9iSDO1b9OQ+PlGGzhPfkpnCQNJpV4WAZ8E+lx8/6BS/nJNr+5wKBgGA3K"
                + "2sjFQ1AG25wEKHn0SR7rMxBBLjAhLX3C4fKKqiykNSEUmtxqIiLe/n+NyBzXLrEYa"
                + "wbGHYEdwv2FU0Utp3J4UmVzspVwT/X69ckVndP/aH9mWpo2pC1oscEYV08h3Lfb44"
                + "B/I5H/wKhVtTO0mkc/hkb0fBvI0PjFB98R6F1AoGBAMsWQdzuyyRainmcrtsTaon+"
                + "PaNyjSYYXX1rb4n4AqdID+OWgBKI3nh/U4JSHwf8++82E3lwncITii4YXio+Efx8z"
                + "42Maly/gUUDfrYlvJ8msC8sncl9m+3SfS8nslBHiGYo0ckM1Mew2ZudLAhg92+Nqo"
                + "H5m8I1EtID7qZ/ODxhAoGBAIcf/IMADuxiCtIs6/MqXJIN7i/fSAR6+mVXzgmu7tE"
                + "759oVqLRiB6m0r7TYPeAtJ958fujjT2uPm8AGHs3xBe+75aLLmGVSl01C4fgX81JD"
                + "DMvGiKacYbLc8nOr6ZEiNfKdTK+0UjbpFOE4CS6d9l/BUUnhjLoNX5jhXVsfNel4";
        String APP_ID = "10000000000004097556";
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        MybankCardbusinessServiceTicketqueryRequestV1 request = new MybankCardbusinessServiceTicketqueryRequestV1();
        request.setServiceUrl("http://ip:port/api/mybank/cardbusiness/service/ticketquery/V1");
        MybankCardbusinessServiceTicketqueryRequestV1.MybankCardbusinessServiceTicketqueryRequestV1Biz bizContent = new MybankCardbusinessServiceTicketqueryRequestV1.MybankCardbusinessServiceTicketqueryRequestV1Biz();
        bizContent.setQueryType("2");//查询类型，1-分页查询；2-单笔查询
        bizContent.setSerno("360215457851");//外包服务机构编号
        bizContent.setStatus("1");//工单状态，1-待处理;2-处理中;3-已超时;4-已完成;5-已取消-（其中，状态4、5仅能查询最近一个月的记录）
        bizContent.setTicketType("1");//工单类型，1-新装机;2-换机;3-撤机;4-合规巡检;5-促活;6-日常维护;7-故障处理
        bizContent.setCreateTimeBegin("2024-08-01 20:10:05");//工单创建日期范围起始时间
        bizContent.setCreateTimeEnd("2024-09-01 20:10:05");//工单创建日期范围结束时间
        bizContent.setPrtiId("360212452456");//商户编号
        bizContent.setTicketNo("00000000020024101210000041");//工单编号
        bizContent.setOutTicketNo("00000000020024101210521458");//外部工单编号
        bizContent.setPnMethod("2");//翻页标志，1-首次查询；2-下一页（注意：查询类型为1-分页查询时，必输）
        bizContent.setCount(15);//每页笔数，每页最多15笔（注意：翻页标志为2-下一页时，必输）
        bizContent.setOffsetTicketNo("00000000020024101210005845");//每一页最后一个工单的工单编号（注意：翻页标志为2-下一页时，必输）
        bizContent.setOffsetCreateTime("2024-10-12 12:00:00");//分页查询时上送上一页最后一个工单的创建时间（注意：翻页标志为2-下一页时，必输）
        request.setBizContent(bizContent);
        MybankCardbusinessServiceTicketModifyResponseV1 response;

        try {
            MybankCardbusinessServiceTicketqueryResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println(" success:" + response.getReturn_code() + response.getReturn_msg());
            } else {
                System.out.println(" fail:" + response.getReturn_code() + response.getReturn_msg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}

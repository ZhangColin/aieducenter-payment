package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.InvestmentFundtrusteeAccountbalancequeryRequestV1;
import com.icbc.api.response.InvestmentFundtrusteeAccountbalancequeryResponseV1;

/**
 * Created by Administrator on 2018/10/16.
 */
public class InvestmentFundtrusteeAccountbalancequeryV1Test {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCL50kmv3pRn32efyF/8+lmdtGkLvT+vmvP13SQ9kEqrRyudCyLsNEtW01S1TwS07Li6Gzlll4UemwpOSBF6LO7HRXNHLtC4TZua8RSxYrYuQVL8xMHR9w8Li8+ol2hUpi/ewCi+asFTypJrNMK3jTqvhVTjXvlAyjFlRdNZgpE9WiqnorGRkVSfQXVhrt1FXWzsfXvhLxAHTdURYlPlb3nVEIp6Ef9pX9DQG+w/fakYa5TDIRrfP2pJiD6w0HHeuBxn/59oe8Iz/lpLzSk6WBin4FZTlFvTmxWeDGbRbOisKOVhy+eICkbCmRN775lpgKG0or8Eyc+hPdYpyPhlEJ3AgMBAAECggEAKhwNiporqRHGb8R4QtkZsXumEjMIDv0l2kNRlKhqaPv+HAxftBQegfZvSbVhBZGu0FmBHYGrA6Ltl2LYcfJe1r0kaL5zJug+x/Gr//XxmtXl4Dxjx9c7VOnZ0pJweDQyQcA2KtfXQBvZ8J8STr0fVGCubp2xYvcWeJItl2BVSvfY+S4ctWd7mG/TOn2yJyfBhU542tP5tEflvzv6krJDxuIo9juaz3VJYeJOOk1ktrVi2oyp/RMLFGrQDWMY9YeKfpUquXiCjHdYrXC41rMvtwZ9Uzb66TjVUKc7VhARuozdaLv4vju3OUlIwtcqOCMlfL3k125oBcKX/UXpzh0wEQKBgQDB/Y76fE32O7oZaRM6SmoXuyu3j7yJ2RsGoZp4Z0sasUe6S3BVA08PmGN5+Pne4SU9BDe1gnGEDNuqMZjYhGA1IkFinqYvGa5EWxxulsxRdcOS1tJDWZuLoaDvMSdum8mvkxsYGq3kuYXQZxz3fXShZyvdnzTFYoVeImQRQeYIowKBgQC4n7tC5Ukly2t7xbNSLQ750gGiaoRQ7D5oJnxSkPdQXzcgOSZouX/TD+mL/mmXimsDOynHpWrIL2Hyngnbvn2V68hId6oD7NKu/0KvHi4Afb2VxXjVu4hHFPkoVpt7a/3SEJN5J957p0EDxMVELYCnE03jdoXG5v7f/p1ObNIYHQKBgQCbWPQgv16naxkStESDj4f6TRcrPl3bfhLYcp2DAiTw0TsBgtwIGEg90ukGik1/IIlznWcHYdiJyMo4NtORwCBTLIVz9lxap5QYhWX1YalpKoddlc53xNB9NwfrxE8xkmwmwAUYfnT3D2KLi6d0wfCeOEK2jYHy93yZ76x27XDLRQKBgDMVz6UE+KaCkO0gmSvF1/z8RQOB4bbah7Id5AycnDIbBo/eAGi920a1gnQAI4uQ+2dbeYYKmW9m3P1bCXNKVEmlgje7FModP3a4nuUBvszX/htxFqxdRaoVfZPR0xflm8gCWiwOAhvI/c5jJgplZNHWH5ku9HlC9YdZVoGazs9pAoGBAKcpVeMiTantmVLbhlt44GNzZ/JUrDbjI2JYI2IaPbI2UF9nERLSyCP24q/tOD1FURh2eGRaFO+lkdQsE/aQFCSSlKBwQVE5hBZZUH7VjTTbk1sWpzolsvLB6LqeyIL3T6t4F/CxZL/dVK7nNnNbx/jq2LueT0sw5GjxCx8Vl3D5";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000000004695";


    public void test_cop() throws IcbcApiException {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        InvestmentFundtrusteeAccountbalancequeryRequestV1 request = new InvestmentFundtrusteeAccountbalancequeryRequestV1();
        InvestmentFundtrusteeAccountbalancequeryRequestV1.InvestmentFundtrusteeAccountbalancequeryRequestV1Biz bizContent = new InvestmentFundtrusteeAccountbalancequeryRequestV1.InvestmentFundtrusteeAccountbalancequeryRequestV1Biz();
        InvestmentFundtrusteeAccountbalancequeryRequestV1.InvestmentFundtrusteeAccountbalancequeryRequestV1In in = new InvestmentFundtrusteeAccountbalancequeryRequestV1.InvestmentFundtrusteeAccountbalancequeryRequestV1In();
        InvestmentFundtrusteeAccountbalancequeryRequestV1.InvestmentFundtrusteeAccountbalancequeryRequestV1InRecord record = new InvestmentFundtrusteeAccountbalancequeryRequestV1.InvestmentFundtrusteeAccountbalancequeryRequestV1InRecord();
        List<InvestmentFundtrusteeAccountbalancequeryRequestV1.InvestmentFundtrusteeAccountbalancequeryRequestV1InRecord> list = new ArrayList<>();

        in.setFileType("1071");
        in.setFundId("040003");
        in.setReportType("01");
        in.setBeginDate("20160818");
        in.setEndDate("20160818");
        in.setDeptCode("0002");
        in.setCertId("");
        in.setSerialNo("20160502000001066382");

        record.setAccntCode("0200099809216701355");
        record.setAccntName("华安现金富利程序化测试八");
        list.add(record);
        in.setRecordList(list);

        bizContent.setIn(in);
        request.setBizContent(bizContent);

        request.setServiceUrl("http://ip:port/api/investment/fundtrustee/accountbalancequery/V1");
        InvestmentFundtrusteeAccountbalancequeryResponseV1 response = client.execute(request);

        System.out.println(JSONObject.toJSONString(response));
        if (response.isSuccess()) {
            // 业务成功处理
            System.out.println("success.");
        } else {
            // 失败
            System.out.println("error." + response.getReturnCode() + response.getReturnMsg());
        }

    }

}

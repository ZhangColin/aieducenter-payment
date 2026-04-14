package com.icbc.api;

import com.icbc.api.request.BbcacpmAcctQuaryRequestV2;
import com.icbc.api.request.BbcacpmAcctQuaryRequestV2.BbcacpmAcctQuaryRequestBizV2;
import com.icbc.api.response.BbcacpmAcctQuaryResponseV2;


public class BbcacpmAcctQuaryTest{
    protected static String APP_ID = "10000000000000002225";
    protected static String APIGW_PUBLIC_KEY =
            "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static String PRI_KEY =
            "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCe/HF/zUu+R1Y+wxHZvJPTng5+yog067beDjuXSfJdHaq/BBwDviYOtDRirXgrlNnZjRV/QTc+s4iU5KBGOWIJXXDbxypO9L1mX0jV05017F55mfs3N1sAfOkyz2LH0YZeQTvNm08Pnb0Pxy54TDtuJmM3Z43PbTURf9h+DpQI8EDbBdSlxdLw9E5Fc5m30QqLDcf8M0WlwemN8vS1U1z/zuSnS3b8DQ8NwYcGnoQqw1t2JMGouYrjENAUWJkKa7BOQpRYkq2WKmQ94V4rHelDSOriyV2WL3HxcF5xxoqwAySORAU2vSdwUyV0Xv1SJLRPgIT66NkLwT7VgByfPdrRAgMBAAECggEAfv+KrptQmOOlOida2AJyV4WXcKLaYOO1xPW7lmNkbgtnvIRM2JSJFA/RFM4bAz05NDYl6OwwdC63uLBUcyQdcvuoTndvDXkk3mTY6q3KlCuHYD2nVfkrAV3GbmcJMjLl0d7sJZaddgz73GGwdjn280tI0BYxSnOjA6KLTk7Y47W4duC5m5lQcP2NFBvnJogQu86UzJfJd2C6KQd50Yt0sXNy6JA8jzBH2gMEBCV3iG6oPzHSur3502UUtGaGK4CsT659liRG1soMSwNwILNhqDGd/5jQrrKvK9ijpeW43j/ZVBJp7aDQcGjnTaIoveqqW0yrb24Dw2/W0tXHDR4XnQKBgQDt4U7hPl/TSkTSMhwD3StICAOEUtwZLq//QHW51glDpbAHSMz5oYkCRPBQtF269TT5FYWKjeJzbN8n4HEN+Rrc9uZK2LlsVR10UA0Q6aEwAJWgFieP7RG2wQm+Fo6VybuOjo6cDR1dy4UVcW+EE6Pg2UeuhTkFLJS1OnTcZQgDJwKBgQCrGLE7X9V48qIYZycyZdKcN+cTyW+iLtU7U9LXVbN9FyA8sPCgzzQT2qMlyYT9RPV/cxnlzf/E9GM2Z1KKQDjXNmNCUBQHWvktEbqGiZL4w9sDZf2LaY7g1cnw92uVDee+HgxCJ4DNyhmNTBuxUrY+aaUG4/XoAIGyYXDd+MQNRwKBgQCLynjhUxdbAm0ZHQtrleBmbxcp/suLHO2eUdIdCFPfZhHg5f5WwH/UTpDuzF+8i3a63dPhTWoDLLqjI3ksnfQUYB+LYnrs18P8Y0KaasEnhWBoeDx9jvJ29E/2Xikiiy9mIg5aVDaqtPwbUtMWybfnXaiLPYz5pm4pZhXgopqnxwKBgEC+DDqabe3hakLvrZ5dd0+Tu8X6AFsPe7KCapL/I/cerB0rGzLOlTc6EWg4FDG+SWNby53w0GrXyz8H4+zalmehdffe02VhIE7UmY9VOZ8WhaaIjmOVovIjE9Ix3ay+YUXImvzhc9TrN7YG6ae0069UTJUJ58BNiMZWJt5nxs/7AoGAcn4pbsdp0CZK04u6d2PQfocvx/kgRMxY+DEVlzYzyGz00zRyNH0zY5ofFmYNCANj6VKHux/Nvm/GajdQHy5Y4c+eizH+N/eSoG1aoPLy491E3rwMiuoo7kVxJLx6pmkacKyLdylAs4ieSJIKpuiGTYVMjya5oP0EGYWMHgxncgQ=";
    protected static String BASE_URL = "http://ip:port/api";

    public static void main(String[] args) throws Exception {
        String URI = "bbcacpm/acct/quary/V2"; // api地址
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {

            BbcacpmAcctQuaryRequestBizV2 bizContent = new BbcacpmAcctQuaryRequestBizV2();
            BbcacpmAcctQuaryRequestV2 request = new BbcacpmAcctQuaryRequestV2();

            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.getGrpHdr().setRefrence("0118011800003042");
            bizContent.getGrpHdr().setBusinessCode("10084");
            bizContent.getGrpHdr().setVersion("1.0.0");
            bizContent.getGrpHdr().setTradeDate("");
            bizContent.getGrpHdr().setTradeSource("C");
            bizContent.getGrpHdr().setTradeTime("");
            
            bizContent.getGrpHdr().getSender().setBrchId("402100000018");
            bizContent.getGrpHdr().getSender().setInstId("402100000018");
            bizContent.getGrpHdr().getSender().setSubBrchId("260");
            bizContent.getGrpHdr().getSender().setSubBrchNm("200");

            bizContent.getGrpHdr().getRecver().setBrchId("402100000018");
            bizContent.getGrpHdr().getRecver().setInstId("402100000018");
            bizContent.getGrpHdr().getRecver().setSubBrchId("260");
            bizContent.getGrpHdr().getRecver().setSubBrchNm("260");
            
            bizContent.getAcpmAcctQuaryInBusitext().setCurrtype("RMB");
            bizContent.getAcpmAcctQuaryInBusitext().setOgcode("402100000018");
            bizContent.getAcpmAcctQuaryInBusitext().setInqmode(1);
            bizContent.getAcpmAcctQuaryInBusitext().setProdacc("2000707660000001");
            bizContent.getAcpmAcctQuaryInBusitext().setTrxserno("789456123");
            bizContent.getAcpmAcctQuaryInBusitext().setLgldoctp("0");
            bizContent.getAcpmAcctQuaryInBusitext().setAccname("");
            bizContent.getAcpmAcctQuaryInBusitext().setLgldocno("");

            // 通讯区参数设置
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            // 输出支付指令提交响应对象，需根据实际调用服务更换（BbcacpmAcctQuaryResponseV2）
            BbcacpmAcctQuaryResponseV2 response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");//
                System.out.println(response);//
            } else {
                // 失败
                System.out.println("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankLoanDatachainloanCustomeradmissionqueryRequestV3;
import com.icbc.api.request.MybankLoanDatachainloanCustomeradmissionqueryRequestV3Biz;
import com.icbc.api.response.MybankLoanDatachainloanCustomeradmissionqueryResponseV3;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MybankLoanDatachainloanCustomeradmissionqueryTestV3 {
    private static Log log = LogFactory.getLog(MybankLoanDatachainloanCustomeradmissionqueryTestV3.class);

    public static void main(String[] args) {

        try {
            String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
            String privateKey = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCMkkwRnaKS34Tw7EopW5IYTXqDNUoAOktjiHcDk/IBS0FEikwkFfLunl7L0428PsCBFiVt04OuQ0poJ5m2uzqQtr8tPEHfy3iv+4xBjbV9iIXd2EBjsZv/sS7A1yPnVpdMOGF3e2R5YNKJ60Y41bz2NWLVDj5W/9AVCQMXsbQo3vkO1nPCcztNTfyKxHrY30lFsHMBCodHd4Tsxjawit7vASo6iVczy8MGO8KDpIMMd5fgyepKIMdFR+M7RIMnsozjWSdPQLr+YDHZjIWWuTrYtjabkCDUAlyIte7ynRZvhe8V+wGcTxnmhBJEym8tOWIPEdkzeecIQsRlPSmVXm0tAgMBAAECggEAH/JHk2FNxy9BGTe6CvgilZ9WDmeX78FPzMkAszF2OFEP/jGn6DOMyCn5xTzI41Unjq6nxvPg40Orr0wApQSb5LODcxZUqjWlsejrESLRcjwKoEY7K+z7VPrBc1XxdOu66rmNfQfv553Xezya8uOwVAwB8AWSqVbtxToNF99s4DSllRjlv9x1bhiEGeB205Io8KiL+D0jATrAoTus6XgnbNKaY/1F2Hj5gCWAN+pcKrVVwTjb+tyNvXv+gtA4JBmyDTt7kFpHknYnAitv0Jujf6e7WVU9uMNXimzY8yGCEdrYDkLE/jYvZVYWyJaVvb58K50w7wJqdUPPZBLhFJX9SQKBgQDSzkfKqOnGvPlUcW98pBibpyK+CEa7Ps9mK5Bv63XIvwlTd+iHEmIwdnxiPnzTXORmU+pjGz1ixFptwPDHW8YWnO1PN+WSddG7u1WRHUB78YNhAraDnaETodPS8IqWUTr7IUsVaNZnjkJo7gknQ5KFdDmuMFqoQunGKQ8dPCldBwKBgQCqtVM+asyAT5njFPTdaR06lRMTdWTlrBXrmmTekqsfqI8+12WwTW8tId0jvlsTzjZ5CxecBRynMWKumOjOmEsY5KnGxLffVWjLS/8GawsDzibJENjxXYz4xltL5QQ2s5uITgRURoexAyZUji4t4lwyrmpcX4CS04pYnLz3Ao+LKwKBgQC69uMrdeZpMj98SmNTy4Jbwr+IhXSeK5XsiuS+rrm3Qt+1nNqpuX9Gdj/ZLoytNW0I6JIwNf+asoV1ZDLozZeBhMrggLXm0QIkG4FHhAcmJkycZLiVQN+UtulU46w2p3LFOoEKSwIltEFoKP4LSd4Q6MUjnZU6OKZ37wCNsEcLTwKBgQCSzpfFYQF4A3z7TmV7GE/btqKOJHJeFk1Prhq7UvU5p7NxPrqkESPgc3QDrkwTT0+Hb2OheL/4hT8xmiz0pwJGaTgcyca7mW8jDrtGoL37SsoONp/riyGWPLf9H/ostO+4HCga9gDx2RyYsSHDshzKNTchM7yWCLi8liboYtphuwKBgAZxjUvAfAHn7dMAXylVDbgdb7m9vABU1ydQCvNdPHznRsh+/eqvVTrkBnYzKvFhM14MBFxF0YRjwENwrcXZaz5mar6xXXjaG4w8xPw2OGjPxAAU5s0465f+1ykbsITK0ZALs2woxZu+CQuXDncAIKjhU24+eBtXaCpsyqQTtY63";
            String appId = "XXXX";
            String url = "http://ip:port/api/mybank/loan/datachainloan/customeradmissionquery/V3";

            DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
            MybankLoanDatachainloanCustomeradmissionqueryRequestV3 request = new MybankLoanDatachainloanCustomeradmissionqueryRequestV3();
            request.setServiceUrl(url);
            MybankLoanDatachainloanCustomeradmissionqueryRequestV3Biz bizContent = new MybankLoanDatachainloanCustomeradmissionqueryRequestV3Biz();

            //参数内容是我随意填写的。请按照实际情况来调整
            bizContent.setMethod("customeradmissionquery");
            bizContent.setAppNo("F-GCMS");
            bizContent.setSerialNo("XXXX");
            bizContent.setAreaCode("0010100000");
            bizContent.setEmployeeCode("020083999");
            bizContent.setLanguage("zh_CN");
            bizContent.setTransNo("IDataChainCustAccessAndQuotalQueryService");
            bizContent.setVer("1.0");
            bizContent.setTurnPageFlag("0");
            bizContent.setBeginRow("1");
            bizContent.setRowCount("200");
            bizContent.setAppId("xxxxx");
            bizContent.setCertType("3");
            bizContent.setCertCode("020090001926823");
            bizContent.setAdmitType("0");
            bizContent.setCustName("借款人");
            bizContent.getClientFlatID("190092")
            request.setBizContent(bizContent);

            MybankLoanDatachainloanCustomeradmissionqueryResponseV3 response = client.execute(request);
            if (response.isSuccess()) {
                String a = response.getReturnContent();
                log.info(a);
            } else {
                String a = response.getReturnContent();
                log.error(a);
            }
        } catch (IcbcApiException e1) {
            e1.printStackTrace();
        }
    }
}

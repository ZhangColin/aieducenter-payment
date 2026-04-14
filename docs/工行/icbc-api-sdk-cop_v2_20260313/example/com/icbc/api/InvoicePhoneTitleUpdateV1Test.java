package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.InvoicePhoneTitleUpdateRequestV1;
import com.icbc.api.response.InvoicePhoneTitleUpdateResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KFZX-CHENWP5
 * @date 2022-7-28 18:20:42
 */
public class InvoicePhoneTitleUpdateV1Test {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKBxOzp8zRz/d66iERz8H+rWFdp/AbCmYIKGowAnhRFd6YRi0ZgcuUHFjrnI+2TimwdUjngyvgEtWxcGgUqbKxQ2B3lGcLD4nOy5kolUwWqqutuaTcvPfms56CeI/O4ZfELCQ6ZWSTh/Q+Azelhqa3DFx6TXB394lbwzKEE7XY6DAgMBAAECgYEAgUrebQKXlG+2tc86tT3kRq2hTMOezAk3qtfu0TgHvvgML7gmqUs0a3FV1BfHpwQJ5H1aljhl77LqPgLq/AFRU6COCH7ehBMjl3IDQYZctee72xIizvRdq9dqmIVo2EwcvIi0WDwAGsZ8C7BLGp0iWYJxVFpd4v+wTmo/qs08G1ECQQDNlsjPYbUcNsHll1l5EIsWZlYsqQEqh+tQ3QwhVH4umjzLn9MeAl+zJGCUujZ30BA6h9YjHQV1YQE5ilYfnsypAkEAx8iAqpmHhdP9UtdS9s90IJosQe+vx6Ro5lncfey1NbIiPshjdEPi4bf1o+xv8vr4567a4rDthA4hgdNKqxlxSwJARJZRWB5YtZjvBxF/IAyM2mQ5y5x2byH5N3kHWvc9QX7jWWtwm0pfcvxn3DZjhYU8ZMp8fipob8h1CCVGpjXsQQJAWUs4lwfAFwTFOP2cy0u178x484+Z1XWanTqU3oOqCrHvtHgEW7br9U8vr3DCKKwcDBGPK7tArcFTIJ+7w/W/OQJAD0d2+So02KjhjUmihkg2PRq3+ouiRCPSlSUWgy1jQP295MdCFdg32Te1rcLlq695tdma1rXqVErknGHf/XamkA==";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static final String APP_ID = "110000000035782";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        InvoicePhoneTitleUpdateRequestV1 requestV1 = new InvoicePhoneTitleUpdateRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/invoice/phone/title/update/V1");

        InvoicePhoneTitleUpdateRequestV1.InvoicePhoneTitleUpdateRequestV1Biz biz = new InvoicePhoneTitleUpdateRequestV1.InvoicePhoneTitleUpdateRequestV1Biz();
        InvoicePhoneTitleUpdateRequestV1.PrivateReqBean privateReqBean = new InvoicePhoneTitleUpdateRequestV1.PrivateReqBean();
        InvoicePhoneTitleUpdateRequestV1.IndexNo indexNo = new InvoicePhoneTitleUpdateRequestV1.IndexNo();

        List<InvoicePhoneTitleUpdateRequestV1.IndexNo> list = new ArrayList<>();
        indexNo.setIndexnoseq("索引码内序号");
        list.add(indexNo);

        privateReqBean.setOperType(1);
        privateReqBean.setAppIndexno("索引码");
        privateReqBean.setBusitype(1);
        privateReqBean.setIndexnoseqset(list);
        privateReqBean.setInnerTaxerNo("购方纳税人识别号");
        privateReqBean.setKpNote("发票抬头");
        privateReqBean.setCustNote("发票抬头");
        privateReqBean.setIsTurnPaper(1);

        biz.setPrivateReqBean(privateReqBean);
        InvoicePhoneTitleUpdateRequestV1.PublicReqBean publicReqBean = new InvoicePhoneTitleUpdateRequestV1.PublicReqBean();
        publicReqBean.setChancode("渠道代码");
        publicReqBean.setOtellerno("操作柜员");
        publicReqBean.setOapp("应用简称");
        biz.setPublicResBean(publicReqBean);

        requestV1.setBizContent(biz);
        InvoicePhoneTitleUpdateResponseV1 response = client.execute(requestV1);
        System.out.println(JSONObject.toJSONString(response));
    }
}

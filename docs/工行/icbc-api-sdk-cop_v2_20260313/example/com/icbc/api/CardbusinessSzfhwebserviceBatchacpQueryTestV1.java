package com.icbc.api.test;

import java.util.UUID;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessSzfhwebserviceBatchacpQueryRequestV1;
import com.icbc.api.request.CardbusinessSzfhwebserviceBatchacpQueryRequestV1.CardbusinessSzfhwebserviceBatchacpQueryRequestBizV1;
import com.icbc.api.response.CardbusinessSzfhwebserviceBatchacpQueryResponseV1;

/**
 * @Author: szfh-lj
 * @Description: 深结算跨行批量代收业务查询，测试类
 * @Date: 2021/03/11
 */
public class CardbusinessSzfhwebserviceBatchacpQueryTestV1 {
    //企业私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDd8N5JTifSJAcRBNnQvbrUgdLNCwSd9XLi6JZMZnvYXk2C8Wu91KlgfK4QnzHYVVSlRhMRnFrbLsr8MoJb7eHmE6mlDeZqBhsqIGI0Kt0XX/G8ssXtqZkLA4FCBHxI8iquW44IAM+F0yUClJr9eQkHlIwU52LY5VaiD9a+ExFtIGcXMwGotSn13vEAuYLsr6EtCZkTaec4qNYpJj6TGsn7/pInXJTM/aDN2QLVAtwlVXs0PoSfRtS8WYcz1xl6fBJKfuuao10dnhhpUywSeK6Z05hgFoYjbxYpQUTrboc+LHKik9rU0PBx8x1qbKApgkBEd4lTWi4zpulo26zWJxi3AgMBAAECggEBAJTDFrvb/yUDsRjfP4CDK++09S8q63VVSCR/8f5T0z88NohGeglLuhxKdgH+MtLmn04Iok9zlhLEUpqo4GfuEzS8iUFxB7iZTyv1eN2uvXMlhEsPKUwcGdEgEEvH5eafK6yjAZirsm3pES2hniG4Ny01jI+WaNMpJeCK7U2KSmvxw9xLnRClqcJxDZ4WMITCfEcpv9GUsC3V9cgYEeAt/Ah+8NAXev8CibxXtO0fSg/bil02bQ8S+7FP2HehMPQZri8steOO3o1WOAFNt0g2E5dNzgYkgov8GHZl8GijJFLpKmqZBnGdbwpQzombjk7hm/bkXq4Usjd5EG2xpavPUsECgYEA/0j5UzGIE7P2hvuA9eVY08COoBQIQLqZN472gc8loncPCnuu/OQ08pHohI1I0BWtWJNFXb2sdi+njbvSmams5TwvkybPJgN2t3n32127Vb/GSANT2I6RvKdV2LTzUUwp0oSq3lw3jJaYyi1SvGH5EjJQqc+Q4QmUCxVUXjXg9HsCgYEA3o/9BMi/jvXl5W7j5NjLBj79MW/maziy3ii4v0gVoAubyiirVmoggvnGVv8ctBdFJPSbqXu7961PUAB4QfbiRxDbdgJS67ymQH0xffzg/ZTnYR+IBh0CD8izY+wlQggfo8cscRpygPNFSzSmgF4f5DgZmZ1QvGEpVzlEtjASrfUCgYA3sdoDVnkTo12TkWdPPlwkxovXTMeIJ62pu1z4cRMK4OK9LMK+laMfkMmjsZdDHpeVDXxCuVDY8AikGWgW8jewQ3M1rW/gIT1TRh7XExn92TsPFvrB8kjFWdSW1NOI1w8AyMTmgkP0Ph7TbRPx2YYXfDaw9YHsNsS/rkWb0DFl5QKBgG652hpwOYVJZxfvRGgNR2yTF+ppjoxXh/2o6skXdtLKE7bO5vc2E3i2BWwwvboHPbo2C9+VFJ4TVjsqEKpYxpiulVCvtUtc7qV045OXpOPkGf0i78yC0AzEQCN9T8f7PKqBo+WdT8xBu36/8fo5Wcpz8qCPOKSP/j5L2ph9cD2lAoGALBRhhH2SHIWVIh5vwdeY+RY9m5BFZdgejoydX7hL/EWN9yL2v6bXM6ww3qW3scScihKgqcUpALwtLA/mZQWA0a6RBoldu/PF2orP1yJzPVCyCPCt85SnmFwZp3ENCHk5/eRTwUZyfKM3uQcZOmWOEEO59bsydzjrFrbiyHGPj68=";
    //工行网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //加密方式
    protected static final String ENCRYPT_TYPE = "";
    //加密密钥
    protected static final String ENCRYPT_KEY = "";
    //请求url
    protected static final String REQ_URL = "https://apipcs3.dccnet.com.cn/api/cardbusiness/szfhwebservice/batchacp/query/V1";
    //签名类型
    protected static final String SIGN_TYPE = "RSA2";
    //编码格式
    protected static final String CHARSET = "UTF-8";
    //报文类型
    protected static final String FORMAT = "json";
    //应用编号
    protected static final String APP_ID = "10000000000000202060";
    //平台编号
    protected static final String PLATFORM_ID = "100001";

    public static void main(String[] args) {
    	
        CardbusinessSzfhwebserviceBatchacpQueryRequestBizV1 bizContent = new CardbusinessSzfhwebserviceBatchacpQueryRequestBizV1();
        bizContent.setCorpno("B01500000002");
        String requestxml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
			        		"<BizTransQuery xmlns=\"szfs.tws.actqry.biztransquery.v2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
				        		"<querytype>1</querytype>" +
				        		"<omsgtype>BATCHACP</omsgtype>" +
				        		"<oversion>2.0</oversion> " +
				        		"<osubnode>B01500000002</osubnode>" +
				        		"<oseqid>2021032610000000002</oseqid>" +
			        		"</BizTransQuery>";
        bizContent.setRequestxml(requestxml);

        CardbusinessSzfhwebserviceBatchacpQueryRequestV1 request = new CardbusinessSzfhwebserviceBatchacpQueryRequestV1();
        request.setServiceUrl(REQ_URL);
        request.setBizContent(bizContent);

        System.out.println("req=" + JSON.toJSONString(bizContent));

        CardbusinessSzfhwebserviceBatchacpQueryResponseV1 response = null;
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, CHARSET, FORMAT,
                APIGW_PUBLIC_KEY, ENCRYPT_TYPE, ENCRYPT_KEY, "", "");
        try {
            response = client.execute(request, UUID.randomUUID().toString());
            System.out.println("resp=" + JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败,return_code=" + response.getReturnCode() + ",return_msg=" + response.getReturnMsg());
        }
    }

}

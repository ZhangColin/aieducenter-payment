package com.icbc.api;


import com.icbc.api.request.ProductPriceQueryRequestV1;
import com.icbc.api.response.ProductPriceQueryResponseV1;

public class ProductPriceQueryV1Test {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/product/price/query/V3
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        ProductPriceQueryRequestV1 request = new ProductPriceQueryRequestV1();
        request.setServiceUrl(BASE_URL);

        ProductPriceQueryRequestV1.RequestBiz bizContent = new ProductPriceQueryRequestV1.RequestBiz();

        //构建 chanCommV10 请求信息
        ProductPriceQueryRequestV1.ChanCommV10 chanCommV10 = new ProductPriceQueryRequestV1.ChanCommV10();
        //构建 infoCommV10 请求信息
        ProductPriceQueryRequestV1.InfoCommV10 infoCommV10 = new ProductPriceQueryRequestV1.InfoCommV10();
        //构建 inqCommV10 请求信息
        ProductPriceQueryRequestV1.InqCommV10 inqCommV10 = new ProductPriceQueryRequestV1.InqCommV10();
        //构建 privateArea 请求信息
        ProductPriceQueryRequestV1.ProductPriceBpServiceQryForSoctPrivateArea privateArea = new ProductPriceQueryRequestV1.ProductPriceBpServiceQryForSoctPrivateArea();
        ProductPriceQueryRequestV1.ProductPriceBpServiceQryForSoctPrivateAreaRow privateAreaRow = new ProductPriceQueryRequestV1.ProductPriceBpServiceQryForSoctPrivateAreaRow();

        bizContent.setChanCommV10(chanCommV10);
        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setInqCommV10(inqCommV10);
        bizContent.setPrivateArea(privateArea);
        bizContent.setPrivateAreaRow(privateAreaRow);
        request.setBizContent(bizContent);
        ProductPriceQueryResponseV1 response = client.execute(request);
        if (response.isSuccess()) {
            // 成功
        } else {
            // 失败
        }
    }
}

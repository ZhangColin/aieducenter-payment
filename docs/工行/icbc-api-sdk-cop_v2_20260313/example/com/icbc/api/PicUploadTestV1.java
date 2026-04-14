package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftApiLoanPicUploadRequestV1;
import com.icbc.api.response.JftApiLoanPicUploadResponseV1;

public class PicUploadTestV1 {

    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    protected static final String APP_ID = "10000000000000004207";
    String host = "[需替换]工行API网关地址";
    String apiUrl= host+"/api/jft/api/loan/pic/upload/V1";
    String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";


    public void test_cop() throws Exception {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
                "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

        JftApiLoanPicUploadRequestV1 request = new JftApiLoanPicUploadRequestV1();
        request.setServiceUrl(apiUrl);

        JftApiLoanPicUploadRequestV1.JftApiLoanPicUploadRequestV1Biz bizContent = new JftApiLoanPicUploadRequestV1.JftApiLoanPicUploadRequestV1Biz();

        String imageFile = "/92j12/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYE...";

        bizContent.setImageFile(imageFile);
        bizContent.setAppId(APP_ID);
        bizContent.setOutVendorId("123456789001");
        bizContent.setImageType("1");

        request.setBizContent(bizContent);

        JftApiLoanPicUploadResponseV1 response;
        try {
            response =  (JftApiLoanPicUploadResponseV1) client.execute(request, System.currentTimeMillis()+"");

            System.out.println(JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                //成功
                System.out.println(response.getReturnCode());
            } else {
                //失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new PicUploadTestV1().test_cop();
    }
}

package com.icbc.api;

import com.icbc.api.request.MybankCardbusinessServiceTicketimageuploadRequestV1;
import com.icbc.api.response.MybankCardbusinessServiceTicketimageuploadResponseV1;

import java.util.ArrayList;
import java.util.HashMap;

public class MybankCardbusinessServiceTicketimageuploadTest {

    public static void main(String[] args) {

        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
        String APP_ID = "请使用正确的APPID";
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        MybankCardbusinessServiceTicketimageuploadRequestV1 request = new MybankCardbusinessServiceTicketimageuploadRequestV1();
        request.setServiceUrl("http://ip:port/api/copbig/mybank/cardbusiness/service/ticketimageupload/V1");
        MybankCardbusinessServiceTicketimageuploadRequestV1.MybankCardbusinessServiceTicketimageuploadResponseV1Biz bizContent = new MybankCardbusinessServiceTicketimageuploadRequestV1.MybankCardbusinessServiceTicketimageuploadResponseV1Biz();
        bizContent.setSerno("360200012345"); // 示例值
        bizContent.setTicketNo("00000000000000000000000000000001"); // 示例值
        bizContent.setImageType("1"); // 创建工单的影像
        bizContent.setImageList(new ArrayList<>());
        bizContent.getImageList().add(new HashMap<>());
        bizContent.getImageList().get(0).put(
                "imageName",
                "test.jpg" // 影像名称，支持jpeg,jpg,gif,png,bmp,pdf；每个文件名除扩展名外最长20个字符，允许数字、英文字母、英文短横杠-、英文下划线_、中文破折号—
        );
        bizContent.getImageList().get(0).put(
                "image",
                "9j/4AAQSkZJRgABAQAAAQABAAD/4gIoSUNDX1BST0ZJTEUAAQEAAA" // 示例值；请勿在头部拼接类似“data:image/jpeg;base64,”的MIME前缀
        );
        request.setBizContent(bizContent);

        try{
            MybankCardbusinessServiceTicketimageuploadResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println(" success:" + response.getReturnCode() + response.getReturnMsg());
            } else {
                System.out.println(" fail:" + response.getReturnCode() + response.getReturnMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

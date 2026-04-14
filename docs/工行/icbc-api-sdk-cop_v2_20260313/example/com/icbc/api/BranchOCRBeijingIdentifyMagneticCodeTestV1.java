package example.com.icbc.api;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BranchOCRBeijingIdentifyMagneticCodeRequestV1;
import com.icbc.api.response.BranchOCRBeijingIdentifyMagneticCodeResponseV1;

import java.util.Random;
import java.util.logging.Logger;

public class BranchOCRBeijingIdentifyMagneticCodeTestV1 {
    //签名私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static final String APP_ID = "10000000000000012345";

    static Logger log = Logger.getLogger(BranchOCRBeijingIdentifyMagneticCodeTestV1.class.getName());
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        BranchOCRBeijingIdentifyMagneticCodeRequestV1 req = new BranchOCRBeijingIdentifyMagneticCodeRequestV1();

        req.setServiceUrl("https://ip:port/api/branch/ocr/beijing/identifymagneticcode/V1");
        BranchOCRBeijingIdentifyMagneticCodeRequestV1.BranchOCRBeijingIdentifyMagneticCodeRequestV1Biz bizContent = new BranchOCRBeijingIdentifyMagneticCodeRequestV1.BranchOCRBeijingIdentifyMagneticCodeRequestV1Biz();
        BranchOCRBeijingIdentifyMagneticCodeRequestV1.TransinfoBiz transInfo = new BranchOCRBeijingIdentifyMagneticCodeRequestV1.TransinfoBiz();
        transInfo.setImg("data:image/jpg;base64,图片编码");
        bizContent.setTransInfoBiz(transInfo);
        req.setBizContent(bizContent);
        BranchOCRBeijingIdentifyMagneticCodeResponseV1 response;

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        try {
            response = client.execute(req,msgId);
            log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getStatus():"+response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():"+response.getReturnCode());
                log.fine("response.getReturnMsg():"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

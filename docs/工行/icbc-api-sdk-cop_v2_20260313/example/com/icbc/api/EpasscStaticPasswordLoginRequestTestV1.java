package com.icbc.api;

import com.icbc.api.request.EpasscStaticPasswordLoginRequestV1;
import com.icbc.api.request.EpasscStaticPasswordLoginRequestV1.EpasscStaticPasswordLoginRequestV1Biz;




//B2B商城垂直电商第三方订单提交页（页面）

/**
 * Servlet implementation class SubmitOrderServlet
 */
public class EpasscStaticPasswordLoginRequestTestV1 {


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(data, data_sign, appflag,algorithm);
        EpasscStaticPasswordLoginRequestV1 request = new EpasscStaticPasswordLoginRequestV1();
        request.setServiceUrl("https://domain:port/ui/epassc/static/password/login/V1");
        EpasscStaticPasswordLoginRequestV1Biz bizContent = new EpasscStaticPasswordLoginRequestV1Biz();

        bizContent.setData("934d21731ab76d3e47a6d5506af77f99e639c6bc6f93884a9883c2c6c8f7775865b5d08f234ac0c999caabec0ebdc3911bc8110744e5b327510cf9279f512f3ace780fc54d66a4199d7df78e09408b25e918a9bff9610b4627678df5f1b2cc71a1ca884e7b469ecc7cb2aba58cef54b9d52e7c0be4ba3e2dd70f387efe6cbb4a36e9388da2e11872d679857e4483a77f7a68483da6380c9ac7768c5d84d62d253679faca307110191749b3469bd1ea8a3174bd261086f07177ec05258eca1bce9ea016a0ed68f83bcdfc62e66e2558ff267391993a34ed4ac66bfed2f4faee48da8ea23f729eea61509e61e3ab4abceaa385537a0b8293054d30823d6e17803d7ccd2485d4878a4228c5e3afd7710ee8fbd8cbdd0d5631b8871ea45a9a0230663bdfdfd7a32c7dd7664befcbb310926931cd2f745be395294f5892f5776eed9a8e2ab9ab5e2a89ef9bc421158bdd873c");
        bizContent.setdataSign("30450221009a4f91cb13402cfe289c3970970a3c19ca565a313a0f0a92926624f91553743602206ce6d02bc91ade10d09c1f8c5f89e0c42e55a269947e3df861aee05a12d6839c");
        bizContent.setappflag("PCMS");
        bizContent.setalgorithm("1");
        request.setBizContent(bizContent);
        try {
            PI00217ResponseV1 response = client.execute(request, "msgId");
            if (response.isSuccess()) {
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}

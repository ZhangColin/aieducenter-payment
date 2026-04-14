package com.icbc.api;

import com.icbc.api.request.JftApiUserEdpDealHitRaexRiskRequestV1;
import com.icbc.api.response.JftApiUserEdpDealHitRaexRiskResponseV1;
import com.icbc.api.request.JftApiUserEdpDealHitRaexRiskRequestV1.JftApiUserEdpDealHitRaexRiskRequestV1Biz;
import com.icbc.api.request.JftApiUserEdpDealHitRaexRiskRequestV1.JftApiUserEdpDealHitRaexRiskRequestV1Biz.RaexHitImportVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JftApiUserEdpDealHitRaexRiskTestV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(JftApiUserEdpDealHitRaexRiskTestV1.class);

    // FIXME：此处替换工行API网关公钥，请向工行联系获取
    private static final String APIGW_PUBLIC_KEY = "[需替换]此处替换工行API网关公钥，请向工行联系获取";

    // FIXME: 此处替换合作方 APPID
    protected static final String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";

    // FIXME：此处替换合作方 APPID 对应私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    // FIXME：此处替换合作方 AES 加密秘钥
    private static final String AES_Key = "[需替换]此处替换合作方 AES 加密秘钥";

    public static void main(String[] args) throws Exception {
        // 签名类型为RSA2时，需传入appId，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "utf-8", "json", APIGW_PUBLIC_KEY, "AES", AES_Key, "", "");
        JftApiUserEdpDealHitRaexRiskRequestV1 request = new JftApiUserEdpDealHitRaexRiskRequestV1();

        // 根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/user/edpreceive/raexrisk/deal/V1");

        // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiUserEdpDealHitRaexRiskRequestV1Biz bizContent = new JftApiUserEdpDealHitRaexRiskRequestV1Biz();
        List<RaexHitImportVO> raexHitImportVOList = new ArrayList<>();
        RaexHitImportVO raexHitImportVO = new RaexHitImportVO();
        raexHitImportVO.setOutUserId("20221128lbc005");
        raexHitImportVO.setBusNo("20230525172848731");
        raexHitImportVO.setPlatformDealRes("01");
        raexHitImportVO.setPlatformDealReason("涉及风险，请行方冻结");
        raexHitImportVOList.add(raexHitImportVO);
        bizContent.setRaexHitImportVOList(raexHitImportVOList);
        bizContent.setAppId(APP_ID);
        request.setBizContent(bizContent);

        JftApiUserEdpDealHitRaexRiskResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                // 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                LOGGER.info("ReturnCode:"+response.getReturnCode());
            } else {
                // 失败
                LOGGER.error("ReturnCode:"+response.getReturnCode());
            }
        } catch (IcbcApiException e) {
            LOGGER.error("IcbcApiException e=", e);
        }
    }
}

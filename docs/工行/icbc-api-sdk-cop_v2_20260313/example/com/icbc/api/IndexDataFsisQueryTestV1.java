package com.icbc.api;

import com.icbc.api.request.IndexDataFsisQueryRequestV1;
import com.icbc.api.response.IndexDataFsisQueryResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth kfzx-xiehr
 * @date 2025/3/6
 * @time 18:21
 */
public class IndexDataFsisQueryTestV1 {

        //1、网关公钥
        protected static final String APIGW_PUBLIC_KEY = "[需替换]此处替换成 网关公钥";

        //2、appid
        protected static final String APP_ID = "[需替换]此处替换合作方 APPID";

        //3、密钥对认证方式，公钥在API平台登记，此处为私钥
        protected static final String MY_PRIVATE_KEY = "[需替换]此处替换合作方 APPID 对应私钥";

        public static void main(String[] args) throws Exception {

            //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

            IndexDataFsisQueryRequestV1 request = new IndexDataFsisQueryRequestV1();

            //4、根据测试环境和生产环境替换相应ip和端口
            request.setServiceUrl("http://[需替换]此处替换网关域名/api/index/data/fsis/query/V1");

            //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            IndexDataFsisQueryRequestV1.IndexDataFsisQueryRequestV1Biz bizContent = new IndexDataFsisQueryRequestV1.IndexDataFsisQueryRequestV1Biz();
            bizContent.setAppcode("F-DAM-DAO");
            bizContent.setUserid("888802545");

            List<IndexDataFsisQueryRequestV1.IndexDataFsisQueryRequestV1BizMetainfo> metainfo = new ArrayList<>();
            IndexDataFsisQueryRequestV1.IndexDataFsisQueryRequestV1BizMetainfo metainfo1 = new IndexDataFsisQueryRequestV1.IndexDataFsisQueryRequestV1BizMetainfo();
            metainfo1.setCurrency("701");
            metainfo1.setDatadate("20230301");
            metainfo1.setIndexid("010130100002264");
            metainfo1.setOrgancode("0010100000");
            bizContent.setMetainfo(metainfo);

            request.setBizContent(bizContent);

            IndexDataFsisQueryResponseV1 response;
            try {
                response = client.execute(request, "100023");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
                if (response.isSuccess()) {
                    //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                    System.out.println("ReturnCode:"+response.getReturnCode());
                    System.out.println("response:" + response);
                } else {
                    //失败
                    System.out.println("ReturnCode:"+response.getReturnCode());
                    System.out.println("ReturnMsg:"+response.getReturnMsg());
                }
            } catch (IcbcApiException e) {
                // log.error("");
            }
        }

}

package com.icbc.api;

//import java.util.List;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
//import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BillResultRequestV1;
import com.icbc.api.response.BillResultResponseV1;
//import com.icbc.api.response.BillResultResponseV1.QueryFormBillDataInfo;
//import com.icbc.api.utils.SM4Util;
//import java.util.List;


/**
 * 根据账单号查询账单缴费结果api接口示例
 */
public class BcssMarketQueryBillResultTest {
    public final static String URL = new String("/api/bcss/query/bill/result/V1");
    protected static final String MY_PRIVATE_KEY = "xxxxxxx";
    protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
    protected static final String APP_ID = "xxxx";
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = "xxxxxxx";
    private final static String SIGN_TYPE = "xxxxxxx";

    private final static String SM4_KEY= "xxxx";
    private final static String SM4_IV= "xxxx";

    public static void main(String[] args) throws Exception {
        //requestV1();
        //requestV2();
        requestV3();
    }

/*    public static void requestV1() throws IcbcApiException {
    	BillResultRequestV1 request = new BillResultRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);

        BillResultRequestV1.BcssMarketQueryBillResultRequestBizV1 bizContent = new BillResultRequestV1.BcssMarketQueryBillResultRequestBizV1();
        bizContent.setCorpId("2000000300"); // 企业编号
        bizContent.setOutBillNo("SF04295");//三方账单号

        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        IcbcResponse response = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }

    public static void requestV2() throws IcbcApiException {
    	BillResultRequestV1 request = new BillResultRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);

        BillResultRequestV1.BcssMarketQueryBillResultRequestBizV1 bizContent = new BillResultRequestV1.BcssMarketQueryBillResultRequestBizV1();
        bizContent.setCorpId("2000000300"); // 企业编号
        bizContent.setQueryType("1");//查询类型 1-查询消费项账单
        bizContent.setMchPayConfId("3091");//缴费项编号
        bizContent.setPayStatus(4);//支付状态 0-未支付1- 已支付
        bizContent.setBeginDate("2024-01-01");//开始日期
        bizContent.setEndDate("2024-12-01");//结束日期
        bizContent.setPageSize(10);//页面数量
        bizContent.setPageNum(1);//页码

        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        IcbcResponse execute = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(execute));
    }*/

    public static void requestV3() throws Exception {
    	BillResultRequestV1 request = new BillResultRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);

        BillResultRequestV1.BcssMarketQueryBillResultRequestBizV1 bizContent = new BillResultRequestV1.BcssMarketQueryBillResultRequestBizV1();
        bizContent.setCorpId("2000000031"); // 企业编号
        bizContent.setQueryType("2");//查询类型：1-查询消费项账单;2-查询表单账单
        bizContent.setBusiTableName("TB_202531015291");//表单编号
        bizContent.setPayStatus(1);//支付状态：0-未支付；1- 已支付；3-已手动退款；4-已关闭；5-已部分退款；6-已全部退款
        bizContent.setBeginDate("2025-01-01");//开始日期
        bizContent.setEndDate("2025-12-31");//结束日期
        bizContent.setPageSize(10);//页面数量
        bizContent.setPageNum(1);//页码

        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BillResultResponseV1 response = client.execute(request);
        System.out.println("解密前返回示例：" + JSONObject.toJSONString(response));
        //取到加密字段按下方方法对其解密
//        QueryFormBillDataInfo data = (QueryFormBillDataInfo) response.getData();
//        BillResultResponseV1.QueryFormBillDataInfo queryFormBillDataInfo = JSON.parseObject(JSON.toJSONString(data), BillResultResponseV1.QueryFormBillDataInfo.class);
//        List<BillResultResponseV1.QueryFormBillItemInfo> payList = queryFormBillDataInfo.getPayList();
//        for (BillResultResponseV1.QueryFormBillItemInfo queryFormBillItemInfo : payList) {
//            String configFields = queryFormBillItemInfo.getConfigFields();
//            String decryptedConfigFields = SM4Util.decrypt_Cbc_Padding(SM4_KEY, SM4_IV, configFields);
//            queryFormBillItemInfo.setConfigFields(decryptedConfigFields);
//        }
//        queryFormBillDataInfo.setPayList(payList);
//        response.setData(queryFormBillDataInfo);
//        System.out.println("解密后返回示例：" + JSONObject.toJSONString(response));
    }

}

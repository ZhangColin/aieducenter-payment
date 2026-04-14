package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateRequestV1;
import com.icbc.api.request.MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateRequestV1.MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateRequestBizV1;
import com.icbc.api.response.MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateResponseV1;


/**
 * @author kfzx-renqh
 *
 * 企业财务室指令查询接口(按时间)
 */
public class MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String URI = "mybank/enterprise/salary/financialoffice/mesfinstrqrybydate/V1"; // api地址
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateRequestBizV1 bizContent =
                    new MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateRequestBizV1();
            MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateRequestV1 request = new MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("QPAYPERTM");
            bizContent.setCis("");
            bizContent.setBankCode("");
            bizContent.setLoginId("");
            bizContent.setTranDate("");
            bizContent.setTranTime("");
            bizContent.setfSeqno("");
            
            bizContent.setBeginTime("");
            bizContent.setEndTime("");
            
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            // 输出支付指令提交响应对象，需根据实际调用服务更换（MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateResponseV1）
            MybankEnterpriseSalaryFinancialofficeMesfinstrqrybydateResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");//
            } else {
                // 失败
                System.out.println("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

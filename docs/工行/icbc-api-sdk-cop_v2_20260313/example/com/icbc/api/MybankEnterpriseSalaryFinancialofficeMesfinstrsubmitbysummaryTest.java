package com.icbc.api;

import java.util.ArrayList;

import com.icbc.api.request.MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestV1;
import com.icbc.api.request.MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestV1.MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestBizV1;
import com.icbc.api.request.MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestV1.MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestBizV1.RequestRdV1;
import com.icbc.api.response.MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryResponseV1;


/**
 * @author kfzx-renqh
 *
 * 企业财务室指令提交汇总记账
 */
public class MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String URI = "mybank/enterprise/salary/financialoffice/mesfinstrsubmitbysummary/V1"; // api地址
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {

            MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestBizV1 bizContent =
                    new MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestBizV1();
            MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestV1 request = new MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("PAYPERCOL");
            bizContent.setCis("");
            bizContent.setBankCode("");
            bizContent.setLoginId("");
            bizContent.setTranDate("");
            bizContent.setTranTime("");
            bizContent.setfSeqno("");
            
            bizContent.setOnlBatF("");
            bizContent.setBusType("");
            bizContent.setTotalNum(1);  
            bizContent.setTotalAmt((long) 3013);
            bizContent.settHBaseAccFlag("");
            // rd
            ArrayList<RequestRdV1> rd = new ArrayList<RequestRdV1>();
            RequestRdV1 rdV1 = new RequestRdV1();
            rdV1.setiSeqno("");
            rdV1.setPayType("");
            rdV1.setPayAccNo("");
            rdV1.setPayAccNameCN("");
            rdV1.setRecAccNo("");
            rdV1.setRecAccNameCN("");
            rdV1.setSysIOFlg("");
            rdV1.setIsSameCity("");
            rdV1.setRecICBCCode("");
            rdV1.setRecBankNo("");
            rdV1.setRecBankName("");
            rdV1.setCurrType("");
            rdV1.setPayAmt((long) 3013);
            rdV1.setUseCode("");
            rdV1.setUseCN("");
            rdV1.setEnSummary("");
            rdV1.setPostScript("");
            rdV1.setSummary("");
            rd.add(rdV1);
            bizContent.setRd(rd);
            
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            // 输出支付指令提交响应对象，需根据实际调用服务更换（MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryResponseV1）
            MybankEnterpriseSalaryFinancialofficeMesfinstrsubmitbysummaryResponseV1 response = client.execute(request);
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

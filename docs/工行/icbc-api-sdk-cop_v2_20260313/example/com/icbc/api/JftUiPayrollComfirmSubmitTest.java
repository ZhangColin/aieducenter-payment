package com.icbc.api.test;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiPayrollComfirmSubmitRequestV1;

public class JftUiPayrollComfirmSubmitTest {
    public static void main(String[] args) throws IcbcApiException {

        /* 使用工行sdk在后端调用api页面服务 */
        // 构造client对象
        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        JftUiPayrollComfirmSubmitRequestV1 request = new JftUiPayrollComfirmSubmitRequestV1();



        request.setServiceUrl("https://ip:port/ui/jft/ui/payroll/comfirmsubmit/V1");
        JftUiPayrollComfirmSubmitRequestV1.JjftUiPayrolComfirmSubmitRequestV1BizV1 bizContent = new JftUiPayrollComfirmSubmitRequestV1.JjftUiPayrolComfirmSubmitRequestV1BizV1();


        bizContent.setAppId("10000000000000365010");
        bizContent.setAppName("盖亚");
        bizContent.setAppRemark("202112243");
        bizContent.setAppSerialno("1");
        bizContent.setFileMdcode("5015");
        bizContent.setFileType("5");
        bizContent.setLocalFilepath("123456789");
        bizContent.setOprType("D:/2022/1月版/代发工资确认提交文件/202112243_20220109t17_0_20211224.xls");
        bizContent.setOutVendorId("1");
        bizContent.setTotalAmt("备注");
        bizContent.setTotalCount("10");
        bizContent.setBusinessType("0");

        request.setBizContent(bizContent);

        client.buildPostForm(request);

    }
}

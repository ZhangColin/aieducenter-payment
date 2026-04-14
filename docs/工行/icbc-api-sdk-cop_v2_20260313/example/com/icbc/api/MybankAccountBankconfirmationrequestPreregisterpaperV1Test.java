package com.icbc.api;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.icbc.api.request.MybankAccountBankconfirmationrequestPreregisterpaperRequestV1;
import com.icbc.api.request.MybankAccountBankconfirmationrequestPreregisterpaperRequestV1.ConfirmInfo;
import com.icbc.api.request.MybankAccountBankconfirmationrequestPreregisterpaperRequestV1.MybankAccountBankconfirmationrequestPreregisterpaperRequestV1Biz;
import com.icbc.api.response.MybankAccountBankconfirmationrequestPreregisterpaperResponseV1;

/**
 * 纸质函证预登记
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */

public class MybankAccountBankconfirmationrequestPreregisterpaperV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "test";

	// 2、appid
	protected static final String APP_ID = "test";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "test";
	
	
	public void test() throws Exception {
		String msg_id = UUID.randomUUID().toString();
		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankAccountBankconfirmationrequestPreregisterpaperRequestV1 request = new MybankAccountBankconfirmationrequestPreregisterpaperRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/account/bankconfirmationrequest/preregisterpaper/V1");

		MybankAccountBankconfirmationrequestPreregisterpaperRequestV1Biz bizContent = new MybankAccountBankconfirmationrequestPreregisterpaperRequestV1Biz();
		 //封装的set方法名与通讯区表格描述中的字段名略有不同，如使用，请按如下set方法使用。
	    bizContent.setRequestType("1");//询证类型
		bizContent.setExpressId("SF123464654");//来函快递编号
		bizContent.setLetterId("202307180000001");//来函业务编号
		bizContent.setCiName("XXX有限公司");//被函证单位名称
		bizContent.setApplicant("XXX有限公司");//来函证单位名称
		bizContent.setPayAccno("0200026009000000146");//付费账号
		bizContent.setConfirmDate("2023-07-18");//询证基准日期
		bizContent.setAddress("jhhhhhhh");//回函地址
		bizContent.setContacts("李四");//回函收件人名称
		bizContent.setPhone("12312125125");//回函收件人联系电话
		bizContent.setSubmitter("张三");//函证单位提交人
		bizContent.setIpAddress("000.000.000.000");//发起方IP
	    ConfirmInfo conlist = new ConfirmInfo();
	     List<ConfirmInfo> creInfoList = new ArrayList<ConfirmInfo>();			       
         for(int i=0;i<1;i++){
        	 conlist.setConfirmationType("1");//函证种类
        	 conlist.setConditionNO("0200062009213110737");//函证条件（账号）
        	 conlist.setConfirmBeginDate("2019-05-02");//函证起始日期
        	 conlist.setConfirmEndDate("2019-05-02");//函证截止日期
        	 creInfoList.add(conlist);
         }
         bizContent.setList(creInfoList);
		 request.setBizContent(bizContent);

		MybankAccountBankconfirmationrequestPreregisterpaperResponseV1 response = new MybankAccountBankconfirmationrequestPreregisterpaperResponseV1();
		try {
			response = client.execute(request, String.valueOf(msg_id));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if ("0".equals(response.getErrorNo())) {
                // 业务成功处理
            } else {
                // 失败
            }
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

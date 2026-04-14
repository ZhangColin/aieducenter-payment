package com.icbc.sisTest.apiTest;

import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.exception.ConfigException;
import com.icbc.apip.exception.InvokerException;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker;
import com.icbc.apip.invoker.Invokers;

import java.util.Map;

public class MybankPayInsuranceCarOrderCommitV1Test {

	public static void main(String[] args){
		System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");
		String inputJson="";

		// 获取调用对象；设置服务对应的uri、调用方式、调用参数；
		DefaultInvoker invoker = Invokers.getDefaultInvoker();
		invoker.setUri("/mybank/pay/insurance/car/ordercommit/V1");
		invoker.setMethod(Invoker.HttpMethodType.POST);
		// 设置报文体参数
		try {

			//inputJson ="[{\"biz_content\":{\"trans_req_info\":{\"cosp\":\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><COSP><PUBLIC><APP_CODE>ASPS</APP_CODE><TRX_CODE>6004</TRX_CODE><CHANNEL_NO>303</CHANNEL_NO><ZONENO>00200</ZONENO><BRNO>00620</BRNO><TELLERNO>00920</TELLERNO></PUBLIC><PRIVATE><COSP_MSG><![CDATA[<?xml version=\\\"1.0\\\"?><ASPS><PRIVATE><ACCTYPE>007</ACCTYPE><AMT>20000</AMT><APPLYNO>20201018100002602063</APPLYNO><BUSIID>BUSIID39383038</BUSIID><CHECKANSWER></CHECKANSWER><CREDITNO>20000020013</CREDITNO><CUSTRISKLV>6</CUSTRISKLV><FEEDBACKRATE></FEEDBACKRATE><INSTALLMENTINFO><DISCOUNTFLAG>1</DISCOUNTFLAG><EACHAMT></EACHAMT><EACHFEE></EACHFEE><FEERATE></FEERATE><FIRSTAMT></FIRSTAMT><FIRSTFEE></FIRSTFEE><PERIODS>3</PERIODS><TOTALFEE></TOTALFEE></INSTALLMENTINFO><PAYACC>6222350050677812</PAYACC><PAYACCBRNO>00620</PAYACCBRNO><PAYACCNAME>河哗</PAYACCNAME><PAYACCZONENO>00200</PAYACCZONENO><SALERNO></SALERNO></PRIVATE><PUBLIC><CHLTRANSDATE>20210630</CHLTRANSDATE><CHLTRANSSEQ>20200701100002593831</CHLTRANSSEQ><CHLTRANSTIME>105644</CHLTRANSTIME><INSUID>110</INSUID><TERMINAL></TERMINAL></PUBLIC></ASPS>]]></COSP_MSG></PRIVATE></COSP>\",\"appcode\":\"ASPS\",\"corpcode\":\"301\"},\"common_req_info\":{\"ServiceAlias\":\"queryContractDetail\"}}}]";
			inputJson = "{\"trans_req_info\":{\"SOURCE\":\"0000\",\"ORDERNO\":\"2451154545\"},\"common_req_info\":{\"ServiceAlias\":\"orderCommit\"}}";
			invoker.addParameter("biz_content", inputJson);

			System.out.println(invoker);
			// 同步调用，并获取响应信息，供调用方后续使用
			Map<String,Object> resp = invoker.syncInvoke();

			System.out.println("返回信息:"+resp);
			//采用配置文件方式调用请使用如下代码

			//业务处理返回
			System.out.println("业务处理返回return_code:"+resp.get("return_code"));
			System.out.println("业务处理返回return_msg:"+resp.get("return_msg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				Invokers.shutdown();
			} catch (InvokerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConfigException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

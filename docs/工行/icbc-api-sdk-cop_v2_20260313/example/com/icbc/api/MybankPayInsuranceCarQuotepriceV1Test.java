package com.icbc.sisTest.apiTest;

import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.exception.ConfigException;
import com.icbc.apip.exception.InvokerException;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker;
import com.icbc.apip.invoker.Invokers;
import java.util.Map;

public class MybankPayInsuranceCarQuotepriceV1Test {

	public static void main(String[] args){
		System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");
		String inputJson="";

		// 获取调用对象；设置服务对应的uri、调用方式、调用参数；
		DefaultInvoker invoker = Invokers.getDefaultInvoker();
		invoker.setUri("/mybank/pay/insurance/car/quoteprice/V1");
		invoker.setMethod(Invoker.HttpMethodType.POST);
		// 设置报文体参数
		try {

			//inputJson ="[{\"biz_content\":{\"trans_req_info\":{\"cosp\":\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><COSP><PUBLIC><APP_CODE>ASPS</APP_CODE><TRX_CODE>6004</TRX_CODE><CHANNEL_NO>303</CHANNEL_NO><ZONENO>00200</ZONENO><BRNO>00620</BRNO><TELLERNO>00920</TELLERNO></PUBLIC><PRIVATE><COSP_MSG><![CDATA[<?xml version=\\\"1.0\\\"?><ASPS><PRIVATE><ACCTYPE>007</ACCTYPE><AMT>20000</AMT><APPLYNO>20201018100002602063</APPLYNO><BUSIID>BUSIID39383038</BUSIID><CHECKANSWER></CHECKANSWER><CREDITNO>20000020013</CREDITNO><CUSTRISKLV>6</CUSTRISKLV><FEEDBACKRATE></FEEDBACKRATE><INSTALLMENTINFO><DISCOUNTFLAG>1</DISCOUNTFLAG><EACHAMT></EACHAMT><EACHFEE></EACHFEE><FEERATE></FEERATE><FIRSTAMT></FIRSTAMT><FIRSTFEE></FIRSTFEE><PERIODS>3</PERIODS><TOTALFEE></TOTALFEE></INSTALLMENTINFO><PAYACC>6222350050677812</PAYACC><PAYACCBRNO>00620</PAYACCBRNO><PAYACCNAME>河哗</PAYACCNAME><PAYACCZONENO>00200</PAYACCZONENO><SALERNO></SALERNO></PRIVATE><PUBLIC><CHLTRANSDATE>20210630</CHLTRANSDATE><CHLTRANSSEQ>20200701100002593831</CHLTRANSSEQ><CHLTRANSTIME>105644</CHLTRANSTIME><INSUID>110</INSUID><TERMINAL></TERMINAL></PUBLIC></ASPS>]]></COSP_MSG></PRIVATE></COSP>\",\"appcode\":\"ASPS\",\"corpcode\":\"301\"},\"common_req_info\":{\"ServiceAlias\":\"queryContractDetail\"}}}]";
			inputJson = "{\n" +
					"    \n" +
					"        \"trans_req_info\":{\n" +
					"        \n" +
					"\t\t\t\"PersonalVehicleInformation\":{\n" +
					"            \"LicenseNo\":\"3435645\",\n" +
					"            \"ModelCode\":\"code001\",\n" +
					"            \"ModelName\":\"奥迪A8L2.4T\",\n" +
					"            \"SingeinDate\":\"20220102\",\n" +
					"            \"EngineNo\":\"432413\",\n" +
					"            \"FrameNo\":\"LKJSLD8239480325\",\n" +
					"            \"ChOwnerFlag\":\"1\",\n" +
					"            \"TransferDate\":\"20180305\",\n" +
					"            \"SaleProv\":\"00101\",\n" +
					"            \"renewInsurerFlag\":\"0\",\n" +
					"            \"VcuDemandNo\":\"\",\n" +
					"            \"VcicheckCode\":\"\",\n" +
					"            \"TcuDemandNo\":\"\",\n" +
					"            \"TciCheckCode\":\"\"\n" +
					"        },\n" +
					"        \"InsuRelatePersion\":{\n" +
					"            \"RelatePersion\":[{\n" +
					"                \"Name\":\"测试名称\",\n" +
					"                \"IdentifyType\":\"2\",\n" +
					"                \"IdentifyNumber\":\"8746349874613\",\n" +
					"                \"Sex\":\"1\",\n" +
					"                \"IdentifyBeginDate\":\"20191014\",\n" +
					"                \"ValidDate\":\"99991231\",\n" +
					"                \"BirthDate\":\"20190306\",\n" +
					"                \"MobilePhone\":\"15068880589\",\n" +
					"                \"EMail\":\"1@qq.com\",\n" +
					"                \"Prov\":\"浙江\",\n" +
					"                \"City\":\"杭州\",\n" +
					"                \"Zone\":\"西湖\",\n" +
					"                \"Address\":\"文一西路70号\",\n" +
					"                \"IdentifyDep\":\"杭州\",\n" +
					"                \"InsuredFlag\":\"01\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"Name\":\"测试名称又\",\n" +
					"                \"IdentifyType\":\"6\",\n" +
					"                \"IdentifyNumber\":\"34648\",\n" +
					"                \"Sex\":\"1\",\n" +
					"                \"IdentifyBeginDate\":\"20100306\",\n" +
					"                \"ValidDate\":\"99991231\",\n" +
					"                \"BirthDate\":\"20191014\",\n" +
					"                \"MobilePhone\":\"15065550258\",\n" +
					"                \"Email\":\"2@qq.com\",\n" +
					"                \"Prov\":\"浙江\",\n" +
					"                \"City\":\"杭州\",\n" +
					"                \"Zone\":\"西湖\",\n" +
					"                \"Address\":\"古翠路地铁站\",\n" +
					"                \"IdentifyDep\":\"杭州\",\n" +
					"                \"InsuredFlag\":\"02\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"Name\":\"春芽\",\n" +
					"                \"IdentifyType\":\"0\",\n" +
					"                \"IdentifyNumber\":\"411322198001011237\",\n" +
					"                \"InsuredFlag\":\"03\"\n" +
					"            }]\n" +
					"        },\n" +
					"        \"BusiKindInfo\":{\n" +
					"            \"kindInfo\":[{\n" +
					"                \"KindAmt\":\"1\",\n" +
					"                \"KindCode\":\"101\",\n" +
					"                \"KindFlag\":\"0\",\n" +
					"                \"KindName\":\"机动车辆损失险\",\n" +
					"                \"NonDeducAmt\":\"1\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"100\",\n" +
					"                \"KindCode\":\"102\",\n" +
					"                \"KindFlag\":\"0\",\n" +
					"                \"KindName\":\"第三者责任险\",\n" +
					"                \"NonDeducAmt\":\"1\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"103\",\n" +
					"                \"KindFlag\":\"0\",\n" +
					"                \"KindName\":\"盗抢险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"104\",\n" +
					"                \"KindFlag\":\"0\",\n" +
					"                \"KindName\":\"车上人员责任险-司机\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"105\",\n" +
					"                \"KindFlag\":\"0\",\n" +
					"                \"KindName\":\"车上人员责任险-乘客\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"201\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"自燃损失险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"202\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"车身划痕损失险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"203\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"指定修理厂险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"204\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"发动机涉水损失险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"205\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"机动车损失保险无法找到第三方特约险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"206\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"精神损害抚慰金责任险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"209\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"修理期间费用补偿险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"210\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"玻璃单独破碎险\",\n" +
					"                \"NonDeducAmt\":\"--\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"211\",\n" +
					"                \"KindFlag\":\"1\",\n" +
					"                \"KindName\":\"新增设备损失险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"1\",\n" +
					"                \"KindCode\":\"301\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"机动车辆损失险\",\n" +
					"                \"NonDeducAmt\":\"1\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"1\",\n" +
					"                \"KindCode\":\"302\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"第三者责任险\",\n" +
					"                \"NonDeducAmt\":\"1\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"303\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"盗抢险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"304\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"车上人员责任险-司机\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"305\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"车上人员责任险-乘客\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"306\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"自燃损失险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"307\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"车身划痕损失险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"308\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"发动机涉水损失险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"309\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"精神损害抚慰金责任险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            },\n" +
					"            {\n" +
					"                \"KindAmt\":\"0\",\n" +
					"                \"KindCode\":\"312\",\n" +
					"                \"KindFlag\":\"2\",\n" +
					"                \"KindName\":\"新增设备损失险\",\n" +
					"                \"NonDeducAmt\":\"0\"\n" +
					"            }]\n" +
					"        },\n" +
					"        \"BusiProdInfo\":{\n" +
					"            \"RiskCode\":\"\",\n" +
					"            \"StartDate\":\"20220103\",\n" +
					"            \"EndDate\":\"20230102\",\n" +
					"            \"SelectFlag\":\"1\"\n" +
					"        },\n" +
					"        \"ForceProdinfo\":{\n" +
					"            \"RiskCode\":\"\",\n" +
					"            \"StartDate\":\"20220103\",\n" +
					"            \"EndDate\":\"20230102\",\n" +
					"            \"SelectFlag\":\"1\",\n" +
					"            \"BeAmt\":\"1\"\n" +
					"        },\n" +
					"        \"tradeFlag\":\"1\",\n" +
					"        \"relatePersionCount\":\"3\",\n" +
					"        \"custGroupCount\":\"0\",\n" +
					"        \"custGroupNumbers\":{\n" +
					"            \"cusinfoNumber\":[]\n" +
					"        },\n" +
					"        \"kindCount\":\"14\",\n" +
					"        \"sourceType\":\"5\",\n" +
					"        \"compensateDayNum\":\"0\",\n" +
					"        \"insuSalerNo\":\"888800201\",\n" +
					"        \"oldOrderNo\":\"\",\n" +
					"        \"custNo\":\"\",\n" +
					"        \"sourceSeriNo\":\"\",\n" +
					"        \"brNo\":\"00000\",\n" +
					"        \"sourcePB\":\"0\",\n" +
					"        \"insuId\":\"109\"\n" +
					"\n" +
					"        \n" +
					"    },\n" +
					"    \n" +
					"        \"common_req_info\":{\n" +
					"        \n" +
					"            \"ServiceAlias\":\"queryContractDetail\"\n" +
					"        \n" +
					"    }\n" +
					"\n" +
					"}";
					//"[{\"trans_req_info\":{\t\t\t\t\t{\\\"PersonalVehicleInformation\\\":{\\\"LicenseNo\\\":\\\"3435645\\\",\\\"ModelCode\\\":\\\"code001\\\",\\\"ModelName\\\":\\\"奥迪A8L2.4T\\\",\\\"SingeinDate\\\":\\\"20220102\\\",\\\"EngineNo\\\":\\\"432413\\\",\\\"FrameNo\\\":\\\"LKJSLD8239480325\\\",\\\"ChOwnerFlag\\\":\\\"1\\\",\\\"TransferDate\\\":\\\"20180305\\\",\\\"SaleProv\\\":\\\"00101\\\",\\\"renewInsurerFlag\\\":\\\"0\\\",\\\"VcuDemandNo\\\":\\\"\\\",\\\"VcicheckCode\\\":\\\"\\\",\\\"TcuDemandNo\\\":\\\"\\\",\\\"TciCheckCode\\\":\\\"\\\"},\\\"InsuRelatePersion\\\":{\\\"RelatePersion\\\":[{\\\"Name\\\":\\\"测试名称\\\",\\\"IdentifyType\\\":\\\"2\\\",\\\"IdentifyNumber\\\":\\\"8746349874613\\\",\\\"Sex\\\":\\\"1\\\",\\\"IdentifyBeginDate\\\":\\\"20191014\\\",\\\"ValidDate\\\":\\\"99991231\\\",\\\"BirthDate\\\":\\\"20190306\\\",\\\"MobilePhone\\\":\\\"15068880589\\\",\\\"EMail\\\":\\\"1@qq.com\\\",\\\"Prov\\\":\\\"浙江\\\",\\\"City\\\":\\\"杭州\\\",\\\"Zone\\\":\\\"西湖\\\",\\\"Address\\\":\\\"文一西路70号\\\",\\\"IdentifyDep\\\":\\\"杭州\\\",\\\"InsuredFlag\\\":\\\"01\\\"},{\\\"Name\\\":\\\"测试名称又\\\",\\\"IdentifyType\\\":\\\"6\\\",\\\"IdentifyNumber\\\":\\\"34648\\\",\\\"Sex\\\":\\\"1\\\",\\\"IdentifyBeginDate\\\":\\\"20100306\\\",\\\"ValidDate\\\":\\\"99991231\\\",\\\"BirthDate\\\":\\\"20191014\\\",\\\"MobilePhone\\\":\\\"15065550258\\\",\\\"Email\\\":\\\"2@qq.com\\\",\\\"Prov\\\":\\\"浙江\\\",\\\"City\\\":\\\"杭州\\\",\\\"Zone\\\":\\\"西湖\\\",\\\"Address\\\":\\\"古翠路地铁站\\\",\\\"IdentifyDep\\\":\\\"杭州\\\",\\\"InsuredFlag\\\":\\\"02\\\"},{\\\"Name\\\":\\\"春芽\\\",\\\"IdentifyType\\\":\\\"0\\\",\\\"IdentifyNumber\\\":\\\"411322198001011237\\\",\\\"InsuredFlag\\\":\\\"03\\\"}]},\\\"BusiKindInfo\\\":{\\\"kindInfo\\\":[{\\\"KindAmt\\\":\\\"1\\\",\\\"KindCode\\\":\\\"101\\\",\\\"KindFlag\\\":\\\"0\\\",\\\"KindName\\\":\\\"机动车辆损失险\\\",\\\"NonDeducAmt\\\":\\\"1\\\"},{\\\"KindAmt\\\":\\\"100\\\",\\\"KindCode\\\":\\\"102\\\",\\\"KindFlag\\\":\\\"0\\\",\\\"KindName\\\":\\\"第三者责任险\\\",\\\"NonDeducAmt\\\":\\\"1\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"103\\\",\\\"KindFlag\\\":\\\"0\\\",\\\"KindName\\\":\\\"盗抢险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"104\\\",\\\"KindFlag\\\":\\\"0\\\",\\\"KindName\\\":\\\"车上人员责任险-司机\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"105\\\",\\\"KindFlag\\\":\\\"0\\\",\\\"KindName\\\":\\\"车上人员责任险-乘客\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"201\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"自燃损失险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"202\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"车身划痕损失险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"203\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"指定修理厂险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"204\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"发动机涉水损失险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"205\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"机动车损失保险无法找到第三方特约险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"206\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"精神损害抚慰金责任险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"209\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"修理期间费用补偿险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"210\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"玻璃单独破碎险\\\",\\\"NonDeducAmt\\\":\\\"--\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"211\\\",\\\"KindFlag\\\":\\\"1\\\",\\\"KindName\\\":\\\"新增设备损失险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"1\\\",\\\"KindCode\\\":\\\"301\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"机动车辆损失险\\\",\\\"NonDeducAmt\\\":\\\"1\\\"},{\\\"KindAmt\\\":\\\"1\\\",\\\"KindCode\\\":\\\"302\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"第三者责任险\\\",\\\"NonDeducAmt\\\":\\\"1\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"303\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"盗抢险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"304\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"车上人员责任险-司机\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"305\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"车上人员责任险-乘客\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"306\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"自燃损失险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"307\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"车身划痕损失险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"308\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"发动机涉水损失险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"309\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"精神损害抚慰金责任险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"},{\\\"KindAmt\\\":\\\"0\\\",\\\"KindCode\\\":\\\"312\\\",\\\"KindFlag\\\":\\\"2\\\",\\\"KindName\\\":\\\"新增设备损失险\\\",\\\"NonDeducAmt\\\":\\\"0\\\"}]},\\\"BusiProdInfo\\\":{\\\"RiskCode\\\":\\\"\\\",\\\"StartDate\\\":\\\"20220103\\\",\\\"EndDate\\\":\\\"20230102\\\",\\\"SelectFlag\\\":\\\"1\\\"},\\\"ForceProdinfo\\\":{\\\"RiskCode\\\":\\\"\\\",\\\"StartDate\\\":\\\"20220103\\\",\\\"EndDate\\\":\\\"20230102\\\",\\\"SelectFlag\\\":\\\"1\\\",\\\"BeAmt\\\":\\\"1\\\"},\\\"tradeFlag\\\":\\\"1\\\",\\\"relatePersionCount\\\":\\\"3\\\",\\\"custGroupCount\\\":\\\"0\\\",\\\"custGroupNumbers\\\":{\\\"cusinfoNumber\\\":[]},\\\"kindCount\\\":\\\"14\\\",\\\"sourceType\\\":\\\"5\\\",\\\"compensateDayNum\\\":\\\"0\\\",\\\"insuSalerNo\\\":\\\"888800201\\\",\\\"oldOrderNo\\\":\\\"\\\",\\\"custNo\\\":\\\"\\\",\\\"sourceSeriNo\\\":\\\"\\\",\\\"brNo\\\":\\\"00000\\\",\\\"sourcePB\\\":\\\"0\\\",\\\"insuId\\\":\\\"109\\\"}}],\"common_req_info\":{\"ServiceAlias\":\"quotePrice\"}}";
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

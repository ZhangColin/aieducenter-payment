package com.icbc.api;

import com.icbc.api.request.InvestmentAssetmanagementProductpurchaseRequestV1;
import com.icbc.api.request.InvestmentAssetmanagementProductpurchaseRequestV1.InvestmentAssetmanagementProductpurchaseRequestV1Biz;
import com.icbc.api.response.InvestmentAssetmanagementProductpurchaseResponseV1;

import java.util.logging.Logger;


public class InvestmentAssetmanagementProductPurchaseTest {
	static Logger log = Logger.getLogger(InvestmentAssetmanagementProductPurchaseTest.class.getName());

	/**
	 * appID
	 */
	protected static final String APP_ID = "id";

	protected static String BASE_URL = "http://ip:port/api";
	/**
	 * 网关公钥
	 */
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16"
			+"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL"
			+"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP"
			+"3CiNgg0W6O3AGqwIDAQAB";
	/**
	 * 应用私钥
	 */
	protected static final String PRI_KEY ="-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDhNCJwBiZgRxDA\n" +
			"6z5N75yC5G6CX4koXDAN3arQ6sXt6KzAflLtPTdOIPLeSd029P5SsNxN4HpUJUlPE\n" +
			"i/QJiGHWlFbusEVOLpQyu7yxWwMnG6FzNhmBEITkYMCyMFi2NtQzpIAsYiDIf54ds\n" +
			"Sl0ZcqozSv6Gekx/k3rYao/OCEIswNeq9z9s7VIrFCB/lLgkoV3dtPnmL4zqu5u/L\n" +
			"kRgHf8KdW+E09cUBZ1HoSqxw6q5YymeLWYOHBRdg+MuGwMnJTGZ9VEDkKX+b4/OQ6\n" +
			"CPskg3iT9gEwEDxCdw0SHqbBd5ur+P4YMMoZOl2FXs8H52wy4ahNWSTy71pm062jo\n" +
			"IdAgMBAAECggEALWElZ3JdypGPA9x/0ZoXX1h4lt1xpggCVICOtLdrkIpvXB7BOws\n" +
			"cNjyq1QlfwOWc9XaDi0wgyjVg0PFFFKCgQB5j1W5ZLyxvAxeHPovrVtmA/jnFI9aJ\n" +
			"gfkoGD27fs1CqE+7C1/Rp6v1OxZgNcD2qUC7yHgQtxuCtMDXysdpXSX+wWy6NFmD5\n" +
			"uekqiToaulVIOhmUeWBM5xNoCilg2cTJTkCUyjoGLKpFzSeyjJ+cSx5RcbYiNOZ8T\n" +
			"tz6tDS8/m1cstVE4hsDUEm6xA0fwKRHYhfszvZa4QEE7C3S1e6oJyYnQd+4zRJp68\n" +
			"fvtlY+d/SSHYI1xbnOxMAs0K3Up3XYQKBgQDxglFLbTZP2oSOy21QKcJRd9cSNAeg\n" +
			"hHO2/7+QUu2HU5e1lvqnCdnhoB7JEvOXCG9XxZ20DXjqMYdO0Ycqfn8xyNw9AYn7M\n" +
			"xG5XaoRHfNRaZLDYIPGkjnVAFBLNZcpgwh6dOVvwv6i/jtPmObHS19ee3mPX6jFjs\n" +
			"GaRyUMMaSc+QKBgQCLaP/xo8Q1zG8sMXeNPxMYzxWui7Y35aX0O0f1UHz//tCzcMh\n" +
			"S7oWpdrSu7inqz68r7/5WpCbe24yZ0C4I0DuZphxpTRXvRYICCaljqeJ+CKtMadMU\n" +
			"aMi+hLnCTuPJKNgYzDkPbt50Hvf3GnkMoyjfgmOw5SadtOs2BriSOd2LRQKBgF1Zt\n" +
			"fqluDydhmd7kkFOIuJCpEtQ+TG+gMvXh0BODaolUZrLRQ+T6zgfIEcwoB9dD0yqAg\n" +
			"86teYwRtDchJaUVq3zqc2a7q6+SreP0NDU9twdJrGGeOkkVF5UxLSNfmR5VamfzZ+\n" +
			"pi6uNAXf+7hVSuQZYS+BzZVUXnRX5MgTIm4xhAoGAZKX62qcEWl7tCHfyOxyFmgyw\n" +
			"N6MQfEylQq2l5vskM+HxJQvQ5OblwxI7omu6AZfNfSg4dK7wjUbuvIGrKpvD28ssi\n" +
			"e2oX2XMVdJJ4/Vb2uhBW9qvFPYNsFa8kgdItwtw8H5I19aQbj+JgHIxYKlPMga+GJ\n" +
			"FoDsX2Zrv1d0SlqQ0CgYAs3kpFxYovuZGMKtWY02znwNBf+5KPP3Ovsg4xdsZNgZZ\n" +
			"Dj4SdyEDDhR4TwMH0gmdHJadDmPVCPFFzokC6CW1loTBdBgIXRQtKqr0ODtWbfY8s\n" +
			"LIl4BC2n/zLIiL5U81IdV3X8JCU+200XUwNWaJOzpxuoBJIj2XzuoRBdpsBIYg==";

	public static void main(String[] args) throws Exception {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, PRI_KEY, APIGW_PUBLIC_KEY);
		try {
			String URI = "investment/assetmanagement/productpurchase/V1";
			// 请求实体
			InvestmentAssetmanagementProductpurchaseRequestV1 request = new InvestmentAssetmanagementProductpurchaseRequestV1();
			// bizContent实体
			InvestmentAssetmanagementProductpurchaseRequestV1Biz bizContent = new InvestmentAssetmanagementProductpurchaseRequestV1Biz();

	
			// 请求报文IN节点实体 设置属性
			bizContent.setAppSheetSerialNo("4501");
			bizContent.setCurrencyType("156");
			bizContent.setFundCode("300001");
			bizContent.setTransactionDate("20200506");
			bizContent.setTransactionAccountID("20110331");
			bizContent.setDistributorCode("G001");
			bizContent.setBusinessCode("022");
			bizContent.settAAccountID("725529001");
			bizContent.setDiscountRateOfCommission("");
			bizContent.setApplicationAmount("1000");
			bizContent.setDepositAcct("");
			bizContent.setRegionCode("0200");
			bizContent.setDateOfPeriodicSubs("");
			bizContent.settASerialNO("");
			bizContent.setBranchCode("0099");
			bizContent.setOriginalAppSheetNo("");
			bizContent.setTransactionTime("130000");
			bizContent.setIndividualOrInstitution("1");
			bizContent.setShareClass("0");
			bizContent.setLargeBuyFlag("");
			bizContent.setVarietyCodeOfPeriodicSubs("");
			bizContent.setTermOfPeriodicSubs("");
			bizContent.setFutureBuyDate("");
			bizContent.setChargeType("1");
			bizContent.setSerialNoOfPeriodicSubs("");
			bizContent.setSpecifyFee("");
			bizContent.setSpecifyRateFee("");
			bizContent.setIPAddress("fe80::c5fe:dc6e:a6b5:60fb%14");
			bizContent.setMACAddress("98-FA-9B-42-3A-D6");
			bizContent.setIMEI("IMEI1234567890");
			bizContent.setUUID("UUID124567891");
			bizContent.setAcctNameOfInvestorInClearingAgency("");
			bizContent.setAcctNoOfInvestorInClearingAgency("");
			bizContent.setClearingAgency("");
			// bizContent实体 设置属性
			request.setServiceUrl(BASE_URL + "/" + URI);
			// 请求实体 设置属性
			request.setBizContent(bizContent);
			// 输出支付指令提交响应对象，需根据实际调用服务更换（InvestmentFundoutsourceValuationreportqueryResponseV1）
			InvestmentAssetmanagementProductpurchaseResponseV1 response = client.execute(request);
			if (response.isSuccess()) {
				// 业务成功处理
				log.info("申购业务处理成功");
				log.info(response.getReturnContent().toString());
			} else {
				// 失败
				log.info("申购业务处理失败");
				log.info(response.getReturnCode()+","+response.getReturnMsg());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

}

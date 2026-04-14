package com.icbc.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.InvestmentAssetmanagementSalesstatisticsendRequestV1;
import com.icbc.api.request.InvestmentAssetmanagementSalesstatisticsendRequestV1.InvestmentAssetmanagementSalesstatisticsendRequestV1Biz;
import com.icbc.api.request.InvestmentAssetmanagementSalesstatisticsendRequestV1.SalesstatisticalParam;
import com.icbc.api.response.InvestmentAssetmanagementSalesstatisticsendResponseV1;

public class InvestmentAssetmanagementSalesstatisticsendV1Test {
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDhNCJwBiZgRxDA"+
			"6z5N75yC5G6CX4koXDAN3arQ6sXt6KzAflLtPTdOIPLeSd029P5SsNxN4HpUJUlPE"+
			"i/QJiGHWlFbusEVOLpQyu7yxWwMnG6FzNhmBEITkYMCyMFi2NtQzpIAsYiDIf54ds"+
			"Sl0ZcqozSv6Gekx/k3rYao/OCEIswNeq9z9s7VIrFCB/lLgkoV3dtPnmL4zqu5u/L"+
			"kRgHf8KdW+E09cUBZ1HoSqxw6q5YymeLWYOHBRdg+MuGwMnJTGZ9VEDkKX+b4/OQ6"+
			"CPskg3iT9gEwEDxCdw0SHqbBd5ur+P4YMMoZOl2FXs8H52wy4ahNWSTy71pm062jo"+
			"IdAgMBAAECggEALWElZ3JdypGPA9x/0ZoXX1h4lt1xpggCVICOtLdrkIpvXB7BOws"+
			"cNjyq1QlfwOWc9XaDi0wgyjVg0PFFFKCgQB5j1W5ZLyxvAxeHPovrVtmA/jnFI9aJ"+
			"gfkoGD27fs1CqE+7C1/Rp6v1OxZgNcD2qUC7yHgQtxuCtMDXysdpXSX+wWy6NFmD5"+
			"uekqiToaulVIOhmUeWBM5xNoCilg2cTJTkCUyjoGLKpFzSeyjJ+cSx5RcbYiNOZ8T"+
			"tz6tDS8/m1cstVE4hsDUEm6xA0fwKRHYhfszvZa4QEE7C3S1e6oJyYnQd+4zRJp68"+
			"fvtlY+d/SSHYI1xbnOxMAs0K3Up3XYQKBgQDxglFLbTZP2oSOy21QKcJRd9cSNAeg"+
			"hHO2/7+QUu2HU5e1lvqnCdnhoB7JEvOXCG9XxZ20DXjqMYdO0Ycqfn8xyNw9AYn7M"+
			"xG5XaoRHfNRaZLDYIPGkjnVAFBLNZcpgwh6dOVvwv6i/jtPmObHS19ee3mPX6jFjs"+
			"GaRyUMMaSc+QKBgQCLaP/xo8Q1zG8sMXeNPxMYzxWui7Y35aX0O0f1UHz//tCzcMh"+
			"S7oWpdrSu7inqz68r7/5WpCbe24yZ0C4I0DuZphxpTRXvRYICCaljqeJ+CKtMadMU"+
			"aMi+hLnCTuPJKNgYzDkPbt50Hvf3GnkMoyjfgmOw5SadtOs2BriSOd2LRQKBgF1Zt"+
			"fqluDydhmd7kkFOIuJCpEtQ+TG+gMvXh0BODaolUZrLRQ+T6zgfIEcwoB9dD0yqAg"+
			"86teYwRtDchJaUVq3zqc2a7q6+SreP0NDU9twdJrGGeOkkVF5UxLSNfmR5VamfzZ+"+
			"pi6uNAXf+7hVSuQZYS+BzZVUXnRX5MgTIm4xhAoGAZKX62qcEWl7tCHfyOxyFmgyw"+
			"N6MQfEylQq2l5vskM+HxJQvQ5OblwxI7omu6AZfNfSg4dK7wjUbuvIGrKpvD28ssi"+
			"e2oX2XMVdJJ4/Vb2uhBW9qvFPYNsFa8kgdItwtw8H5I19aQbj+JgHIxYKlPMga+GJ"+
			"FoDsX2Zrv1d0SlqQ0CgYAs3kpFxYovuZGMKtWY02znwNBf+5KPP3Ovsg4xdsZNgZZ"+
			"Dj4SdyEDDhR4TwMH0gmdHJadDmPVCPFFzokC6CW1loTBdBgIXRQtKqr0ODtWbfY8s"+
			"LIl4BC2n/zLIiL5U81IdV3X8JCU+200XUwNWaJOzpxuoBJIj2XzuoRBdpsBIYg==";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16"
			+"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL"
			+"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP"
			+"3CiNgg0W6O3AGqwIDAQAB";
	protected static final String APP_ID = "id";


    public void v1test() {
    	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		InvestmentAssetmanagementSalesstatisticsendRequestV1 request = new InvestmentAssetmanagementSalesstatisticsendRequestV1();
		request.setServiceUrl("http://ip:port/api/investment/assetmanagement/salesstatisticsend/V1");
		InvestmentAssetmanagementSalesstatisticsendRequestV1Biz bizContent = new InvestmentAssetmanagementSalesstatisticsendRequestV1Biz();
		SalesstatisticalParam sp = new SalesstatisticalParam();
		sp.setStatisticalDate("20161029");
		sp.setFundCode("4223000224");
		sp.setDistributorCode("003");
		sp.setSubNum(new BigDecimal("1"));
		sp.setSubApplyAmount(new BigDecimal("2"));
		sp.setSubCancelNum(new BigDecimal("3"));
		sp.setSubCancelAmount(new BigDecimal("4"));
		sp.setAllotNum(new BigDecimal("5"));
		sp.setAllotAmount(new BigDecimal("6"));
		sp.setAllotApplyNum(new BigDecimal("7"));
		sp.setAllotCancelNum(new BigDecimal("8"));
		sp.setAllotCancelAmount(new BigDecimal("9"));
		sp.setRedeemNum(new BigDecimal("10"));
		sp.setRedeemShare(new BigDecimal("11"));
		sp.setRedeemApplyShare(new BigDecimal("12"));
		sp.setRedeemAmount(new BigDecimal("13"));
		sp.setRedeemCancelNum(new BigDecimal("14"));
		sp.setRedeemCancelShare(new BigDecimal("16"));
		sp.setPreSubNum(new BigDecimal("16"));
		sp.setPreSubApplyAmount(new BigDecimal("17"));
		sp.setPreSubCancelNum(new BigDecimal("18"));
		sp.setPreSubCancelAmount(new BigDecimal("19"));
		sp.setPreAllotNum(new BigDecimal("20"));
		sp.setPreAllotApplyNum(new BigDecimal("21"));
		sp.setPreAllotCancelNum(new BigDecimal("22"));
		sp.setPreAllotCancelAmount(new BigDecimal("23"));
		sp.setPreRedeemNum(new BigDecimal("24"));
		sp.setPreRedeemShare(new BigDecimal("25"));
		sp.setPreRedeemCancelNum(new BigDecimal("26"));
		sp.setPreRedeemCancelShare(new BigDecimal("27"));
		sp.setSubCancelFee(new BigDecimal("28"));
		sp.setAllotCancelFee(new BigDecimal("29"));
		sp.setRedeemCancelFee(new BigDecimal("30"));
		sp.setPreSubCancelFee(new BigDecimal("31"));
		sp.setPreAllotCancelFee(new BigDecimal("32"));
		sp.setPreRedeemCancelFee(new BigDecimal("33"));
		sp.setOpenNum(new BigDecimal("34"));
		sp.setCloseNum(new BigDecimal("35"));
		sp.setReserveNum1(new BigDecimal("36"));
		sp.setReserveNum2(new BigDecimal("37"));
		sp.setReserveNum3(new BigDecimal("38"));
		sp.setReserveNum4(new BigDecimal("39"));
		sp.setReserveAmount1(new BigDecimal("40"));
		sp.setReserveAmount2(new BigDecimal("41"));
		sp.setReserveAmount3(new BigDecimal("42"));
		sp.setReserveAmount4(new BigDecimal("43"));
		sp.setReserveAmount5(new BigDecimal("44"));
		sp.setReserveAmount6(new BigDecimal("46"));
		sp.setStatisticalEndTime("133002");
		List<SalesstatisticalParam> data = new ArrayList<>();
		data.add(sp);
		bizContent.setData(data);
		request.setBizContent(bizContent);
		try {
			InvestmentAssetmanagementSalesstatisticsendResponseV1 response = client.execute(request,
					"01dd2223221");
			if (response.isSuccess()) {
				System.out.println(response.getReturnCode() + ";"+ response.getReturnMsg() + ";" + response.getReturnContent());
			} else {
				System.out.println(response.getMsgId()  + ";" + response.getReturnCode()
				+ ";" + response.getReturnMsg() +";" + response.getReturnContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.icbc.api;

import com.icbc.api.request.FintechIotUiThingmapentryRequestV1;
import com.icbc.api.request.FintechIotUiThingmapentryRequestV1.FintechIotUiThingmapentryRequestV1Biz;

public class FintechIotUiThingmapentryTestV1 {
	
	//应用私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC0pnj3kL4am3MB3WtVDLbmj90Z/JDzc7eo2rSvPbAWD2DXfJ9L7EifcF+Ll8HsTZw6WvNa81Odh3AuaTcFzKZUC66rsq9dvtiu1v3fuhm+WTZbDM2KLAl7UO7UmFCPadX2KTHbd2g+M6lPam2JC5Y6cZM51OaXQklWcuRMxqWst0uHDowNSsraucNFhfzX8ybNFRt7iWJRsrvmhhZ15m/htyX5wUnagSBvn/1qpTBsfcmM68HmNPV7xYb63G7XvVNhD2agvuEkNVyuBex5lcC5YWYHJE8mS3amBymUy3B7onj3wYb5bzxUlz2AbYADPup6lBr4grkVpZum6rQUV51jAgMBAAECggEAEAdhXl4KcCPw3W5/EonRRL3pGWAuoFtZJXNaat4nXJ26swQMwljRzMvUvncVrtjoDsl5JE3I0oZs7sFJL4uMCpMKyZNOOhwv77uFxS8nD5us1gFZz3O+16FDv7tQEpmKcZAQK2/54OMGl9RSqcFMcB0Zuhzo5Oztwuzo0uMhWiS2xwrCH/K3zcmj6uYx4NNpOLp5OAnGwqqgpAErMvsV9aGXX49o4za7uZTBjpk2fCfoHyhB1cDse2v95tp7SSHfEqV+VCe8O2cnh/XGRhmp87NAv3gTgZaHn9yqthw/ja6p7CyNGKifSs4pDAE40CUEmb9H6ntE6efTpoZMjSepCQKBgQD6CNn+e0FNHGcw8wFLqTtwrHZt7931SywSwgMGRrf8FB4CiCyLu/aAbw/VIfXq5BwtzP1iNS6FddD6P6nZrzNbItq1R96gn/6HS689cXOdPJI2LuUC1LY9Ap7ezSL6QvQvARhrMedOLPq7V1/quJHIFUIfZDtPhoQePf60Qa821QKBgQC49dbRgzU3z5q9cTK6pZHkhczzFMLEe1lNaaNeMDm5o0RNycVTVV5AXjmk20A9PW9gLq3VuDYxfm2lTbFdqTbjIfYiuYGKB9REO1Hf8rJFagCEpbch+3hhSWjLj2Wl5wz1RLHE9GdmDrfEDqB0nBWSGF8SnUBwa06AhSS+NP+PVwKBgQC/i1RlDcti5WVwLFlLuyOcF6LKab2iEqp63EIw5rA/3rF2wxdNHPukPHDmmBOqlIG53JYoTBGNkEo9y9O0vfEb2kGuKpa0ep0kzKg869LkG+jutVIfrxDFAGLw0DoBepVSBQbN5/cI/pA+QJtsV2Tm4NI89i4xqB1Chb/AG6m82QKBgQCJ1muo52cZmJVw6JMtlYYMD8qUvLwJQQNkNX2SC855EbstXVi4qb37qjy7JsnQ2Z/Oxfq2cvmwGNANiMDNU/Wbfwu3BRgMjZka4Do78tn38O9B61127bjgh5yZVtwhrvZxMiho5trHq+A3M89QF6Hbpl3zuUz0eoOeM5sWRNjPMQKBgQDwhCqjggy2rDvFbu9/sLE2sZ6jHfVQ7crglock161ogZstUcRkND3q0A8SyrtbhpjB7xEnR8Sdo112QFJ23GzWx2r9Z0ezjbJ+MWKbYL4xEMdJQHryqhuGgg73DFGWRxjAwpgEqw/V+/XfXGujOg4t4yDE6v+BZWNPLXpP6BeroQ==";
	//APID
	protected static final String APP_ID = "10000000000004594554";
	
	public static void main(String[] args) {
		
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8); 
		FintechIotUiThingmapentryRequestV1 request = new FintechIotUiThingmapentryRequestV1();
		//request.setServiceUrl("https://ip:port/ui/fintech/iot/ui/thingmapentry/V1");
		request.setServiceUrl("http://ip:port:8085/ui/fintech/iot/ui/thingmapentry/V1"); 
		FintechIotUiThingmapentryRequestV1Biz bizContent = new FintechIotUiThingmapentryRequestV1Biz();
		bizContent.setAppName("F-ECSP");
		bizContent.setBussId("bussid01");
		bizContent.setBussType("SCENICSPOT");
		bizContent.setPageId("DISTRIBUTION");
		bizContent.setStruId("struid");
		bizContent.setDbLon("111.123456");
		bizContent.setDbLat("22.123456");
		bizContent.setData("{'abc':'987'}");
		
		request.setBizContent(bizContent);
		try{
			System.out.println(client.buildPostForm(request));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		

	}


}

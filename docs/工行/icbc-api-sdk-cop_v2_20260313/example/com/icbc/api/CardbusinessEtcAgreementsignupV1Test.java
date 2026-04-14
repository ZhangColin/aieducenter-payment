package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CardbusinessEtcAgreementsignupRequestV1;
import com.icbc.api.request.CardbusinessEtcAgreementsignupRequestV1.CardbusinessEtcAgreementsignupRequestV1Biz;
import com.icbc.api.response.CardbusinessEtcAgreementsignupResponseV1;

public class CardbusinessEtcAgreementsignupV1Test {

	protected static String APP_ID = "0200EH0013035";
	protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCp5CMknPYcCYoggJSLxaaN68/uBRp6XOvz/f/tbNBykGl5f+cKNAepgGmmUN5JCmc1ujqB+49sipRiZKDex9HeQcslRVwhmZ/tBmFJ59lSsmdopzBWof32ryQy4rJUFb2dk0Mi+G2uWvf3ReERdrxx1KthUs7OKR+gLaLwLBkTG74/sMyY+ZILkecgJNRCGbIPfdPTC5zOxdfXt/iHbtTEEtzqT1vIN5oVre1TtWLiFlXdV1uRuTPwmmZdKqzA+a4rz+QSG0hkbdygTPXfA6Cid1T11qX2QGw/NAZLVf5+6kMS/Wd0iyP3JEw6KlkT08aOYe/y8y27pYcDJd7uK19JAgMBAAECggEAfSNG44QeH97C+Ffdkuq+WNE2clsd+r2RK0KPjcHV9Fz84/rbIn/amPa2W0Q7x087YK7Qw8eZCFk3HRaEDDThGDYcPVLOnw06aGuHo9l8EVbJpqNnjph5qYeuQTEMK7Vr2rKLBCo7F+93sJKvSp7MBYihx+kn3WEbI3AaEZBZ0+Eo7F7sWi2pnvfeKoxubkzzRe1YlxRTdSE2bPhb5uEph73STLyaC0hHt+edmTtS13MYu4af1bunYLHnRkiFpmHzKuOA6863csSYpPyqFbBS0BfSozhAq5Ft9NwEiTLLATED3OBZn5pJbAc3STl0AJuoyNjOyRA3BNMRTRUPfsErVQKBgQDsR/vFdsckywUkB4kTR7wV1CFmJILiBiPSwEWdCm9YAdCOnIa0BXytluWvpswu0fy00+WITSrqJRMOfFZ71zSGCOMDDgPxBwrVjvIVQuklMDFt7BiXaAmRRo9JdKjjwVsS9tPojz8pNkZNQRybiY7lkCXLoUP5xAqz+9bqwcZWjwKBgQC4EcSKhkB4ex2Qgch1rKPuvpx7kuZT57NbuoEkrxzVOhJauI2W+gu7yN2gPbF40di055JGAVyuivdk5t7Jgy6ylPYN8Vtj5MQVhWnLeYpfBOhd2EOvz1z7fSCiMGIFZTFGFkDTtaX1Uj6z9QkGULG+4Ae85bY5VXRddNlLaVyYpwKBgHNLULxzEAuREUoabzW8PDf+kemQiVkXG+XbslxF2jz8bbMZB9XSE5t01VEXt+xkxMXL2+XdmcI1Iw5fd6foRBXfQj6cWSoEgLHilZfwVWmzEXuMyBchSgHch1CVn0+7auBfFtoVBAHtHPLXgckXn2W/J+IsJ8o5ceuPSLi2ozxbAoGAdCmKXl1xLFeOBddRuamU3yNdv8KqJsNvdnER9SOpz4rRcH1NpbzxULXn36tvZ68O0sPdlN5hLim3DO+CI6vs+Xh2e8AYi8cgU/CZHnb6cxK2zhqgRMBZjsCdqp1cDsmYugFrMfBN5q7bsVfvNRQRyEK7v2M8h5w9ToliZGv+qrsCgYEA5HZObz1qg6KDNhmGCJX+Lb1s7Bgy5G6X9/9it5bqKArgPSRt15pNfQ2UTFb+PziQeN1dzzgvn6qZN+dnCro/mp/jwuo5tcsmutUs/PYATBtKalKMMYRixvz/8+aFd8JCIr9+T+WcVlPic0SfdlyoMA00ZSKswCevB50nWBNpsc8=";
	protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessEtcAgreementsignupRequestV1 request = new CardbusinessEtcAgreementsignupRequestV1();
		CardbusinessEtcAgreementsignupRequestV1Biz bizContent = new CardbusinessEtcAgreementsignupRequestV1Biz();

		String ICBCAPI_URL = "http://ip:port/api/cardbusiness/etc/agreementsignup/V1";

		bizContent.setMsgId(genRandomCode());

		bizContent.setAgreementNum(genRandomCode());
	//	System.out.println(bizContent.getAgreementNum());
		bizContent.setCard_no("123456");//卡号
		JSONObject object = new JSONObject();
		object.put("vehicleId", "369875415586321");
		object.put("vin", "fdsafdsa");
		object.put("engineNum", "yyyymmdd1111111");
		object.put("issueDate", "2018-01-01");
		object.put("name", "test");
		object.put("plateNum", "粤A56987");
		object.put("registerDate", "2018-01-01");
		object.put("useCharacter", 2);
		object.put("vehicleType", "fdsafdasfdsa");
		object.put("fileNum", "3969fdafd4599eee");
		object.put("approvedCount", "5");
		object.put("totalMass", 1235);
		object.put("maintenaceMass", 39687);
		object.put("permittedWeight", 59875);
		object.put("outsideDimensions", "100*100*100");
		object.put("permittedTowWeight", 369874);

		bizContent.setExtend_info(object.toJSONString());
		bizContent.setHolder_name("李东波");
		bizContent.setId_no("123456");//身份证号
		bizContent.setId_type("0");
		bizContent.setMobile_no("15622374560");

		bizContent.setOrgCode("00003");
		bizContent.setTrx_date("2024-07-01");
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		bizContent.setTrx_time(sf.format(date));

		request.setBizContent(bizContent);

		request.setServiceUrl(ICBCAPI_URL);

		String msg_id = String.valueOf(System.currentTimeMillis());

		try {
			CardbusinessEtcAgreementsignupResponseV1 response = client.execute(request, msg_id);

			if (response.isSuccess() && response.getReturnCode() == 0) {
//				System.out.println("response.isSuccess():" + response.isSuccess());
//				System.out.println("Success:" + response.getReturnCode());
//				System.out.println("RetMsg:" + response.getReturnMsg());
			} else {
//				System.out.println("response.isSuccess():" + response.isSuccess());
//				System.out.println("err_ReturnCode " + response.getReturnCode());
//				System.out.println("err_ReturnMsg " + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

	public static String genRandomCode() {
		String randomCode = "";
		Random initRandom = new Random();
		String singleRandom = "";
		for (int i = 0; i < 15; i++) {
			singleRandom = initRandom.nextInt(9) + "";
			randomCode += singleRandom;
		}

		return randomCode;
	}
}

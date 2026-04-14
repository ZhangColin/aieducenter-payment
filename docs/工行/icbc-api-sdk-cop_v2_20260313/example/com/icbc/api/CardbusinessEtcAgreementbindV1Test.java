package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.CardbusinessEtcAgreementbindRequestV1;
import com.icbc.api.request.CardbusinessEtcAgreementbindRequestV1.CardbusinessEtcAgreementbindRequestV1Biz;
import com.icbc.api.response.CardbusinessEtcAgreementbindResponseV1;

public class CardbusinessEtcAgreementbindV1Test {
	protected static String APP_ID = "0200EH0013035";
	protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCp5CMknPYcCYoggJSLxaaN68/uBRp6XOvz/f/tbNBykGl5f+cKNAepgGmmUN5JCmc1ujqB+49sipRiZKDex9HeQcslRVwhmZ/tBmFJ59lSsmdopzBWof32ryQy4rJUFb2dk0Mi+G2uWvf3ReERdrxx1KthUs7OKR+gLaLwLBkTG74/sMyY+ZILkecgJNRCGbIPfdPTC5zOxdfXt/iHbtTEEtzqT1vIN5oVre1TtWLiFlXdV1uRuTPwmmZdKqzA+a4rz+QSG0hkbdygTPXfA6Cid1T11qX2QGw/NAZLVf5+6kMS/Wd0iyP3JEw6KlkT08aOYe/y8y27pYcDJd7uK19JAgMBAAECggEAfSNG44QeH97C+Ffdkuq+WNE2clsd+r2RK0KPjcHV9Fz84/rbIn/amPa2W0Q7x087YK7Qw8eZCFk3HRaEDDThGDYcPVLOnw06aGuHo9l8EVbJpqNnjph5qYeuQTEMK7Vr2rKLBCo7F+93sJKvSp7MBYihx+kn3WEbI3AaEZBZ0+Eo7F7sWi2pnvfeKoxubkzzRe1YlxRTdSE2bPhb5uEph73STLyaC0hHt+edmTtS13MYu4af1bunYLHnRkiFpmHzKuOA6863csSYpPyqFbBS0BfSozhAq5Ft9NwEiTLLATED3OBZn5pJbAc3STl0AJuoyNjOyRA3BNMRTRUPfsErVQKBgQDsR/vFdsckywUkB4kTR7wV1CFmJILiBiPSwEWdCm9YAdCOnIa0BXytluWvpswu0fy00+WITSrqJRMOfFZ71zSGCOMDDgPxBwrVjvIVQuklMDFt7BiXaAmRRo9JdKjjwVsS9tPojz8pNkZNQRybiY7lkCXLoUP5xAqz+9bqwcZWjwKBgQC4EcSKhkB4ex2Qgch1rKPuvpx7kuZT57NbuoEkrxzVOhJauI2W+gu7yN2gPbF40di055JGAVyuivdk5t7Jgy6ylPYN8Vtj5MQVhWnLeYpfBOhd2EOvz1z7fSCiMGIFZTFGFkDTtaX1Uj6z9QkGULG+4Ae85bY5VXRddNlLaVyYpwKBgHNLULxzEAuREUoabzW8PDf+kemQiVkXG+XbslxF2jz8bbMZB9XSE5t01VEXt+xkxMXL2+XdmcI1Iw5fd6foRBXfQj6cWSoEgLHilZfwVWmzEXuMyBchSgHch1CVn0+7auBfFtoVBAHtHPLXgckXn2W/J+IsJ8o5ceuPSLi2ozxbAoGAdCmKXl1xLFeOBddRuamU3yNdv8KqJsNvdnER9SOpz4rRcH1NpbzxULXn36tvZ68O0sPdlN5hLim3DO+CI6vs+Xh2e8AYi8cgU/CZHnb6cxK2zhqgRMBZjsCdqp1cDsmYugFrMfBN5q7bsVfvNRQRyEK7v2M8h5w9ToliZGv+qrsCgYEA5HZObz1qg6KDNhmGCJX+Lb1s7Bgy5G6X9/9it5bqKArgPSRt15pNfQ2UTFb+PziQeN1dzzgvn6qZN+dnCro/mp/jwuo5tcsmutUs/PYATBtKalKMMYRixvz/8+aFd8JCIr9+T+WcVlPic0SfdlyoMA00ZSKswCevB50nWBNpsc8=";

	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessEtcAgreementbindRequestV1 request = new CardbusinessEtcAgreementbindRequestV1();
		CardbusinessEtcAgreementbindRequestV1Biz bizContent = new CardbusinessEtcAgreementbindRequestV1Biz();

		bizContent.setMsgId("1234567898");
		bizContent.setAgreementNum("440521573114032");
		bizContent.setOrgCode("00003");
		bizContent.setVehicleId("00003");
		bizContent.setPlateNum("粤A88888");
		bizContent.setPlateColor(0);
		bizContent.setIssuerId("370101");
		bizContent.setVehicleType(1);
		bizContent.setType(1);
		bizContent.setSalecode("36020078951");

		request.setServiceUrl("http://ip:port/api/cardbusiness/etc/agreementbind/V1");

		request.setBizContent(bizContent);
		request.isNeedEncrypt();

		String msg_id = String.valueOf(System.currentTimeMillis());
		try {
			CardbusinessEtcAgreementbindResponseV1 response = client.execute(request, msg_id);
			if (response.isSuccess() && response.getReturnCode() == 0) {

			} else {

			}
			// System.out.println("response=>" + response.getReturnCode() + "|"
			// + response.getReturnMsg());
			// System.out.println("msgId:" + response.getMsgId());
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

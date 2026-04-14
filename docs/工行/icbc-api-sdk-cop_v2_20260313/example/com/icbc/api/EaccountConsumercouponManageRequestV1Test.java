package com.icbc.api;

import java.io.IOException;
import java.text.ParseException;

import com.icbc.api.request.EaccountConsumercouponManageRequestV1;
import com.icbc.api.request.EaccountConsumercouponManageRequestV1.EaccountConsumercouponManageRequestBizV1;

public class EaccountConsumercouponManageRequestV1Test {
	protected static String APP_ID = "10000000000004098027";// appid
	protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCe4pPKMnQyAnipg065kcWsbFl69aGxcC170I8/FPmrFi5OV5nxOfDKBbRt0co+VFnYh18nq3iJFaLq0uh0AKn0GmB72YcUgPwbIiue8a6A5k/lOzlqbkoKyw5WlOPRV8xZNxhWrcVX3BRxxwALwK4e/W9p4OrhkFXB4aEaq3FXtMXkKQeBzH5BuhtO4e4vpMtdEZbPgs/LiixGRtLse5dv7kfTO/FNO72fgsA/cwhvX7OFKSAP+wbOMud6kpU1zMuKnuZMbtKG39ezXrXjnR9/93662Qe/IPs7KrEen7Or4ZvbM5evWTSsqkLU8O1K3LKFkdT47Ut2nST7sVb6enFpAgMBAAECggEBAJr/FAezUpEawhUzyE/Veg4roNsH1pYfhe98VNHIJ4uUVtYMNVwTFugtyoc7ml1VslQ+M0Ci0TKCDe2glC3eG+3K8YbAfTsuXUInAoDK6l5Kz3QjY26Fauu+jR0/oEMvsGT0D2U3ngWI59Tauhu8+t+2gsK6LMihIlfCQYSf7TEN0jeXRpbpPPqj71NP5K0pyh5jF8L/utROpAWYartQZpTHb/O7tL8xTgoo678uSs6nnq4Z1zeH0EmXht68BbD8BDr+d4BYrAMwguxixnTNiMmV8eLBzeeanDg/WE9flPNOFTkMBWBgPRNCEbygKuzDxXgu+1d1uIHhoF+NczjBfDUCgYEA9zNvV6X5ES5BJr+7nggPw1JT2zWV96japCBUV3nWDt4O27JIMEgil7ORviclgBpufypHfAcyafT4xDMzG3YHMX2vt/lYXYRnwBZCg+9V5zW+gTKLnluSWK0DIb5MNtsy/u9s6wO8MebrGheZkktNbcPK4L6MVpvsq2T+usgHfkMCgYEApIpiG0Y++JpFGuwfQwM79w0swq0ipCs/rnGBKYsNN+uw0ZQHrlEV6GY922Td6m5DVELzRRr+nSvXowgQJ6OdlkZHkEF+G69HqT0TJnnTd+Vi2SneZ6IoGbBGp4XOSGaXRDQfchuDEulOr3dDpgyTEU+vFTqUJWw1i5UxXR5C1OMCgYA/1wOxMgbRng1rUWbFmDVh7vgDC6KwM+ZLLqI5eRiD/Gr/59nmxipQ3SoNXpS7sZfJtYSCjmVBoedA3CwdsmyHJntj2rwsYo5cuCLzabLYpDOPsBsIiYOQ7RV6+Gc8X32bAwAw7WUDyiv7MA8UDehwKXlSc0NQ9rG3TbzPRetRewKBgFOvXj5o99YRAFy1AyFeH72DIeTRkfDVXkomyH6MjmiGoyO0mkN6Uh4lAgj3wDZfZjcQ5hGbrSvbBI2SqETA3oFztSoVZUJHX7cfrQHArGEqdZ/lv4wN2T9XfFfvof8Izp6NtAChJYUUzERJ8Eof0xzW3kF5zadK5eDcPIMTxQHpAoGAPFehYLe4usLhp3/v0Y9cBSTpPa89rkX5JN8tH9tOCOSpIk1mdYG/hNqvQznYz5yPQ5dV1w04QisvPR4vxa9R97RTgVg9/0Vzd4F1Vj+etx6rzRO/ytZ1YREUffsyGDKyG+wUQZ8fW7pu5cNxQ3T9FhThxEgkNF1NnjS2TLgS2kk=";
	private static String AES_KEY = "xMh0xFsG7G80ziePFdnT8g=="; // AES_KEY;

	public static void main(String args[]) throws IcbcApiException, IOException, ParseException {

		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
				IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY);
		EaccountConsumercouponManageRequestV1 request = new EaccountConsumercouponManageRequestV1();
		request.setServiceUrl("http://ip:port/ui/eaccount/consumercoupon/manage/V1");
		EaccountConsumercouponManageRequestBizV1 bizContent = new EaccountConsumercouponManageRequestBizV1();
		bizContent.setUserId("");// 客户唯一id，取自开户申请接口的corp_medium_id
		bizContent.setCorpAppid("");// 合作方APPID
		bizContent.setMobileNo("");// 手机号
		bizContent.setBacAddr("");// 回调地址
		bizContent.setIsApp("");// 呼起方标示（微信卷发放时使用，0-H5、1-公众号、2-小程序）
		bizContent.setConsumerCpnMiniProgUrl("");// 消费券小程序跳转页面地址
		bizContent.setTradeType("");// 交易类型（0-查询、1-开户、2-充值）
		bizContent.setAmount("");// 充值金额（单位分）
		bizContent.setCorpSerno("");// 开户/充值流水号，取自开户/充值接口的corp_serno
		bizContent.setBindMedium("");// I类卡号，取自充值接口的bind_medium
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));// 实际调用时的相关返回结果及异常处理，请自行添加

	}
}

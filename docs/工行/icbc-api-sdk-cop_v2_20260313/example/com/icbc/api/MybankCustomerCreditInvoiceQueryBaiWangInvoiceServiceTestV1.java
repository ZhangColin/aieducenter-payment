package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceRequestV1;
import com.icbc.api.request.MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceRequestV1.MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceRequestV1Biz;
import com.icbc.api.response.MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceResponseV1;


public class MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceTestV1 {
	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDEdCspoRFgbl55kFUMv7IHfwWHMPhWl6kHZ17OpWswrYT9nm6GpX7i/YkGSmlzlDg6UmNkxGHkxDnF3owWgmhIa+S7B/gMpIN550YzWvxY0WWgdlfDXEVMNzv7kxWR3HsRygNxrPFvk/DWMmzxurJ0XM9YhCxhaCmjz8dngQkzxyjb0J3y8zyfGGhpzyidul+1Rz6ksMBBv2PXxaXg85fqcxGl5pdF23xuKkz3gmPH0D6vo+Np9Nhj60kA/xVutQu3Zc0tZD3S5x8aj5LeWR3lu8DHKB7b0Ndcutq/YEDo5ZNndUnC/Qm4jkPil/EaqxibFNO50lzFNyE/idwj9NnhAgMBAAECggEATVaOR20pMhVBDApN8R7mLx1KR9Z3bV93I3sGT8O/VwJ4gH+Gc2tjcGjvMJLKwL1xBUS57EmDYFP/im36KEJeW2ReP2L7u75oI+1wLXd8MFr+7xSnqDkC7uYvrvJQY4Owz2f/wxiaU5qiDTD8wo0FA9olpmsiLZacDekWTyD1RnRHq4r5/jHKDRHHkr9FlnIvR54V5S+6uMItr9qhIsevt6+ZANNrRfWntv6kFxvPGYFBUpHb28LFCzP/O/Sze248vW1wuioix+YjDxoux02SNfg+DyiakMFgPjhRJm45S4+K3rrlEYT8aEjFBzvqEe4OC1V4gLS97QxTl10fNISAAQKBgQD3fPNDpB3be8CocnXQ4MtjW3pC9cpQ7CdEA0HC4XQ264p/n/jpD7ut/JIy5nBs+smbHOvE5zb8ar4VUfhTnnUgy1WKGV9k0j/f/i710arxQGhxfk24V8SaRdnreJL5vAcy2G7C0x/mOgX1jkHu2RfsqpWfKqqmXgJYM7At/I8FYQKBgQDLNeDes7ARiAmsZNNEUAbSf+3e/p7+c40bIGYwVvdMC7IHlJwJkF6yxf3BvT79ftVQ5peWy3eZocTfYf0oXvUj4Wetc3wG8XuO6jyYluq56COP/C96gYxEtr59dVMde689umOaGmQa3nOI/bkkLDhw085PFxSsx8oDWpaKBRGkgQKBgQDF6py4CTT/YxYNH4vsgPjl+i/qAJnM4S39jAU5DgdcLD1UFuB9sWFLoGJiZjqeTGiRrS03CBjG/64HasgGRwAwO6uqhuPswt7mCGYRm5zy1akwqesJctb88MN3NbOEF8RB+yeIkkI+YUSGvT8o/V/OmhSRdxOFfESvuJY6EHFnwQKBgQCIylf5EdQvuPdqCOT+UzCEeoFRXu0ANCCEZibj22bU1fqdh7gR6BEXHphW1mGUmtBViSP56jU0QeUYygl0J5e084KrC9Ythli6N4ta3Bxd0Jo/zm7XdGVLzAgmw9eTPTMVxmZN0f4Y6C21xbXfV7UWnj1+l/be6A5ge5Ziik76AQKBgF0mkLGxsspvLTVf+mu4+PNSTQRw7cRiJ8TgHADhincnjTzzJMQPbEC/1ikN4kkqk7Fs5uqSM6hvIVeY0b2xxJFlfBZHzj8qJusLVJzi6BzjmR4vcGs+ivwBbHcA+s36CteBkYiJ4+adbT5Y3j79ylcPtcZnJVoDs63CBJSEB9z2";
	
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	protected static final String APP_ID = "11000000000000000670";
	protected static final String SIGN_TYPE = "RSA2";
	
	
	public static void main(String[] args){
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,SIGN_TYPE,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceRequestV1 request = new MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceRequestV1();
		MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceRequestV1Biz bizContent = new MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceRequestV1Biz();
		
		request.setServiceUrl("http://ip:port/api/mybank/customer/credit/invoice/queryBaiWangInvoiceService/V1");
		bizContent.setNvoicecode("031002200411");
		bizContent.setInterface_id("008");
		bizContent.setCheckcode("272873");
		bizContent.setInvoicenumber("15024854");
		bizContent.setBillingdate("2023-08-24");
		bizContent.setApp_name("F-CIIS");
		bizContent.setOrgan_id("0010100000");
		bizContent.setTotalamount("2980");
		bizContent.setUser_id("888806011");
		bizContent.setUuid("01234567890123456789012345678901");
		request.setBizContent(bizContent);
		try{
			MybankCustomerCreditInvoiceQueryBaiWangInvoiceServiceResponseV1 response = client.execute(request);
			System.out.println(response);
			}catch(IcbcApiException e) {
				e.printStackTrace();
		}
	}
}

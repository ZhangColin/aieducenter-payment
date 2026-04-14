package com.icbc.api;

import com.icbc.api.request.InvestmentAssetmanagementRedeemconfirmationqueryRequestV2;
import com.icbc.api.request.InvestmentAssetmanagementRedeemconfirmationqueryRequestV2.InvestmentAssetmanagementRedeemconfirmationqueryRequestV2Biz;
import com.icbc.api.response.InvestmentAssetmanagementRedeemconfirmationqueryResponseV2;

import java.util.logging.Logger;


public class InvestmentAssetmanagementRedeemconfirmationqueryTest {

	//appid
	protected static String APP_ID = "xxxxxxxxxxxxx";
	protected static String BASE_URL = "http://ip:port/api";
	protected static Logger log = Logger.getLogger(InvestmentAssetmanagementRedeemconfirmationqueryTest.class.getName());

	//公钥
	protected static String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAkUgg6vmbfp2xt3+OLs5pfSoS3SK/CJ2KuCv6BwhWZPCazq94C0ccQwyUB4H/+RTj2Lkb50HnWFqOT53+DXSv3pVECtKwhWhtPr9V2kIxgYHbdqjo570YEEjZACp6ERtJDPl847rx3RiFLrZVmzTdKTC0CmJelGLEGW4VnHLlSLspS3wHePAoSflWV/gLBPYqv82MoRlwn0LiadhkgAbQMbKyNsrtFx/ve+qnR26rH2XLFT2Pf3GCY2H3Hjm3dFenuS0BeZl7a310pGKUpeFS8Vfcb4F96KcM2Yb2F1sUfrIQyGDpfD1+CkMTiIQK9HzkqkCHNIttmBbRuyPnDgJq7wIDAQAB";
	//私钥
	protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRSCDq+Zt+nbG3f44uzml9KhLdIr8InYq4K/oHCFZk8JrOr3gLRxxDDJQHgf/5FOPYuRvnQedYWo5Pnf4NdK/elUQK0rCFaG0+v1XWQjGBgdt2qOjnvRgQSNkAKnoRG0kM+XzjuvHdGIUutlWbNN0pMLQKYl6UYsQZbhWccuVIuylLfAd48ChJ+VZX+AsE9iq/zYyhGXCfQuJp2GSABtAxsrI2yu0XH+976qdHbqsfZcsVPY9/cYJjYfceObd0V6e5LQF5mXtrfXSkYpSl4VLxV9xvgX3opwzZhvYXWxR+shDIYOl8PX4KQxOIhAr0fOSqQIc0i22YFtG7I+cOAmrvAgMBAAECggEAZau8nbShBTMGtAwoTMlXOajAGtG+LyFReyILj/BdxbY8IHjb4ciaFrIuxjJMGNxtt7WTuXt9k2+MOFuw92m5ymNbgtd/gV1bVpZAU/lu+9sM4zXCCKtks8DHXD5dvqsBrHrM0/J4YOpQCijAMCYGwFS9Kqc82UtlQQaxSRN/Mi35cSGrtgIsvjflA3bLqXkn29Gawy1CmOa1AmC9aQSHK+TTql/NnGoa3BMe01FtMuazWYi9bk9roiyjjtLSQRYNHut1y5gciCNONRG1XwYc0dsCU13nVaj/M4ssHy96VrzwQdIHEgULmWFUd/kcQBxmWaRf/86OSN/0iLAQbeuwwQKBgQDWy9Yop6ElFRmVAK9EQUaKFzbkTaozMnZgghp9KgsyTqVndoB4mpeXQYNIV+RyBZf1Wk3nWDfWNQi1IrCGNLLQk4WzCloEfDzc+yh/LqYCk/mIUJEe2BVXynRodaeoS3QwqLYvE9H5NEAgczf2SB4MR/S6kc/J7svKPURzD0H77QKBgQCtJpdJRiJ6NOSPT1v7f72kM/1Hn7P5eOSK9sIOwV1GAYcy//KfBJgciUYK/s9jith45MxuSYl1P36C+qbhkIKZ2rimfz7iJZjK91OoJjXYPwytnbWuZQHwjpch0wXvLfoB44zZjbpBuuyALopChqJqO+xdeBjAx1rZ5YiBiVV+ywKBgGnkYbb3NQ1oAo2focg/efwYB7qzwyWsSgm3+kEcWhq6BPclx54tcHBkHsmqcvf3Y/vUQjW8GwLicGGEMYxoLIOBRteGUeyGJyc2NrfEkpkQxGL8fR68noECUTMyVa41TJm0guRgnBKEPN0CkGM9GHsrei4ENnEx5YvVeopSyk/dAoGAGtRu/siiUQNfIyGt5BOQJCB2L6H8djyYVTwVJ8oolM9pzqpAQwmoNEvFEbEYG5mJH3p8ft5gMU3MYalLMIp+4i41DH+NE+e1fEXErbp1/3MNmN1mKIa4PTkrC++x7LTYF3DWA2Plp4e6W8ZaYCKSihmM75LLaxu45Sie9Ut9du8CgYEAgxJT08BlWVqAdOktv3RtHyh/Hkf/msG384Sf0I6/2f47FC2eUvNID4o+32du2Usx8by5mnAW3gZy0L6Gns/H4DF/P5ijJjjijlbFPzjA4pxrHmoIGwvoAGyGSJp5XmpZNwT7ogiE/H91fXAntkmfhhujnD7XTO23qt8dvLN5tFw=";

	public static void main(String[] args) throws Exception {
		String URI = "investment/assetmanagement/redeemconfirmationquery/V2"; // api��ַ
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
		try {

			//
			InvestmentAssetmanagementRedeemconfirmationqueryRequestV2 request = new InvestmentAssetmanagementRedeemconfirmationqueryRequestV2();
			// bizContentʵ��
			InvestmentAssetmanagementRedeemconfirmationqueryRequestV2Biz bizContent = new InvestmentAssetmanagementRedeemconfirmationqueryRequestV2Biz();

			//
			bizContent.settASerialNO("4501");

			// ����ʵ�� ��������
			request.setServiceUrl(BASE_URL + "/" + URI);
			request.setBizContent(bizContent);

			log.info(request.getMethod());
			InvestmentAssetmanagementRedeemconfirmationqueryResponseV2 response = client.execute(request);
			if (response.isSuccess()) {
				log.info("success");//
			} else {
				log.info("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
	}

}

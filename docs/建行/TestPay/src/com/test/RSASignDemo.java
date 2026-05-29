package com.test;

import java.security.PrivateKey;
import java.util.Map;

import com.ccb.mktpay.sign.RSASignUtil;

public class RSASignDemo {
		public static	void main(String[] args) {

			//测试生成密钥对
			Map<String,String> keys = testGenKey();
			
			
			String publicKey = keys.get(RSASignUtil.PUBLIC_KEY);//公钥
			String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu";//私钥
			
			String srcStr = "Write Once, Run Anywhere";//需要生成签名的原串
//			String srcStr1 = "Py_Chnl_Cd=1&OnLn_Ofln_IndCd=1&Cmdty_Ordr_No=20180101123456&Opr_No=123456&Usr_ID=&Ccy=156&PgFc_Ret_URL_Adr=http://order.zwgl.com&Fee_Itm_Cd=1234&Fee_Itm_Prj_Nm=水费&RvPyUnt_Cd=8891&RvPyUnt_Nm=长沙水务公司&Fee_Itm_Prj_Usr_No=001022345623&Fee_Itm_Prj_Amt=80";
			String signInf = testSign(srcStr,privateKey);
			
			testVerify(srcStr, signInf, publicKey);
			
			

		}
		
		/**
		 * 测试生成签名方法
		 * @param srcStr
		 * @param privateKey
		 * @return
		 */
		public static String testSign(String srcStr, String privateKey) {

			System.out.println("开始测试签名...");
			String signInf = RSASignUtil.sign(privateKey, srcStr);

			System.out.println("签名=" + signInf);
			return signInf;


		}
		
		/**
		 * 测试验签方法
		 * @param srcStr
		 * @param signInf
		 * @param publicKey
		 */
		public static void testVerify(String srcStr, String signInf, String publicKey) {
			
			System.out.println("开始验签...");

			System.out.println("验签结果=" + RSASignUtil.verifySign(publicKey, srcStr, signInf));
		}

		/**
		 * 测试生成密钥对
		 * @return
		 */
		public static Map<String,String> testGenKey() {

			System.out.println("开始生成密钥对...");

			Map<String,String> keys = RSASignUtil.generateKeyBytes();

			System.out.println("公钥:" + keys.get(RSASignUtil.PUBLIC_KEY));

			System.out.println("私钥:" + keys.get(RSASignUtil.PRIVATE_KEY));
			return keys;

		}
}

package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.request.InvestmentAssetmanagementTaaccountopenqueryRequestV1;
import com.icbc.api.request.InvestmentAssetmanagementTaaccountopenqueryRequestV1.FtasChannelTaaccountopenqueryRequestV1Biz;
import com.icbc.api.response.InvestmentAssetmanagementTaaccountopenqueryResponseV1;

/**
 * @ClassName InvestmentAssetmanagementTaaccountopenqueryTest
 * @Description 联机开户申请查询测试
 * @author: kfzx-wangyh1
 * @create: 2020-04-22 14:41
 **/
public class InvestmentAssetmanagementTaaccountopenqueryTest {
	/**
     * appId
     */
    protected static String APP_ID = "id";
    /**
     * 行外
     */
    protected static String BASE_URL = "http://ip:port/api";

	/**
     * 网关公钥
     */
    protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16\n" +
            "bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL\n" +
            "T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP\n" +
            "3CiNgg0W6O3AGqwIDAQAB";
    /**
     * 应用私钥（与内管平台注册app时的“签名公钥”配对）
     */
    protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDhNCJwBiZgRxDA6z5N75yC5G6CX4koXDAN3arQ6sXt6KzAflLtPTdOIPLeSd029P5SsNxN4HpUJUlPEi/QJiGHWlFbusEVOLpQyu7yxWwMnG6FzNhmBEITkYMCyMFi2NtQzpIAsYiDIf54dsSl0ZcqozSv6Gekx/k3rYao/OCEIswNeq9z9s7VIrFCB/lLgkoV3dtPnmL4zqu5u/LkRgHf8KdW+E09cUBZ1HoSqxw6q5YymeLWYOHBRdg+MuGwMnJTGZ9VEDkKX+b4/OQ6CPskg3iT9gEwEDxCdw0SHqbBd5ur+P4YMMoZOl2FXs8H52wy4ahNWSTy71pm062joIdAgMBAAECggEALWElZ3JdypGPA9x/0ZoXX1h4lt1xpggCVICOtLdrkIpvXB7BOwscNjyq1QlfwOWc9XaDi0wgyjVg0PFFFKCgQB5j1W5ZLyxvAxeHPovrVtmA/jnFI9aJgfkoGD27fs1CqE+7C1/Rp6v1OxZgNcD2qUC7yHgQtxuCtMDXysdpXSX+wWy6NFmD5uekqiToaulVIOhmUeWBM5xNoCilg2cTJTkCUyjoGLKpFzSeyjJ+cSx5RcbYiNOZ8Ttz6tDS8/m1cstVE4hsDUEm6xA0fwKRHYhfszvZa4QEE7C3S1e6oJyYnQd+4zRJp68fvtlY+d/SSHYI1xbnOxMAs0K3Up3XYQKBgQDxglFLbTZP2oSOy21QKcJRd9cSNAeghHO2/7+QUu2HU5e1lvqnCdnhoB7JEvOXCG9XxZ20DXjqMYdO0Ycqfn8xyNw9AYn7MxG5XaoRHfNRaZLDYIPGkjnVAFBLNZcpgwh6dOVvwv6i/jtPmObHS19ee3mPX6jFjsGaRyUMMaSc+QKBgQCLaP/xo8Q1zG8sMXeNPxMYzxWui7Y35aX0O0f1UHz//tCzcMhS7oWpdrSu7inqz68r7/5WpCbe24yZ0C4I0DuZphxpTRXvRYICCaljqeJ+CKtMadMUaMi+hLnCTuPJKNgYzDkPbt50Hvf3GnkMoyjfgmOw5SadtOs2BriSOd2LRQKBgF1ZtfqluDydhmd7kkFOIuJCpEtQ+TG+gMvXh0BODaolUZrLRQ+T6zgfIEcwoB9dD0yqAg86teYwRtDchJaUVq3zqc2a7q6+SreP0NDU9twdJrGGeOkkVF5UxLSNfmR5VamfzZ+pi6uNAXf+7hVSuQZYS+BzZVUXnRX5MgTIm4xhAoGAZKX62qcEWl7tCHfyOxyFmgywN6MQfEylQq2l5vskM+HxJQvQ5OblwxI7omu6AZfNfSg4dK7wjUbuvIGrKpvD28ssie2oX2XMVdJJ4/Vb2uhBW9qvFPYNsFa8kgdItwtw8H5I19aQbj+JgHIxYKlPMga+GJFoDsX2Zrv1d0SlqQ0CgYAs3kpFxYovuZGMKtWY02znwNBf+5KPP3Ovsg4xdsZNgZZDj4SdyEDDhR4TwMH0gmdHJadDmPVCPFFzokC6CW1loTBdBgIXRQtKqr0ODtWbfY8sLIl4BC2n/zLIiL5U81IdV3X8JCU+200XUwNWaJOzpxuoBJIj2XzuoRBdpsBIYg==";

    protected static Logger log = Logger.getLogger(InvestmentAssetmanagementTaaccountopenqueryTest.class.getName());

    public static void main(String[] args) throws Exception {
    	// api地址
        String URI = "investment/assetmanagement/taaccountopenquery/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, PRI_KEY, APIGW_PUBLIC_KEY);
        try {

			// 请求实体
			InvestmentAssetmanagementTaaccountopenqueryRequestV1 request = new InvestmentAssetmanagementTaaccountopenqueryRequestV1();
			 // bizContent实体
			FtasChannelTaaccountopenqueryRequestV1Biz bizContent = new FtasChannelTaaccountopenqueryRequestV1Biz();

			// 请求报文IN节点实体 设置属性
			bizContent.settASerialNo("4501");

			// 请求实体 设置属性
			request.setServiceUrl(BASE_URL + "/" + URI);
			request.setBizContent(bizContent);

			log.info(request.getMethod());
			InvestmentAssetmanagementTaaccountopenqueryResponseV1 response = (InvestmentAssetmanagementTaaccountopenqueryResponseV1) client.execute(request);
			if (response.isSuccess()) {
				log.info(response.getReturnCode() + ";"+ response.getReturnMsg() + ";" + response.getReturnContent());
				log.info("success");
			} else {
				log.info(response.getMsgId()  + ";" + response.getReturnCode()
                        + ";" + response.getReturnMsg() +";" + response.getReturnContent());
				log.info("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
	}
}

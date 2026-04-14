/**
 * 
 */
package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

import com.icbc.api.request.BamvasCooperationinfoInfoqryQueryRequestV1;
import com.icbc.api.request.BamvasCooperationinfoInfoqryQueryRequestV1.BamvasCooperationinfoInfoqryQueryRequestBizV1;
import com.icbc.api.response.BamvasCooperationinfoInfoqryQueryResponseV1;


/**
 * TODO  
 * <pre>
 *
 * </pre>
 *
 * <pre>
 * modify by kfzx-huangguangyu on 2019-4-12
 *    fix->1.
 *         2.
 * </pre> 
 */

public class BamvasCooperationinfoInfoqryQueryTestV1 {
	//MY_PRIVATE_KEY 用于加密请求报文
	//protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDN+LQNaeLvQ4Y0ik2s5nf/p30qBDu9iqpA2TMOe7aiHvuL4RQ2Vr/dwJaUBRoQgHSmE1Xog4sVsPUYwrTei7P0JEYrjDKoZTPPKIDlyWJIdri7/m5iFPgVWs6O5biaygqAbBFulTOAX1QljzyfwswZWZ50iryOnJA+WmZmFRXrOUdZg1pE9dlJhzo0eGmom0DdOtJfBT+PDntG+jtPTACyR6v+Ojn20vGtQOa5PqXJ2HPU/tA/D512ZoqUu1w9OJ/uSZVGoUIxrIpTXi8b02OHtXoiNeBLba5Ez/GwFMdTxmSx3ePJg6+9B9hubqj+1LxES+ufo2wG9oM6GBoM0sJVAgMBAAECggEBALIRyQArwhFezGESDNNlMOtqjvBLVqaogkasQNLNq/h/VrP49HOCDkCV5tizjHQlyjNmVgg96XnoUg2FD7+zAfIZhjzR6V3imKcJel6u+3Rwh4eoESBkxpIXvqqV+kKAWBHs3ClBfpQGVoZeTmhEjhWAgFh7NUcKHj56AztQEXIYtwOg0+RHT0QpP7e++Nz8nm/fc7cZ5k3Gvn0lqVeU/6mdKLFz2qLEQ03HwxmBCrUJU+I4LQ4ghOyQR1RC2I3lkxKzo/KQiX+0fxrI7UqAR3+5YgxBICaI1v1n5Yk9G5BKw45mblKIpGIiuJhUebp28vrUt4S5D0nXIPvPP6ntMsUCgYEA5wh0raupwYs4yO/6ZsUK09aVCrYXuX7m6lV8RvLK3/DLKMP6Hd7r/7Hq0pd4dO6ZErVgwDxGck54n0r+dLAbhTKe7+gzew6QZeq8k/QjqdhJulRpfBlA5Rayg0RfSUfVuucnlpZ2+y1/BCqWrBWv3BLiN5adIiN58yEjNbC98z8CgYEA5DrrJ2HLRlVit2eNAS+LqD6bUUds8PZIZQfm4mVJTuusleKbd/E2djVa2uAXdI2bDPo9/CyixtYymywf3BXFuLCsygnCTPeEjjYk5Iw0wHw2JCECqIprzLUxfh7+u/aKRpMdcOvBlcrXNo87A6uZCJ1GLdvrstkkc/+r3/VHKWsCgYBOeorX67YQbrBtYUdX3Zsd9ohOSSip9DeQZlV+7D6J74uzX075Ep5sDw3LV3Stk9cficlBw7fSYGGNCraBAMl4Z4ehyuih5oKafDBuucHbkgMJbl8erp1itGETQgh0JhiuEpNaDeaA0CWTTrw73GoOgcOiSmE6FX75XcpOFKmRlQKBgAVfmb/Anq+WPQ/CYWCAxRH9/1wniVoXAldWm0oDRnmUNOkGf9CQHk8kLHJwyjyVROxGTAVO67owutpKbKma3Z/ekQLkve1f+n/6qF21K1zIz+GD4XMkZytXrMWiZy73vAOzXQMWmeUz9GM1dhc9CApa4Zcc0WzRjfkpMNvCA53nAoGBAL8ktkWk61HxNVPMphYUXeB+FFBxmdbH1pGLka/YLIoinLpTDq7vciZ36U3m0bsnWhjRH6wbCDip9+pmUlVezXCa90mOZhFsOnbfcLgnraVna/yNwgpJmUaTWz5tla9I/ZO+SIsT5FjH0h9ilT5EAhdQg+OSQjoW3QrQMMUSxkXS";
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCpL9BnrwwXIdBi9Ls64EeTxW/g0hI9F/NK+5U/EHdnY+vWV0fcftxM2eE+UCetJ4jSIMlhtai959l+/eXXX7H1ckcWoej72to49RYm6U1f/FnzFgMja2voFGlK2PuWBuvKMYTeWG/IOoelCrkOEl7qFoFNxQSKzfceU2yTJR36/JYRXk9jSqYoj5o75qHGLGnQjXxF7nNeUr4sYVSMn2BrLs2r4xhW2LpS2phwfLlNYs4xlnT/TYSgeRR+7SZ4qoKZ4h/fYkfocnlW2EOYglsSgjFq9a9udkP2DsRasz8GXTB0dwOgJ55GSAezW+KJGbZQNfbPWrG+0fp7YKUN1T5dAgMBAAECggEAStIpcWEZkLVUih6/GneLUA+VvbBJfpkz8Co4w64NkDzBVIY+8oVr+dS1ZzBdi5cbTApR5RNIb8u0BRRV7ByW//ws8hZiidcsgNnqlQTCQjvAr99BPg3Ip9c3BXHWOXAQINgrcYVc0gkIafXHBzEapp2TS4hgzN3vdorgTmawzojhdWwqwUqS3ALylHUzXKdxxar32hWVFkBWW5Dbe4qf4r1JdAavpjj3eYJZ1aqOPBBGc6zsLlMC3cPngWUHr3PKuDyP5oxHRS+QMMv/40PevD2ZI0bgoCr9Nv1CoXI2yr9pc6Rnd0qtM0srl47m12niCNoDIkiltyWvYnjySqPb5QKBgQD024tAh1OErsNweq29KY80oy1toA+B8q/37fsAZmw/jWiqwqLg/IR3brdCd4xGIMeV0jqmObNim850QkO82JLJBiRm0bplLoieBVkYsceuEGnajPhMueI0DbyCuCW33eWFYTfwOyZFVP0h8BK69nfw4VimzsVCuLbrTivY4e7BQwKBgQCw4r8shkIVkqeU1vGmR73k3mddvG0IwhHL/qbaA9tjhiI+I8jKkb/SDqbTfqMqAcoXVF4LLU5NSrrieKGqYgeKsUsE9g2Wc4JOkKh9czCg6ASPNmNRB9bjL/oydDw2IGtD6rbPzxXjWitmoF2QRglgJm01CsUT3x1FiDLNrTy33wKBgQCjOsws+0nQ9NQy4oV5yf++fl1lbEOKRGfMZuhiDuVwxGsXEuOUCqKgH72pYJqxkV6b4ObaMzMDtIw/8oDHnrTTr/WKWZvhodDPTw9jJ/jOcACxAjLwqyZBvzqBBhOW7CT68qFg032i0JJjEnDaFD5G5WK86LCV1x+R463VSmBokwKBgQCGmlWd/SCmp+3sXlCPF0LSucdw1iUmp//A+yxe9NZq+rRuEu8F7zlIEKw0fsaM/cWaUTIrUTOnr3ldQyzKqkRQ7y9GPDMEzStYu83rgM99cnGdxWZ4T/3e7jB3WmpDShI2IZMxucVPN9uVAe2RDyqtRzCNT3++6mas9cKrLuvogwKBgQDkLK7WrEiEgzVtcgn9YY5foD1XDmHBdd3o2ikwGuB4LVn9GKb+yjkFmXWaqSCX0qJoDvur02EWDSC8LHxG04QfPiGO9Pbm9g9J8ErcC3kuchw6EXXT1Sgwh7gcXj708Ss9hauW2b7Vo3nXN0vp+THyq+ydQQxclkdk/kfdnL/TgQ==";
	
	//APIGW_PUBLIC_KEY 工行公钥，用于解密返回报文
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMpjaWjngB4E3ATh+G1DVAmQnIpiPEFAEDqRfNGAVvvH35yDetqewKi0l7OEceTMN1C6NPym3zStvSoQayjYV+eIcZERkx31KhtFu9clZKgRTyPjdKMIth/wBtPKjL/5+PYalLdomM4ONthrPgnkN4x4R0+D4+EBpXo8gNiAFsNwIDAQAB";
	
	//APP_ID 工行API接口的ID
	//protected static final String APP_ID = "10000000000000002708";
	protected static final String APP_ID = "10000000000007534633";
	
	public static void main(String[] args) throws Exception{
		BamvasCooperationinfoInfoqryQueryTestV1 a=new BamvasCooperationinfoInfoqryQueryTestV1();
		a.test_cop();
	}
	
	
	public void test_cop() throws IcbcApiException {
		BamvasCooperationinfoInfoqryQueryRequestV1 request = new BamvasCooperationinfoInfoqryQueryRequestV1();
		request.setServiceUrl("https://ip:port/api/bamvas/cooperationinfo/infoqry/query/V1");
		BamvasCooperationinfoInfoqryQueryRequestBizV1 bizContent = new BamvasCooperationinfoInfoqryQueryRequestBizV1();
		
		bizContent.setAppId(""); //应用名称
		bizContent.setCorgNo("");  //合作方编号
		bizContent.setFuncId("P001003001");  //功能ID
		bizContent.setIndustry("1");  //场景代码
		bizContent.setQryType("2"); //查询类型
		bizContent.setSysNo("240290000065338");  //编号
		bizContent.setTlno(""); //柜员号
		bizContent.setType("");  //类型
	
	    
		request.setBizContent(bizContent);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		BamvasCooperationinfoInfoqryQueryResponseV1 response = client.execute(request);
		
		System.out.println(JSONObject.toJSONString(response));


	}
}

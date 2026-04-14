package com.icbc.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.EpasscLogonRequestV1;
import com.icbc.api.request.EpasscLogonRequestV1.BusiData;
import com.icbc.api.request.EpasscLogonRequestV1.BusinessEntryData;
import com.icbc.api.request.EpasscLogonRequestV1.EpasscLogonRequestV1Biz;
//import com.icbc.crypto.utils.TripleDesCryptFileInputKey;
//import com.icbc.crypto.utils.Base64;

public class EpasscLogonRequestTest {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMNfWT8xOPrpQAS+88loK1rjUB4EdHvCFQ4mhlZqNM0ABt4LgDXkfSM8w4Q/kWTzmjxoGm2SY32K5VBfvg3fSr43TrNdMDurPxBAsYpe1YnBXx+NxwB0GZ3dL2mMsgu8z1plDIgVqUfHwkczuGsUvWu432CC/h1EQfG2qwM2xudNAgMBAAECgYBMVYbDP/IpZtUShaEuwjqeSk5VTBxO9mfPnZHDVb/HkQkd3JR9egbCq80gcM3YCa3riCk7QoUI2in6m03gkavbs1fQJKMgp53qXYmbV18sivkOAkt4YU50MFTUrWt47TRlnvblNwZNA3P5+UvTgD8FCNZbq0ehZRiwdqoDHNBx6QJBAOdegVgusYky3mm03/TSGXEJ68XN9KIgqxhPkrNWGrO/FzBt/V9HwjBDLfSS+qp8xEGWU8SLFrq7QbQEPY9Ws5cCQQDYK9OY4O8ujxqrsojFr9G1ohMOKV47rek0E/83k1hYK/e/5sh6j0+MAl+jL08P6JT94T/Bb4UFMkBxZpbgcwi7AkAIu+6SD6EwFrWCtAtISK4hMEiwPOghPq52yno1PujfJDWR5HUOJb1U6RRRCUNhMDiQaUZcQwvmnK8MRqS0RoedAkBx594Ob2/445UVJfUl3bgt8iICU1ABOGlqdKMFwNFLXJemec/nrL4os1N7pEEPy+k+QcObhMzd6RXF613TFSpjAkEA5uf5ozWTzTWpvkZYrvIIcHH05PCEMmX+0FbAZLoYpCpyKzd/7+KNt+OO5TJDEvab7U8+ICOQIbNms3vi2p3i3A==";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000794554";

	protected static final String PASSWORD = "12345678";
	
	
	private static String from = "CHRM";
	private static String timeStamp;
	private static String expireTime;
	private static String func_id = "51001";
	private static String version = "1.0";
	private static String fromDomain = "";
	private static String chanData  = "";
	private static String busiData  = "";
	
	private static String  TripleDESKeyFilePath = "";
	

	public static void test_cop() throws Exception{

		String castr = APIGW_PUBLIC_KEY;
		// 去除签名数据及证书数据中的空格 added for Safari
		Pattern p = Pattern.compile("\\s*|\t");
		Matcher m2 = p.matcher(castr);
		castr = m2.replaceAll("");
		
		
		// 构造client
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY,
				IcbcConstants.CHARSET_UTF8, castr, PASSWORD);

		EpasscLogonRequestV1 request = new EpasscLogonRequestV1();
		request.setServiceUrl("https://ip:port/ui/epassc/logon/V1");
		
		
		//构造BusiData
		BusiData  busiData = new BusiData();
		busiData.setForward_url("forward_url");
		busiData.setCallbackmodpwd("callbackmodpwd");
		busiData.setBack_url("back_url");
		
		//构造BusinessEntryData
		BusinessEntryData busiEntrydata = new BusinessEntryData();
		busiEntrydata.setFrom(from);
		busiEntrydata.setTimeStamp(timeStamp);
		busiEntrydata.setExpireTime(expireTime);
		busiEntrydata.setFunc_id(func_id);
		busiEntrydata.setVersion(version);
		busiEntrydata.setFromDomain(fromDomain);
		busiEntrydata.setChanData(chanData);
		busiEntrydata.setBusiData(JSON.toJSONString(busiData));
		
		//构造bizContent
		EpasscLogonRequestV1Biz bizContent = new EpasscLogonRequestV1Biz();
		
		String encryptBusiEntryData = JSON.toJSONString(busiEntrydata);//需要对encryptBusiEntryData做3DES加密后传入到bizContent.setBusinessEntryData()中。
		bizContent.setBusinessEntryData(encryptBusiEntryData);
		//bizContent.setBusinessEntryData(encrypt(JSON.toJSONString(busiEntrydata),"utf-8"));
		
		try {
			request.setBizContent(bizContent);

			// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
			System.out.println(client.buildPostForm(request));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
	
     /**
     * 使用3DES加密明文,输入字符串将按dataEncoding转换为字节后处理
     * @param sourceData 明文数据
     * @param dataEncoding 该明文数据的字符编码方式
     * @return 3DES加密后的密文
     * @throws CryptoException
     */
/*    private static String encrypt(String sourceData, String dataEncoding) throws Exception {

        try {
            byte[] bTranData = sourceData.getBytes(dataEncoding);
            byte[] outTranData = new byte[(bTranData.length / 8) * 8 + 8];
			//
			  //加解密的方式：0加密，1解密
			  //内部加密方式：1--ECB;2--CBC;3--CFB;4--OFB
			  //内部算法方式：0 加加加，1 加解加
			//
            final int module = 0;
            final int method = 1;
            final int destype = 1;
			int ret = TripleDesCryptFileInputKey.IcbcTripleDes(bTranData, bTranData.length, outTranData, module, method, destype, TripleDESKeyFilePath);
            if(ret < 0){
                //返回小于0的含义：
                //-1：输入参数in或者out为空，
                //-2：缺省密钥长度（16进制ASCII表示）不是32或48个ASCII字符或者为空
                //-3：输入的加解密模式参数是”0加密，1解密“以外的值
                //-4：将缺省密钥转换为16进制表示的整形的时候出错，缺省密钥为非法密钥，密钥字符串的范围为[0-9]、[A-F]、[a-f]
                //-5：加密模式时，加密输出的缓冲区长度不够，最小长度应为(len/8)*8+8
                //-6：解密模式时，输入的长度不是8的整数倍
                //-7：解密模式时，输出缓冲区的长度不够缓存返回数据
                //-8：对密文进行解密后得到明文不符合PKCS#5的补位规则，输入的密文错误
                //-9：IV向量为空，或者IV向量的长度不是8
                //-10：加密或解密输出的缓冲区长度不够，最小长度应为len
                //-11：内部加密方式method不是1--ECB;2--CBC;3--CFB;4--OFB这几个数值（1/2/3/4）
                //-25：内部算法方式：0 加加加，1 加解加，数值是（0/1）
                //-26：in输入数组、out输出数组的长度不是8
                //-27：密钥长度不符合要求
                //-28：析取密钥失败，密钥文件非法或已损坏
                throw new Exception(ret+":TripleDesCryptFileInputKey.IcbcTripleDes()");
            }
            return Base64.icbcbase64encode(outTranData).replaceAll("[\n\r]", "");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
*/
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		test_cop();

	}
}

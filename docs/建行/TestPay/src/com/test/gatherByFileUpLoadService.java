package com.test;

import com.ccb.mktpay.sign.RSASignUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.File;
/**
 * 对账单推送接口
 */
public class gatherByFileUpLoadService {
	
	public static void main(String[] args) {
		
		String filePathStr = "F:\\TestPay";
		String fileName = filePathStr + ".zip";
		File zipFile = new File(fileName);
		ZipUtilTest.compress(filePathStr, fileName);
		try {
			sendFile(zipFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendFile(File zipFile) throws Exception{
		String fileSmryInf = "wenjianzhaiyao";
		//SM2算法采用该私钥
		//String  privateKey  = "20A6B71FEE52B30BD8669911BA1EEA9B530E24D0CFADF4694CBE10B358A884B6";
		//RSA算法加密
		String privateKey_rsa = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJxqHNPs/1lF+lrRy/jVKaBV4bV/cjVuSPVuaVf9TjxylbL3K9dFUW0HT94+3xqnFmW8IhJc4XzcDRz3L3rmXUzBSSlzH00xpHj1omhDXAGBTzksHmLx2sKdQsb0kJL//vQ0FpWr9In8CDCVABhDyz7SAN834lOX08VGARqoPGp3tIHmOJyDtasERktxEJJw4vhsjTUye6T3RPiGefsRZFDmC3D5HTLlwunr/zIYRHA/KrWSCNAifldcwIBonZYqicRjYnkFCM4seTJlwePOaN8zCMdfAXHzCcCnj9rhVJFRwpLv/y7cTgF58C2uQVil9WxnjF1OOhV1+0ks5x9i79AgMBAAECggEAKMGsvaWWKUSyIrWaKoQo6k0qMJaOElMzG8AOSC1fkd4pusLHg4n1XeFeqniRvAq6rxf3zox7cgk4wBhJH0Hk99VDRLYbXhxQyth6R6iWqfO1xvQkDe7kLTjWfiqRhXlAkcvofr0MlvRDI8BOfZRbzLIZ6Gaea26dQIqJPNCfWNVjQuZz7uuivs+CtI3gK5+tPHTklBmPWnfpfQQXGXQP4rnoziylY13KD3cYcvB49XhxGKRXymhU/lj4elC1YOfG/K4K5IUN4sHDjxAqBp5viKh8OaLUEc7k9+a7A+tZ/Wr9bJd+wh3Q/QvNC7piAV3oZ8FesTakvWoFNSfnlzqO9QKBgQDlld/DbJaPcyYW6thyZWdPHJAofjm7XeCk8eoJMKNVx1Wo5PRjLUXtFhWooQjofnvlhSKG2/7ISCVm47KylmVh36KihYF6FOm59oa0FREfMnqEEJ+rZ+ckHlr4hOmIlcvMqW7icRPVD8rLvQtWVFga2IjXIfQtkRUZ8i6IoxLv+wKBgQCZoKIgEBnDHMqBFmdpO69BaokQ9KMQN9bMPLRutULX+M41VJ2fz/nRpw1TCDC4wVfBE2QIN+J2Ry4azwklUeU7X/EAa1n7AjRdWB8HKl/y1tfGln8iW2HzSSyhg7xuDiKYrAOFUJBOS5hrzoN8HdDA8FvkBprl/vNdtdviahgTZwKBgQCCgnNEPUtNWpDxgCjwxtI2d68/RZn99/zG5zo6ZSrEjV8VdqmyYz8X6nD/fiN5PsWhkNAyx4aLRe/1EEU6HiKdw6pJJwmWY6MF8q1aW4tzJ5fb0TNjFdqgfp0KH0w+N6E1w69kDBHREXwf22RBfArln+gSG5wZ9xp+uFxZkhIm1wKBgGmB0XZ1uEuwrT7kdRbnn6Asm3/ik06jGsjfdAeIQyTyQiSMPUixW9/pe5QnztZEKpF2UL/4KXaTwg01XRGdYfJaHLjuATkLNY1Z5M1WA9lSRZSkbSHaYrXj7lvqjnGDEa2KjUx0nPa4ojB//vsxutmW+XTsOFt2sgsMx7uCo5BHAoGASua4g7T909yPvMjCe/R4+c254jTdXN/ww4tmVgkmT0l9lM7PowDaU4uvNi+A+KSMp4VqF/bZS2FPO15oZ2KyVmt1Ea3OueQyOpDnWZo7FRqvZ8XyjEqMsQsC/G1THearHlh2HWgoUT06niQF1K9LdUa/A+5POmadlwsTAtuEwSA=";
		//String signInf = SignUtil.sign("file_Smry_Inf=" + fileSmryInf, privateKey);    //SM2算法加密
		String signInf = RSASignUtil.sign(privateKey_rsa, "file_Smry_Inf=" + fileSmryInf);
		String receiveUrl = "http://127.0.0.1:8080/testpay-online/receive/receiveDivsionFiles.do";
		PostMethod filePost = new PostMethod(receiveUrl);
		HttpClient client = new HttpClient();

		try {
			
			Part[] parts = {(Part) new StringPart("File_Smry_Inf", fileSmryInf),
					(Part) new StringPart("Sign_Inf", signInf),
					(Part) new FilePart(zipFile.getName(), zipFile)};
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			
			int status = client.executeMethod(filePost);
			
			if (status == HttpStatus.SC_OK) {
				System.out.println("上传成功");
			} else {
				System.out.println("上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			filePost.releaseConnection();
		}
	}
}


/**
 * 接收文件参考代码
 */

/**spring-mvc开发时可使用以下方式进行配置：*/
/*<bean id="multipartResolver"  
class="org.springframework.web.multipart.support.StandardServletMultipartResolver"> 
</bean>*/

/* web.xml中配置
<multipart-config>
<location>D:\\upload</location>
<max-file-size>209715200</max-file-size>
<max-request-size>419430400</max-request-size>
<file-size-threshold>0</file-size-threshold>
</multipart-config>
或
application.properties中配置
spring.http.multipart.maxFileSize=200Mb
spring.http.multipart.maxRequestSize=1024M
*/

/*@Controller
@RequestMapping("/receive")
public class ReceiveFilesController  extends BaseController{

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver getStandardServletMultipartResolver(){
            return new StandardServletMultipartResolver();
    }
    
	@ResponseBody
	@RequestMapping(value = "/receiveDivsionFiles", produces = "multipart/form-data; charset=utf-8", method = RequestMethod.POST)
	public void receiveDivsionFiles(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("接收到的参数: [File_Smry_Inf = " + request.getParameter("File_Smry_Inf")
				+ ", Sign_Inf = " + request.getParameter("Sign_Inf"));
		//验签原串
		String oriString = "File_Smry_Inf=" + request.getParameter("File_Smry_Inf");
		String signInf = request.getParameter("Sign_Inf");
		
		//SM2算法采用公钥
		//String pubKey = "04083E2CA7E71E51DB5374A49A3C07066390BD18C53B12A939D54C33E39E6916386F448B81D003BF76155EFEA565CD9818F6B84E846CB57CD4364BC715766D4FEC";
		//RSA算法加密公钥
		String pubKey_rsa = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAicahzT7P9ZRfpa0cv41SmgVeG1f3I1bkj1bmlX/U48cpWy9yvXRVFtB0/ePt8apxZlvCISXOF83A0c9y965l1MwUkpcx9NMaR49aJoQ1wBgU85LB5i8drCnULG9JCS//70NBaVq/SJ/AgwlQAYQ8s+0gDfN+JTl9PFRgEaqDxqd7SB5jicg7WrBEZLcRCScOL4bI01Mnuk90T4hnn7EWRQ5gtw+R0y5cLp6/8yGERwPyq1kgjQIn5XXMCAaJ2WKonEY2J5BQjOLHkyZcHjzmjfMwjHXwFx8wnAp4/a4VSRUcKS7/8u3E4BefAtrkFYpfVsZ4xdTjoVdftJLOcfYu/QIDAQAB";
		
		//boolean b = SignUtil.verify(oriString, signInf, pubKey);    //国密验签
		boolean b = RSASignUtil.verifySign(pubKey_rsa, oriString, signInf);    //RSA验签
		
		if(!b) {
			throw new RuntimeException("验签失败");
		}
		//存放路径
		String dstPath = "D://receiveFiles";
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
			Iterator<String> iterator = req.getFileNames();
			
			System.out.println(iterator.hasNext());
			while(iterator.hasNext()) {
				MultipartFile file = req.getFile(iterator.next());
				String fileName = file.getOriginalFilename();
				System.out.println(fileName);
				String filePath = dstPath + "/" + fileName;
				File desFile = new File(filePath);
				if(!desFile.getParentFile().exists()) {
					desFile.mkdirs();
				}
				try {
					file.transferTo(desFile);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}*/
/**
 * 
 */
package com.icbc.api.test;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BamBamapApplycardApplyRequestV1;
import com.icbc.api.request.BamBamapApplycardApplyRequestV1.BamBamapApplycardApplyRequestBizV1;
import com.icbc.api.response.BamBamapApplycardApplyResponseV1;


public class BamBamapApplycardApplyTestV1 {
	//MY_PRIVATE_KEY：京东密钥，用于京东加密请求报文
	protected static final String MY_PRIVATE_KEY   = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC07rtvqy8mRKi1VwW8qftOctVUMidwRYIyGIjxlv1JkfUBMfxv50u3OMYhJKoCncmn2T0NJEu/BIdxy67pSIo1JKmWRqPGIwDYzSZyarENNL6hBGSr3U8b3ET34lELbAu2eQk6cwA4m1VyT7NkMV3mDWXMRBiMu4ECAiVm3MJE2cgnYPaQvoETL6XGh7rkPhipjcY49gPFQh5jNZP3M0iEPNfz54oXUMPObfawz4DqUIXK9iqefJhubBmn8aE9MCX/l7zOAFBEn6jqlqLKeMjklb8mCy1iUvLyM/SVKTVerwoPiOc43Htq9I4q4HfY3TO15bIR5rSVSJlAqZh5emdlAgMBAAECggEAclLOeHbP6fxEJaEA7P7y3dTNWs/9/4DMDV9XWF+xV4U0LDcrbIHXY8Qp3NNP4StWOgqX+u/ONLpl4s9yi9DcE0xBI8Uqwo1ngZXPD0i+6rtDIHaoLbg+vjnmEkMcNn1kwT2lSRtVLm9oBDy7+zj+PdiFVBZK9NGdaGl2vJIM5nctJTc5K9szTQaE6eGGyc6tLqfYrsycd6BPby8fdaW6vuYweggcRM0TAPvm05Ot8EbqFc8NWep5CbNanDVg0HV+4aiM4TQccUInZSTLGR8wVie/ECIuILYw4+OOQmOzdLOa6xd1BWJ9xolwsVh6RCzMVUetjah89iCStLZdrKKK2QKBgQDiCHzRgV1bCnZKy7mrWDd+bYQq8xKGm7S3bSIrA7UgE96hmKTa044B0hk1nfcNxEtc3F4YyXFgWxRnljHBMl8LY6U4+MVhKysL7cbSn/CwynFL2Sdsp214s8vRjx3Pd6W4JFIweVdGX947qlhQlqzfrlL3bqKB8ATxXyV5mSs89wKBgQDM64pg6Idrvejb36aHQsvRL1ufQzwVKnZl9n4lU6dnApSxVD8SixTR5eJvsieVXETSGzvmZz0glSOdpX0tMhVe3VUg0n3Z3JhtnMc0yUX5VO73xU8VpEFx0sL5s/JckKdfmgQU+F+v6XlQhNohUdj8Egh+GDTWdF3sqsxM2jMzgwKBgC/ZwCLSEiNd2aEH8+x0JntbT+mPv9JDUESsk9krVcLOsgjfubahpqR4B+Iz6MM/VPz5oen5a0kMvwsW3r2ZnklY9PdUouRpMRm7YqXgOiITGV0HQbNc1m4BEhrxA0ySdcC/365nYbSCQxe6fivBWiOYUfQTtc9wMnuuus49QMwNAoGBAIB1+B2gJ6THPL75iW+p1BOi2JESmQu7vtVUnOfRdVQCLiysX43EWpzqu/TBfkcHvKzqHbbmaU+PrvAY2kwG5Za+sasNyY9V1siAS/EvbApN0DDLJDSw78XnFgtrXzfgBszLxFtRCMtCVabAYe4ZXSlINMK3cggukuLWf5R+BTL5AoGBAI7YFgxVGjdfO0VtwRrBjy6z5u8c1HCQ+I1Qu/XY+mBKLmsRLaF2XZO1G5gtDVRD0zJa/+HRBQFUOm4EhjRodkivfZddE+OEXMlw+Bj6rNX+/NNV0wF763deiXdv2wVc8SzQJ1RPRf8piW0KKvsL1ZFwMjZ4MDqX8w0PUUS4Fa1l";
	//APIGW_PUBLIC_KEY：工行公钥，用于京东解密返回报文
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//APP_ID：工行API接口的ID
	protected static final String APP_ID = "10000000000004094595";
	
	public static void main(String[] args) throws Exception{
		BamBamapApplycardApplyTestV1 a=new BamBamapApplycardApplyTestV1();
		a.test_cop();
	}

	
	
	public void test_cop() throws IcbcApiException {
		BamBamapApplycardApplyRequestV1 request = new BamBamapApplycardApplyRequestV1();
		//request.setServiceUrl("http://ip:port/api/bam/bamap/applycard/V1/apply");
		request.setServiceUrl("http://ip:port/api/bam/bamap/applycard/V1/apply");
		BamBamapApplycardApplyRequestBizV1 bizContent = new BamBamapApplycardApplyRequestBizV1();
		bizContent.setApply_id("1234567890000000000000008");  //流水号
		bizContent.setFirm_num("JDJR");  //公司代码（二级来源标签）
		bizContent.setChannel("0001");  //渠道代码（三级来源标签）
		bizContent.setPage_num("0000");  //页面位置代码（四级来源标签）
		bizContent.setSrc_level5("0000");  //五级来源标签
		bizContent.setSrc_level6("0000");  //六级来源标签
		bizContent.setSrc_level7("000");  //七级来源标签
		bizContent.setCity_num("00200");  //申请地区号
		bizContent.setId_card_type("0");  //证件类型
		bizContent.setId_card_no("440111199111111115");  //证件号码
		bizContent.setName("网银测试");  //姓名
		bizContent.setName_spell("WANG YIN CE SE");  //姓名拼音或英文名
		bizContent.setGender("1");  //性别
		bizContent.setBirth("1991-11-11");  //出生日期
		bizContent.setId_card_end_date("2035-12-31");  //证件有效期
		bizContent.setAp_nationality("156");  //国籍
		bizContent.setMarital_status("1");  //婚姻状况
		bizContent.setEducation_degree("3");  //受教育程度
		bizContent.setMobile("13500136000");  //手机号码
		bizContent.setHome_st("1");  //住宅状况
		bizContent.setHome_add_pro("广东");  //住宅地址省份
		bizContent.setHome_add_city("广州");  //住宅地址市
		bizContent.setHome_add_county("越秀");  //住宅地址县(区)
		bizContent.setHome_add("文明路123号601房");  //住宅详细地址
		bizContent.setHome_postcode("510000");  //住宅邮编
		bizContent.setCompany_name("中国工商银行股份有限公司软件开发中心");  //工作单位名称
		bizContent.setCompany_posit("5");  //职务
		bizContent.setCompany_tel_are("020");  //单位电话区号
		bizContent.setCompany_tel("83820012");  //单位电话号码
		bizContent.setCompany_tel_ext("1062");  //单位电话分机
		bizContent.setCompany_add_pro("广东");  //单位地址省份
		bizContent.setCompany_add_city("广州");  //单位地址市
		bizContent.setCompany_add_county("天河");  //单位地址县(区)
		bizContent.setCompany_add("中山大道中82号");  //工作单位详细地址
		bizContent.setCompany_postcode("513029");  //单位邮编
		bizContent.setEarn("12345600");  //本人年收入
		bizContent.setCompany_kind("10");  //单位性质
		bizContent.setOccupation("6");  //职业及职级
		bizContent.setContact_name("余春娇");  //联系人一姓名
		bizContent.setContact_relation("5");  //联系人一与主卡联系人关系
		bizContent.setContact_mobile("13800138000");  //联系人一手机
		bizContent.setBa_deliver("2");  //卡片领取方式
		bizContent.setMailing_address("1");  //卡片寄送地址类型
		bizContent.setRec_poin("620");  //领卡网点
		bizContent.setCity("广州");  //办卡城市
		bizContent.setCard_logo("46009538");  //营销档案编号
		bizContent.setSale_num("DX914020001181015200");  //营销代码
		bizContent.setCust_id("");  //联名单位会员号
		bizContent.setCust_data_flag("0");  //简捷办卡标识
		bizContent.setSceneno("460020190823ETCAPI00");  //场景代码
		bizContent.setAuxiliary("");  //辅助信息

		request.setBizContent(bizContent);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		BamBamapApplycardApplyResponseV1 response = client.execute(request);
   //第二个参数送前面的流水号，用于防重放检查
		
		//System.out.println(JSONObject.toJSONString(response));
		if (response.isSuccess()) {// 业务成功处理
				//申请单编号
				response.getAppno();
				//。。。。
		} else {// 失败
				;
		}

	}
}

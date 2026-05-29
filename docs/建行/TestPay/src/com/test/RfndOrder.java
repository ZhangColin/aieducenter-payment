package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.ccb.mktpay.sign.RSASignUtil;
import org.apache.commons.lang3.StringUtils;
import com.test.HttpUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单退款接口
 */
public class RfndOrder {
	//访问 地址
	private static final String URL = "http://marketpaykone.dev.jh:8028/online/direct/refundOrder";

	public static void main(String[] args) {
		//RSA算法采用该私钥
		String private_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu";
		//平台公钥
		String plat_Pk = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vbHtcG6udUl8OPW2YMyHY/0oYletfG/7zQ87RYSv4ltZw9czMaO9mA/ch0V/I1Vp6wcg8C9fNdOxytp2Gx7vLdwXMXfJN/7aWYKLGwzqFxMQ7iRtNnMEseI2wiuseVlr7nBjvrY6DGgdaTxLqyukfAFcpHBMwenqNGxko8emVA4d6VBEjF0ypF6EDv3yaH6tQYm1lKuSqQHXjS/soxVFGYNOTx9iu10RafJ8HvbQ0Nx671MdR4cjT/yBcVP10kfw+7mbGLmDnoYgV++pkM2A/D6HJ0tHoY6XMoaO0f45hrg+DugRnVsQrYaza60XeoNN9P0730YnLYrhCQDEkKn/wIDAQAB";

		//市场编号
		//String Mkt_Id = "43086363609829";
		String Mkt_Id = "41060860898016";
		//支付流水号
		String Py_Trn_No = "10555100000715401111929484212H";
		//版本号
		String Vno = "3";
		//支付流水号
		String Rfnd_Amt = null;//"0.01";;//"0.02";
		//子订单 子订单编号:退款金额,子订单编号:退款金额
		//附加项 子订单编号:减项编号:退款金额,子订单编号:减项编号:退款金额
		//退款参与方 子订单编号:减项编号:退款金额,子订单编号:减项编号:退款金额
		//消费券 子订单编号:消费券编号:退款金额,子订单编号:减项编号:退款金额
		String subOrders = null;//"105551000007154011119205954181002:0.01";//"105551000007154011017540443279001:1";
		String orderAdds = null;//"105551000007154011018055043403001:P430863636098290001:0.5";//"105551000007154011017540443279001:P430863636098290001:0.9";
		String parlist = null;//"105551000007154011018055043403001:43086363609829006213:0.5";//"";//"123123:666666:11";
		String cpnlist = null;//"";//"123123:666666:11";
		PartRfndDo partRfndDo = createPartRfndDo(Mkt_Id,Py_Trn_No,Rfnd_Amt,subOrders,orderAdds,parlist,cpnlist,Vno);


		String jsonString = SplicingUtil.createSign(JSON.toJSONString(partRfndDo,new PascalNameFilter()));
		System.out.println("加签字符串：" + jsonString);
		String signInf = RSASignUtil.sign(private_RSA, jsonString);
		//        String signInf = RSASignUtil.sign(private_RSA, "Ittparty_Jrnl_No=1572104638263&Ittparty_Stm_Id=TEST0&Ittparty_Tms=20191026234358262&Mkt_Id=43086363609829&Py_Chnl_Cd=1&Py_Trn_No=10555100000715410270011181789H&Rfnd_Amt=0.4&Rfnd_Amt=0.4&Sub_Ordr_Id=105551000007154102700111811788001&Vno=3");
		partRfndDo.setSign_Inf(signInf);
		String json = JSON.toJSONString(partRfndDo,new PascalNameFilter());
		//        String json = "{\"Ittparty_Jrnl_No\":\"1572104638263\",\"Ittparty_Stm_Id\":\"TEST0\",\"Ittparty_Tms\":\"20191026234358262\",\"Mkt_Id\":\"43086363609829\",\"Py_Chnl_Cd\":\"1\",\"Py_Trn_No\":\"10555100000715410270011181789H\",\"Rfnd_Amt\":0.4,\"Sign_Inf\":\""+signInf+"\",\"Sub_Ordr_List\":[{\"Rfnd_Amt\":0.4,\"Sub_Ordr_Id\":\"105551000007154102700111811788001\"}],\"Vno\":\"3\"}";
		System.out.println("传入的json串：" + json);
		try {

			String result = HttpUtil.doJsonPost(URL, json);
			System.out.println(result);
			//开始验签
			JSONObject jsonObject = JSONObject.parseObject(result);
			//验签原串
			jsonString = SplicingUtil.createSign(result);
			signInf = jsonObject.getString("Sign_Inf");
			System.out.println("验签结果:" + RSASignUtil.verifySign(plat_Pk, jsonString, signInf));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static PartRfndDo createPartRfndDo(String mktId, String pyTrnNo, String rfndAmt, String subOrders, String orderAdds,String parlist,String cpnlist, String vno){
		PartRfndDo partRfndDo = new PartRfndDo();
		partRfndDo.setMkt_Id(mktId);
		partRfndDo.setPy_Trn_No(pyTrnNo);
		partRfndDo.setVno(vno);
		BigDecimal orderRfndAmt = StringUtils.isBlank(rfndAmt) ? null : new BigDecimal(rfndAmt);
		partRfndDo.setRfnd_Amt(orderRfndAmt);

		//附加项
		Map<String, List<PartRfndDoSubDo2>> map2 = new HashMap<>();
		if ( StringUtils.isNotBlank(orderAdds)){
			String[] strs = orderAdds.split(",");
			for (int i = 0; i < strs.length; i++) {
				String[] orderAddInfo = strs[i].split(":");
				String subOrdrId = orderAddInfo[0];
				String prjId = orderAddInfo[1];
				String orderAddRfndAmtStr = orderAddInfo[2];
				BigDecimal orderAddRfndAmt = StringUtils.isBlank(orderAddRfndAmtStr) ? null : new BigDecimal(orderAddRfndAmtStr);

				PartRfndDoSubDo2 partRfndDoSubDo2 = new PartRfndDoSubDo2();
				partRfndDoSubDo2.setPrj_Id(prjId);
				partRfndDoSubDo2.setRfnd_Amt(orderAddRfndAmt);

				List<PartRfndDoSubDo2> partRfndDoSubDo2List = null;
				if (map2.containsKey(subOrdrId)){
					partRfndDoSubDo2List = map2.get(subOrdrId);
				}else{
					partRfndDoSubDo2List = new ArrayList<>();
				}

				partRfndDoSubDo2List.add(partRfndDoSubDo2);

				map2.put(subOrdrId,partRfndDoSubDo2List);
			}
		}

		//参与方
		Map<String, List<PartRfndDoSubDo3>> map3 = new HashMap<>();
		if ( StringUtils.isNotBlank(parlist)){
			String[] strs = parlist.split(",");
			for (int i = 0; i < strs.length; i++) {
				String[] mktMrchInfo = strs[i].split(":");
				String subOrdrId = mktMrchInfo[0];
				String mktMrchId = mktMrchInfo[1];
				String mktMrchRfndAmtStr = mktMrchInfo[2];
				BigDecimal mktMrchRfndAmt = StringUtils.isBlank(mktMrchRfndAmtStr) ? null : new BigDecimal(mktMrchRfndAmtStr);

				PartRfndDoSubDo3 partRfndDoSubDo3 = new PartRfndDoSubDo3();
				partRfndDoSubDo3.setMkt_Mrch_Id(mktMrchId);
				partRfndDoSubDo3.setRfnd_Amt(mktMrchRfndAmt);

				List<PartRfndDoSubDo3> partRfndDoSubDo3List = null;
				if (map3.containsKey(subOrdrId)){
					partRfndDoSubDo3List = map3.get(subOrdrId);
				}else{
					partRfndDoSubDo3List = new ArrayList<>();
				}

				partRfndDoSubDo3List.add(partRfndDoSubDo3);

				map3.put(subOrdrId,partRfndDoSubDo3List);
			}
		}

		//消费券
		Map<String, List<PartRfndDoSubDo4>> map4 = new HashMap<>();
		if ( StringUtils.isNotBlank(cpnlist)){
			String[] strs = cpnlist.split(",");
			for (int i = 0; i < strs.length; i++) {
				String[] cpnInfo = strs[i].split(":");
				String subOrdrId = cpnInfo[0];
				String cpnId = cpnInfo[1];
				String cpnRfndAmtStr = cpnInfo[2];
				BigDecimal cpnRfndAmt = StringUtils.isBlank(cpnRfndAmtStr) ? null : new BigDecimal(cpnRfndAmtStr);

				PartRfndDoSubDo4 partRfndDoSubDo4 = new PartRfndDoSubDo4();
				partRfndDoSubDo4.setCnsmp_Note_Ordr_Id(cpnId);
				partRfndDoSubDo4.setRfnd_Amt(cpnRfndAmt);

				List<PartRfndDoSubDo4> partRfndDoSubDo4List = null;
				if (map4.containsKey(subOrdrId)){
					partRfndDoSubDo4List = map4.get(subOrdrId);
				}else{
					partRfndDoSubDo4List = new ArrayList<>();
				}

				partRfndDoSubDo4List.add(partRfndDoSubDo4);

				map4.put(subOrdrId,partRfndDoSubDo4List);
			}
		}

		List<PartRfndDoSubDo> subDoList = new ArrayList<>();
		if ( StringUtils.isNotBlank(subOrders)) {
			String[] strs = subOrders.split(",");
			for (int i = 0; i < strs.length; i++) {
				String[] subOrderInfo = strs[i].split(":");
				String subOrdrId = subOrderInfo[0];
				//               BigDecimal orderAddRfndAmt = StringUtils.isBlank(orderAddRfndAmtStr) ? null : new BigDecimal(orderAddRfndAmtStr);
				String subOrderRfndAmtStr = subOrderInfo.length == 1? null : subOrderInfo[1];
				PartRfndDoSubDo partRfndDoSubDo = new PartRfndDoSubDo();
				partRfndDoSubDo.setSub_Ordr_Id(subOrdrId);
				BigDecimal subOrderRfndAmt = StringUtils.isBlank(subOrderRfndAmtStr) ? null : new BigDecimal(subOrderRfndAmtStr);
				partRfndDoSubDo.setRfnd_Amt(subOrderRfndAmt);
				partRfndDoSubDo.setPrj_List(map2.get(subOrdrId));
				partRfndDoSubDo.setParlist(map3.get(subOrdrId));
				partRfndDoSubDo.setCpnlist(map4.get(subOrdrId));
				subDoList.add(partRfndDoSubDo);
			}
		}
		partRfndDo.setSub_Ordr_List(subDoList);
		return partRfndDo;
	}

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static class PartRfndDo{
		private String Ittparty_Stm_Id = "TEST0";
		private String Py_Chnl_Cd = "1";
		private String Ittparty_Tms = formatter.format(new Date());
		private String Ittparty_Jrnl_No = System.currentTimeMillis() + "";
		private String Mkt_Id;
		private String Py_Trn_No;
		private BigDecimal Rfnd_Amt;
		private List<PartRfndDoSubDo> Sub_Ordr_List;
		private String Vno;
		private String Sign_Inf;

		public String getIttparty_Stm_Id() {
			return Ittparty_Stm_Id;
		}

		public void setIttparty_Stm_Id(String ittparty_Stm_Id) {
			Ittparty_Stm_Id = ittparty_Stm_Id;
		}

		public String getPy_Chnl_Cd() {
			return Py_Chnl_Cd;
		}

		public void setPy_Chnl_Cd(String py_Chnl_Cd) {
			Py_Chnl_Cd = py_Chnl_Cd;
		}

		public String getIttparty_Tms() {
			return Ittparty_Tms;
		}

		public void setIttparty_Tms(String ittparty_Tms) {
			Ittparty_Tms = ittparty_Tms;
		}

		public String getIttparty_Jrnl_No() {
			return Ittparty_Jrnl_No;
		}

		public void setIttparty_Jrnl_No(String ittparty_Jrnl_No) {
			Ittparty_Jrnl_No = ittparty_Jrnl_No;
		}

		public String getMkt_Id() {
			return Mkt_Id;
		}

		public void setMkt_Id(String mkt_Id) {
			Mkt_Id = mkt_Id;
		}

		public String getPy_Trn_No() {
			return Py_Trn_No;
		}

		public void setPy_Trn_No(String py_Trn_No) {
			Py_Trn_No = py_Trn_No;
		}

		public BigDecimal getRfnd_Amt() {
			return Rfnd_Amt;
		}

		public void setRfnd_Amt(BigDecimal rfnd_Amt) {
			Rfnd_Amt = rfnd_Amt;
		}

		public List<PartRfndDoSubDo> getSub_Ordr_List() {
			return Sub_Ordr_List;
		}

		public void setSub_Ordr_List(List<PartRfndDoSubDo> sub_Ordr_List) {
			Sub_Ordr_List = sub_Ordr_List;
		}

		public String getVno() {
			return Vno;
		}

		public void setVno(String vno) {
			Vno = vno;
		}

		public String getSign_Inf() {
			return Sign_Inf;
		}

		public void setSign_Inf(String sign_Inf) {
			Sign_Inf = sign_Inf;
		}
	}

	/**
	 * 子订单Do
	 */
	private static class PartRfndDoSubDo{
		private String Sub_Ordr_Id;
		private BigDecimal Rfnd_Amt;
		private List<PartRfndDoSubDo2> Prj_List;
		private List<PartRfndDoSubDo3> Parlist;
		private List<PartRfndDoSubDo4> Cpnlist;

		public List<PartRfndDoSubDo4> getCpnlist() {
			return Cpnlist;
		}

		public void setCpnlist(List<PartRfndDoSubDo4> cpnlist) {
			Cpnlist = cpnlist;
		}

		public List<PartRfndDoSubDo3> getParlist() {
			return Parlist;
		}

		public void setParlist(List<PartRfndDoSubDo3> parlist) {
			Parlist = parlist;
		}

		public String getSub_Ordr_Id() {
			return Sub_Ordr_Id;
		}

		public void setSub_Ordr_Id(String sub_Ordr_Id) {
			Sub_Ordr_Id = sub_Ordr_Id;
		}

		public BigDecimal getRfnd_Amt() {
			return Rfnd_Amt;
		}

		public void setRfnd_Amt(BigDecimal rfnd_Amt) {
			Rfnd_Amt = rfnd_Amt;
		}

		public List<PartRfndDoSubDo2> getPrj_List() {
			return Prj_List;
		}

		public void setPrj_List(List<PartRfndDoSubDo2> prj_List) {
			Prj_List = prj_List;
		}
	}

	/**
	 * 附加项Do
	 */
	private static class PartRfndDoSubDo2{
		private String Prj_Id;
		private BigDecimal Rfnd_Amt;

		public String getPrj_Id() {
			return Prj_Id;
		}

		public void setPrj_Id(String prj_Id) {
			Prj_Id = prj_Id;
		}

		public BigDecimal getRfnd_Amt() {
			return Rfnd_Amt;
		}

		public void setRfnd_Amt(BigDecimal rfnd_Amt) {
			Rfnd_Amt = rfnd_Amt;
		}
	}

	/**
	 * 退款参与方Do
	 */
	private static class PartRfndDoSubDo3{
		private String Mkt_Mrch_Id;
		private BigDecimal Rfnd_Amt;

		public String getMkt_Mrch_Id() {
			return Mkt_Mrch_Id;
		}

		public void setMkt_Mrch_Id(String mkt_Mrch_Id) {
			Mkt_Mrch_Id = mkt_Mrch_Id;
		}

		public BigDecimal getRfnd_Amt() {
			return Rfnd_Amt;
		}

		public void setRfnd_Amt(BigDecimal rfnd_Amt) {
			Rfnd_Amt = rfnd_Amt;
		}
	}

	/**
	 * 退款消费券Do
	 */
	private static class PartRfndDoSubDo4{
		private String Cnsmp_Note_Ordr_Id;
		private BigDecimal Rfnd_Amt;

		public String getCnsmp_Note_Ordr_Id() {
			return Cnsmp_Note_Ordr_Id;
		}

		public void setCnsmp_Note_Ordr_Id(String cnsmp_Note_Ordr_Id) {
			Cnsmp_Note_Ordr_Id = cnsmp_Note_Ordr_Id;
		}

		public BigDecimal getRfnd_Amt() {
			return Rfnd_Amt;
		}

		public void setRfnd_Amt(BigDecimal rfnd_Amt) {
			Rfnd_Amt = rfnd_Amt;
		}
	}
}

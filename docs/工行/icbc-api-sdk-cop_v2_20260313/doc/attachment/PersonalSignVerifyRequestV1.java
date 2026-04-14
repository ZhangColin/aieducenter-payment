package com.icbc.api.request;

import com.icbc.api.AbstractIcbcRequest;
import com.icbc.api.BizContent;
import com.icbc.api.IcbcResponse;
import com.icbc.api.internal.util.fastjson.annotation.JSONField;

public class PersonalSignVerifyRequestV1 extends AbstractIcbcRequest<IcbcResponse> {

	public static class PersonalSignVerifyRequestV1Biz implements BizContent {

		// 证件号
		@JSONField(name = "cert_no")
		private String certNo;
		// 证件类型
		@JSONField(name = "cert_type")
		private String certType;

		public String getCertNo() {
			return certNo;
		}

		public void setCertNo(String certNo) {
			this.certNo = certNo;
		}
		
		public String getCertType() {
			return certType;
		}

		public void setCertType(String certType) {
			this.certType = certType;
		}

	}

	@Override
	public Class<IcbcResponse> getResponseClass() {
		return null;
	}

	@Override
	public boolean isNeedEncrypt() {
		return false;
	}

	@Override
	public String getMethod() {
		return "POST";
	}

	@Override
	public Class<? extends BizContent> getBizContentClass() {
		return PersonalSignVerifyRequestV1Biz.class;
	}

}

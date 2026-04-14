package com.icbc.api.request;

import com.icbc.api.AbstractIcbcRequest;
import com.icbc.api.BizContent;
import com.icbc.api.internal.util.fastjson.annotation.JSONField;
import com.icbc.api.response.PreciousmetalGoldPriceQueryResponseV1;

public class PreciousmetalGoldPriceQueryRequestV1 extends AbstractIcbcRequest<PreciousmetalGoldPriceQueryResponseV1> {

	@Override
	public Class<PreciousmetalGoldPriceQueryResponseV1> getResponseClass() {
		return PreciousmetalGoldPriceQueryResponseV1.class;
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
		return PreciousmetalGoldPriceQueryRequestV1Biz.class;
	}

	public static class PreciousmetalGoldPriceQueryRequestV1Biz implements BizContent {

		// 报价请求时间戳
		@JSONField(name = "quote_price_req_ts")
		private String quotePriceReqTs;

		public String getQuotePriceReqTs() {
			return quotePriceReqTs;
		}

		public void setQuotePriceReqTs(String quotePriceReqTs) {
			this.quotePriceReqTs = quotePriceReqTs;
		}

	}

}

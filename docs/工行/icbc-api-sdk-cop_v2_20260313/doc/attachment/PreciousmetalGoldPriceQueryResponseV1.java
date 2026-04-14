package com.icbc.api.response;

import com.icbc.api.IcbcResponse;
import com.icbc.api.internal.util.fastjson.annotation.JSONField;

public class PreciousmetalGoldPriceQueryResponseV1 extends IcbcResponse {

	// 报价日期
	@JSONField(name = "quote_price_date")
	private String quotePriceDate;
	// 报价时间
	@JSONField(name = "quote_price_time")
	private String quotePriceTime;
	// 积存价格
	@JSONField(name = "purchase_price")
	private long purchasePrice;

	public String getQuotePriceDate() {
		return quotePriceDate;
	}

	public void setQuotePriceDate(String quotePriceDate) {
		this.quotePriceDate = quotePriceDate;
	}

	public String getQuotePriceTime() {
		return quotePriceTime;
	}

	public void setQuotePriceTime(String quotePriceTime) {
		this.quotePriceTime = quotePriceTime;
	}

	public long getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(long purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

}

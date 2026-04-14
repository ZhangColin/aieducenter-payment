/** ******************************
 * Copyright (c) 2017 ICBC.
 * All rights reserved.
 * Created on 2017年8月23日 下午1:51:36
 *
 * Contributors:
 *     kfzx-chenhj01 - initial implementation
 * ******************************/
package com.icbc.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.icbc.stars.business.module.Exposable;
import com.icbc.testng.utils.AbstractTestNgDubboCase;

/**
 * TODO:交易名
 * @author kfzx-chenhj01
 * @since 2017年8月23日
 * @last 2017年8月23日
 *富士康：II类户合作方介质绑定服务
 */
public class AccountBoundATSTest extends AbstractTestNgDubboCase {

	public void testAccountBoundATS() {
		HashMap<String, Object> mapInput = new HashMap<String, Object>();
		mapInput.put("corp_no", "000000000148");
		mapInput.put("corp_serno", "1236");
		mapInput.put("out_service_code", "bound");
		mapInput.put("corp_medium_id","11011119840503082X");
		mapInput.put("medium_id","6214760200000602406");
		mapInput.put("cis_no", "123456789012345");

    	Exposable op = (Exposable) SpringContainer.getContext().getBean("AccountBoundATS");
		Map<String, ?> or = op.execute(mapInput);
		System.out.println(or.toString());
    	Assert.assertEquals(or.get("return_code"), 0);
	}
}

/** ******************************
 * Copyright (c) 2017 ICBC.
 * All rights reserved.
 * Created on 2017年8月23日 下午1:50:59
 *
 * Contributors:
 *     kfzx-chenhj01 - initial implementation
 * ******************************/
package com.icbc.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.icbc.stars.business.module.Exposable;
import com.icbc.testng.utils.AbstractTestNgDubboCase;

/**
 * TODO:交易名
 * @author kfzx-chenhj01
 * @since 2017年8月23日
 * @last 2017年8月23日
 *富士康， 绑定II类户查询服务
 */
public class AccountStatusQueryATSTest extends AbstractTestNgDubboCase {

	public void testAccountStatusQueryATS() {
		HashMap<String, Object> mapInput = new HashMap<String, Object>();
		mapInput.put("corp_no", "000000000148"); //"000000000168");
		mapInput.put("corp_serno", "1236");
		mapInput.put("out_service_code", "querystatus");//"querystatus");
		mapInput.put("corp_medium_id", "987654200011110021");
		//210104198506020137
		
    	Exposable op = (Exposable) SpringContainer.getContext().getBean("AccountStatusQueryATS");
		Map<String, ?> or = op.execute(mapInput);
		System.out.println(or.toString());
    	Assert.assertEquals(or.get("return_code"), 0);
	}
}

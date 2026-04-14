package com.icbc.api;

import javax.servlet.http.HttpServlet;

import com.icbc.api.request.ProjectQueryRequestV1;
import com.icbc.api.request.ProjectQueryRequestV1.ProjectQueryRequestV1Biz;
import com.icbc.api.response.ProjectQueryResponseV1;



public class PaymentRefundDetailQueryServiceTest extends HttpServlet {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1588786734333856129L;
	
	
	public static void main(String[] args) {

		String appId ="10000000000000004467";
		String MY_PRIVATE_KEY ="-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCeJ2W+0ecaP/7zYdCKz8ma23ekN5KOFrL7f88PUe9J6vfPyUmgIMjhJhANcvmYcahwY1GKsRMhaCe/eZzyj2yTxIzLrJ8YgrvIyEoL4odTxGM5uvu4vs0oLmErRnzee8jPJldCQU8ukoVNO8KEAqNBy/e4D8KYo9DmM3Rx7YNIiu90XXh4pfumXVp5v9MbF3aqpOS7qH1hSz90yMdhQjFdBcR9hXlT8HUARPdvDTQXcQ2eM8ZcRAhe7qiIVGmeD9vk1SEDQX1vnrYwdhUwYYltdgsXAqvnpeNTLP9+Rc1HJQoST4XXi8BMZGP6yp/clCdD+fObpHin/1pNJnqVxrC3AgMBAAECggEAKeXiDIVrJgciMbQOHVgi3sWZkoWua7oTvJEFd9eK/xgigoee268TyKHeytFU0k/6AZwuNAHpQYZoUU+UhqX8a8E8U5DtrP6lZPG6vB9NHqH+b4LbwPu7OhPfZimMVxyP/GD56PGIWpwyeF2GkJZqTboNbFOwh+OOKg4joTL8/rB0SGLhkjJgRTYhzs6UYrO6yRY+Tl+zbHTjRhC8mkd4P0UoJJogzlXhZUq5zJLp9xPZyreQnzUY04uojN9T9mS5znyzHQg98FrVCFao7Akf0XFJta+wA9+yPVC3QJIl9wVvqIglBoyKkBP0BSXiSifI6peXdnQax4NXMiqK0mkkMQKBgQDnljAjRRkMKVZRLN3MImdVlERcsbJYZ0pLFCxrkbqz14fsj//Ydxxwi7sGzbx1vKMRy8kvy1wVF6P8Lp593WhjPfLwynCfuUQUda+2cDuRwW2Q1yMvyaXR20OmeI71nfW5FKJvKZMrvmOx0a9OYqovXX0XP6cf88hKSnLgT/PnzQKBgQCu03wSJ8jwrI8WM/Va2cf257WPIwcTEvKJwhL00VO6aCDuG40oLxgkfkTFQtlzJ/eUsCiosNj4E2mEnrfiaI7u1CSLypfKCK2JS3w+cvkdpXhYLK8gSvJiIyThCg7cet2IDHQP4v/3P2AnEipHH/CWdfoh1hzh3bwcDLPS8BjukwKBgQDPUia6DGvF9WOuvAm7a7kyZc9lZDcS6TUbTW4OxqFryrGGV8tk0A0HvC+sgZYLmNorXlI3lUhUIZvOcTW12/ZNkfHhGnoEo2iNIGYzXvoVHCKoHdEEQPNPkYlYiGkC/WwzSoO4HHICISlC0UKtW6qbSIhpvbQx6J4P0+JPaYQiqQKBgQCmYWkL0kc/HcqTY+GWk/rltUEqNzjNY1Pl3Qiza+BWYDEuypO9cYx1dqI2ZdeS66+JF1mqqZvkBH2AlJkGZWHaU5VwYN+yjC2zo15cBImnOEpfCC+m+QzSbwXzEeJw3JGmn8t5Lx1RjkQRCw2nYPNUDnV97ErSfnc+7qvZDV189wKBgCDzLOzliIh6nvIJDrTquFizfVNZQ9U56tlNHxsnm/JfACdcJJF4k3LTdQ2ZN5f6FwyFgKSiBuHJFH/cXyi9pWPibuqX36EqFx/i8bti46zc327sJYQ+BSxBa4sUBg0Ii5/ejYpfFi85r/QsYdkw9jfRN12mINyE++nKqi7exT9q";

		String APIGW_PUBLIC_KEY ="-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16"+
		"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL"+
		"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP"+
		"3CiNgg0W6O3AGqwIDAQAB";
		
		
		DefaultIcbcClient client = new DefaultIcbcClient(appId, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		ProjectQueryRequestV1 request = new ProjectQueryRequestV1();		
		request.setServiceUrl("http://ip:port/api/project/query/V1");
		
		
		ProjectQueryRequestV1Biz bizContent = new ProjectQueryRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setEpmAppId("10000000000000004506");
		bizContent.setCurPage("0");
		bizContent.setFetchNum("30");
		
		
		
		ProjectQueryResponseV1 response;
		try {
			response = client.execute(request, "msgId128234859879");
			//
			System.out.println(response.getReturnCode());
			System.out.println(response.getReturnMsg());
			System.out.println(response.getMsgId());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

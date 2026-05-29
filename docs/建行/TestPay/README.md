## 配置
### 测试环境需要在hosts文件中进行配置，配置如下
hosts文件位置：C:\Windows\System32\drivers\etc
添加：103.126.126.119         marketpaykone.dev.jh

## 接口简介
### 生成支付订单接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/gatherPlaceorder
生产环境：https://marketpay.ccb.com/online/direct/gatherPlaceorder
#### 对应测试类：
1. 单一订单（gatherByPlaceorder）
2. 合并订单（gatherByMergePlaceorder）
3. 在途下单（gatherByOneTtheWayPayorder）
4. 购买消费券（gatherByPurchaseCnporder）

### 生成无收银台支付信息接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/mergePayUrl
生产环境：https://marketpay.ccb.com/online/direct/mergePayUrl
#### 对应测试类：
1. 生成无收银台支付信息（gatherBymergePayUrl）

### 支付结果通知接口
#### 对应URL:
测试环境：市场方URL
生产环境：市场方URL
#### 对应测试类：
1. 支付结果通知接口（gatherByTongzhi）

### 查询支付结果接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/gatherEnquireOrder
生产环境：https://marketpay.ccb.com/online/direct/gatherEnquireOrder
#### 对应测试类：
1. 查询支付结果接口（gatherByEnquireOrder）

### 更新线下支付订单状态接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/updateOrderSt
生产环境：https://marketpay.ccb.com/online/direct/updateOrderSt
#### 对应测试类：
1. 更新线下支付订单状态接口（gatherByupdateOrderSt）

### 附加项信息确认接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/orderAddConfirm
生产环境：https://marketpay.ccb.com/online/direct/orderAddConfirm
#### 对应测试类：
1. 附加项信息确认接口（gatherByAddConfirm）

### 退款下单接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/refundOrder
生产环境：https://marketpay.ccb.com/online/direct/refundOrder
#### 对应测试类：
1. 退款下单接口（RfndOrder）

### 退款结果通知接口
#### 对应URL:
测试环境：市场方URL
生产环境：市场方URL
#### 对应测试类：
1. 退款结果通知接口（NoticeMktRefundRst）

### 查询退款结果接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/enquireRefundOrder
生产环境：https://marketpay.ccb.com/online/direct/enquireRefundOrder
#### 对应测试类：
1. 查询退款结果接口（TestMrgRefund）

### 在途信息确认接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/confirmOnTheWay
生产环境：https://marketpay.ccb.com/online/direct/confirmOnTheWay
#### 对应测试类：
1. 在途信息确认接口（gatherByconfirmOnTheWay）

### 确认收货接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/mergeNoticeArrival
生产环境：https://marketpay.ccb.com/online/direct/mergeNoticeArrival
#### 对应测试类：
1. 确认收货接口（gatherBymergeNoticeArrival）

### 对账单推送接口
#### 对应URL:
测试环境：市场方URL
生产环境：市场方URL
#### 对应测试类：
1. 对账单推送接口（gatherByFileUpLoadService）

### 分账规则查询接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/accountingRulesList
生产环境：https://marketpay.ccb.com/online/direct/accountingRulesList
#### 对应测试类：
1. 分账规则查询接口（gatherByaccountingRulesList）

### 分账规则推送接口
#### 对应URL:
测试环境：市场方URL
生产环境：市场方URL
#### 对应测试类：
1. 分账规则推送接口（gatherByClrgRuleId）

### 商家信息通知接口
#### 对应URL:
测试环境：市场方URL
生产环境：市场方URL
#### 对应测试类：
1. 商家信息通知接口（gatherByMktMrchNmtongzhi）

### 商家信息查询接口
#### 对应URL:
测试环境：http://marketpaykone.dev.jh:8028/online/direct/enquireMkMrchOrder
生产环境：https://marketpay.ccb.com/online/direct/enquireMkMrchOrder
#### 对应测试类：
1. 商家信息查询接口（gatherByenquireMkMrchOrder）

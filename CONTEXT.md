# aieducenter-payment

平台**支付域**服务（基础能力层）——为业务应用提供支付下单、预下单、退款等机机接口，并对入站调用做机机签名校验。本文件是本服务的**术语表 + 设计不变式**，不含实现细节。

> 架构权威在兄弟仓库 [`../aieducenter-architecture/`](../aieducenter-architecture/)。机机验签凭据的登记与颁发归兄弟仓库 [`../aieducenter-app-registry/`](../aieducenter-app-registry/)（平台「应用/消费方」单一登记处，签名凭据的唯一源）。本文件是二者在 payment 服务内的落地折射。

## 稳定不变式（务必遵守）

1. **payment 是 openapi provider（非 caller）**：校验入站请求的机机签名（HMAC-SHA256），放行可信业务系统的调用；自身**不做任何出站签名调用**，故不持有、也不需要自有的出站签名凭据。
2. **app-registry 是签名凭据的唯一源**：payment 不自建凭据表——入站签名校验所需的 `apiKey` / `apiSecret` / `appName` 全部向 app-registry 查询取得。凭据的登记、颁发、轮换、启停都只在 app-registry 一处发生。
3. **bootstrap 端点内网-only**：payment 取凭据所依赖的 app-registry bootstrap 端点**按设计不验签**，其安全完全依赖**网络隔离**——生产部署须保证该端点仅在内网可达，明文 `apiSecret` 永不暴露到公网（依据 app-registry ADR-0002）。
4. **`businessSystemName` 源自调用方 `appName`，无数据迁移**：支付订单与退款订单上的业务系统归属（`businessSystemName`）即验签后由框架注入的调用方显示名（`appName`）。该语义在本次凭据源迁移中保持不变——既不回填、也不重算历史订单。

## 术语表（Ubiquitous Language）

_以下为支付域 + 机机签名接入相关、已敲定的术语。重载术语在代码与沟通中须无歧义地按此使用。_

**appCode**:
应用在 app-registry 登记时的**稳定公开 slug**——创建时填写、创建后**不可修改**、不轮换。是跨域引用、归属、离线聚合用的应用级稳定身份。
_Avoid_: 把 `appCode` 与应用内部主键、或与凭据密钥混为一谈。

**apiKey**:
机机签名的**凭证标识**，**在值上等于** `appCode`（创建后不可变），随每个请求以 `X-Api-Key` 头部发送。是机机签名框架验签时定位凭据所用的键。
_Avoid_: 以为 `apiKey` 是与 `appCode` 不同的、另一份可独立轮换的字符串——在当前契约下二者同值；真正可轮换的是 `apiSecret`，不是 `apiKey`。

**apiSecret**:
机机签名的**凭证密钥**，与 `apiKey` 成对使用做 HMAC-SHA256。在 app-registry 中以 AES-GCM 加密存储，仅经内网 bootstrap 端点**明文返回**给可信 provider 服务（如 payment）用于验签。**可轮换**——轮换只换 secret，不动 `apiKey` / `appCode`。
_Avoid_: 把 `apiSecret` 当作可随意取回的普通明文配置、常暴露在公网链路上。

**appName**:
应用在 app-registry 登记时的**显示名**（`RegisteredApp.name`）——人可读、**可修改**。随 `apiKey` 一并由 bootstrap 端点返回，是订单归属所用的业务系统身份。
_Avoid_: 把 `appName`（可改的显示名）与 `appCode` / `apiKey`（不可变的稳定标识 / 凭证标识）混用。

**callerAppName**:
payment 处理一个入站请求时，「**调用方应用显示名**」的运行时取值——由机机签名框架验签通过后、按调用方凭据注入请求上下文。payment 取它作为下文 `businessSystemName` 的来源。
_Avoid_: 与 `appCode` / `apiKey` 混淆——`callerAppName` 是显示名（可改），不是稳定标识。

**businessSystemName**:
持久化在支付订单与退款订单上的**业务系统归属**——值即本次调用的 `callerAppName`（亦即调用方的 `appName`）。用于把订单归到发起它的业务系统。
_Avoid_: 以为它是独立于 app-registry 的另一套业务系统主数据，或以为本次迁移需要回填 / 重算——它只是 `appName` 的落库投影，语义未变。

_项目级新术语随讨论沉淀、追加于后。重大决策同步落 `docs/adr/`。_

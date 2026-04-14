# Apifox 签名验证前置脚本

## 配置步骤

### 1. 设置环境变量

在 Apifox 环境中添加：

| 变量名 | 说明 | 示例 |
|--------|------|------|
| `baseUrl` | 服务地址 | `http://localhost:8080` |
| `appId` | API Key（创建 ApiKey 时生成） | `A1B2C3D4E5F6...` |
| `appSecret` | API Secret（创建 ApiKey 时生成） | `abc123...` |

### 2. 添加前置脚本

在接口集合（Folder）→ 前置脚本 → 粘贴以下代码。集合内所有接口自动签名，无需逐个配置。

```javascript
// ========================================================
// Apifox 前置脚本 — HMAC-SHA256 签名自动计算 (v3)
// ========================================================
// 环境变量: appId, appSecret, baseUrl
// ========================================================

const appId = pm.environment.get("appId");
const appSecret = pm.environment.get("appSecret");

if (!appId || !appSecret) {
    throw new Error("请先在环境变量中设置 appId 和 appSecret");
}

const CryptoJS = require("crypto-js");

// ---- Step 1: 计算 bodyDigest ----
// POST: SHA-256(body 原文字节)
// GET:  SHA-256("") 空字符串
let body = "";
const reqBody = pm.request.body;
if (reqBody && reqBody.mode === "raw" && reqBody.raw && reqBody.raw.trim() !== "") {
    body = reqBody.raw;
}
const bodyDigest = CryptoJS.SHA256(body).toString(CryptoJS.enc.Hex);

// ---- Step 2: 系统参数 ----
const timestamp = Math.floor(Date.now() / 1000).toString();
const nonce = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function(c) {
    const r = (Math.random() * 16) | 0;
    return (c === "x" ? r : (r & 0x3 | 0x8)).toString(16);
});

// ---- Step 3: 收集 URL 查询参数 ----
const queryParams = {};
const urlStr = pm.request.url.toString();
const queryIdx = urlStr.indexOf("?");
if (queryIdx > -1) {
    urlStr.substring(queryIdx + 1).split("&").forEach(function(pair) {
        const eqIdx = pair.indexOf("=");
        if (eqIdx > -1) {
            const key = decodeURIComponent(pair.substring(0, eqIdx));
            queryParams[key] = decodeURIComponent(pair.substring(eqIdx + 1));
        }
    });
}

// ---- Step 4: 按 key 字典序排列，拼接为 key=value&... ----
const allParams = Object.assign({
    appId: appId,
    bodyDigest: bodyDigest,
    nonce: nonce,
    timestamp: timestamp
}, queryParams);

const stringToSign = Object.keys(allParams).sort().map(function(key) {
    return key + "=" + allParams[key];
}).join("&");

// ---- Step 5: 计算 HMAC-SHA256 签名 ----
const sign = CryptoJS.HmacSHA256(stringToSign, appSecret).toString(CryptoJS.enc.Hex);

// ---- Step 6: 注入 Headers ----
pm.request.headers.upsert({ key: "X-App-Id", value: appId });
pm.request.headers.upsert({ key: "X-Timestamp", value: timestamp });
pm.request.headers.upsert({ key: "X-Nonce", value: nonce });
pm.request.headers.upsert({ key: "X-Sign", value: sign });
pm.request.headers.upsert({ key: "X-Body-Digest", value: bodyDigest });

console.log("===== 签名计算完成 =====");
console.log("body:", body || "(无body, GET请求)");
console.log("bodyDigest:", bodyDigest);
console.log("timestamp:", timestamp);
console.log("nonce:", nonce);
console.log("queryParams:", JSON.stringify(queryParams));
console.log("stringToSign:", stringToSign);
console.log("sign:", sign);
```

## 签名规则说明

### 签名计算流程

```
Step 1: 计算 bodyDigest
  POST: bodyDigest = hex(SHA-256(requestBody))
  GET:  bodyDigest = hex(SHA-256(""))

Step 2: 收集参与签名的参数
  - 系统参数: appId, bodyDigest, nonce, timestamp
  - 查询参数: URL 中所有 ?key=value

Step 3: 按 key 字典序排列，拼接为 key1=value1&key2=value2&...

Step 4: 计算签名
  sign = hex(HMAC-SHA256(拼接字符串, appSecret))
```

### HTTP Headers

| Header | 说明 | 由脚本自动设置 |
|--------|------|:-:|
| `X-App-Id` | API Key（即 appId） | 是 |
| `X-Timestamp` | 当前时间戳（秒级） | 是 |
| `X-Nonce` | UUID 随机串 | 是 |
| `X-Sign` | HMAC-SHA256 签名结果 | 是 |
| `X-Body-Digest` | Body SHA-256 摘要 | 是 |

## 测试接口

先用以下测试接口验证签名机制正确性：

| 方法 | URL | Body | 说明 |
|------|-----|------|------|
| POST | `{{baseUrl}}/api/v1/test/server/create-order` | `{"businessOrderNo":"TEST001","amount":100,"subject":"测试"}` | POST JSON |
| GET | `{{baseUrl}}/api/v1/test/server/ping` | 无 | GET 无参数 |
| GET | `{{baseUrl}}/api/v1/test/server/search?keyword=Python&status=PENDING&page=1` | 无 | GET 带查询参数 |
| POST | `{{baseUrl}}/api/v1/test/server/transfer?channel=BANK&operator=admin` | `{"orderNo":"TF001","amount":50000}` | POST + 查询参数 |

验证通过后，将 URL 替换为正式的 payment/refund 接口即可。

## 正式接口

| 方法 | URL | 说明 |
|------|-----|------|
| POST | `{{baseUrl}}/api/v1/payments` | 创建支付订单 |
| GET | `{{baseUrl}}/api/v1/payments/{paymentOrderNo}` | 查询支付订单 |
| POST | `{{baseUrl}}/api/v1/payments/{paymentOrderNo}/query` | 主动查询支付状态 |
| POST | `{{baseUrl}}/api/v1/refunds` | 创建退款订单 |
| POST | `{{baseUrl}}/api/v1/refunds/{refundOrderNo}/audit` | 审核退款 |
| GET | `{{baseUrl}}/api/v1/refunds/{refundOrderNo}` | 查询退款订单 |

## 注意事项

1. **bodyDigest 与 JSON 格式**：bodyDigest 是对 HTTP 传输的原始字节做 SHA-256，Apifox 中 Body 编辑器的内容就是实际传输的内容，确保服务端收到的是同样的字节即可
2. **时间窗口**：默认 5 分钟容忍窗口（300 秒），客户端与服务端时钟需大致同步
3. **appSecret 安全**：appSecret 仅在客户端本地使用，绝不在网络中传输
4. **控制台日志**：Apifox 控制台会打印完整的签名计算过程，便于排查问题

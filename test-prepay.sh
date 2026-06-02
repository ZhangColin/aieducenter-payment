#!/bin/bash
# 预支付接口测试脚本
# 用法: ./test-prepay.sh

set -e

BASE_URL="http://localhost:8081"
API_PATH="/api/v1/payments/prepay"

# 调用方的 appId 和 appSecret（需要从 aieducenter 管理后台获取）
CALLER_APP_ID="${CALLER_APP_ID:-你的调用方AppId}"
CALLER_APP_SECRET="${CALLER_APP_SECRET:-你的调用方AppSecret}"

# 请求体
BODY='{
  "businessOrderNo": "TEST-PREPAY-'$(date +%Y%m%d%H%M%S)'",
  "amount": 1,
  "subject": "测试预支付",
  "body": "测试聚合支付预支付接口",
  "businessName": "测试业务",
  "notifyUrl": "https://example.com/notify",
  "expiredSeconds": 3600,
  "payMode": 9,
  "accessType": 7,
  "openId": "test_open_id_123"
}'

echo "=== 请求参数 ==="
echo "$BODY" | python3 -m json.tool 2>/dev/null || echo "$BODY"
echo ""

# 生成签名所需的参数
TIMESTAMP=$(date +%s%3N)  # 毫秒时间戳
NONCE=$(python3 -c "import secrets; print(secrets.token_hex(16))")

# 计算 Body Digest (SHA-256 -> Base64)
BODY_DIGEST=$(echo -n "$BODY" | shasum -a 256 | awk '{print $1}')

# 构建 StringToSign: POST\n{path}\n{timestamp}\n{nonce}\n{bodyDigest}
STRING_TO_SIGN="POST\n${API_PATH}\n${TIMESTAMP}\n${NONCE}\n${BODY_DIGEST}"

# 计算 HMAC-SHA256 签名
SIGNATURE=$(echo -n "$STRING_TO_SIGN" | openssl dgst -sha256 -hmac "$CALLER_APP_SECRET" -binary | base64)

echo "=== 签名信息 ==="
echo "Timestamp: $TIMESTAMP"
echo "Nonce: $NONCE"
echo "Body Digest: $BODY_DIGEST"
echo "StringToSign: $STRING_TO_SIGN"
echo "Signature: $SIGNATURE"
echo ""

echo "=== 发送请求 ==="
curl -s -X POST "${BASE_URL}${API_PATH}" \
  -H "Content-Type: application/json" \
  -H "X-App-Id: ${CALLER_APP_ID}" \
  -H "X-Timestamp: ${TIMESTAMP}" \
  -H "X-Nonce: ${NONCE}" \
  -H "X-Body-Digest: ${BODY_DIGEST}" \
  -H "X-Sign: ${SIGNATURE}" \
  -d "$BODY" | python3 -m json.tool

echo ""
echo "=== 完成 ==="
echo "如果成功，响应中的 prepayDataPackage 就是前端调起微信支付所需的参数"
echo "前端用这个参数调用 wx.requestPayment() 即可唤起支付"

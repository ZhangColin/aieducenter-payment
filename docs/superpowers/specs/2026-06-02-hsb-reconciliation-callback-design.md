# HSB 对账文件接收接口设计

## 背景

建行惠市宝每天早上 8 点跑批后，通过 HTTP Multipart POST 向我们推送对账文件（zip 压缩包）。我们需要提供一个接收接口，验签后保存文件。

## 需求

- 接收建行惠市宝推送的对账 zip 文件
- 验签通过后保存到本地文件系统
- 不解压、不验证 zip 内部内容
- 不存数据库，仅文件存储 + 日志

## 接口协议

### 请求

- **Method**: POST (Multipart/form-data)
- **Path**: 由建行配置（我们提供 `/api/v1/hsb/callback/reconciliation`）
- **参数**:
  - `File_Smry_Inf` (String, 必输): 分账汇总文件摘要信息
  - `Sign_Inf` (String, 必输): RSA 签名，签名原文为 `File_Smry_Inf={value}`
  - File (MultipartFile): zip 压缩包

### 响应

```json
{"Svc_Rsp_St": "00"}
```

- `Svc_Rsp_St=00`: 成功
- `Svc_Rsp_St=01`: 失败

## 设计

### 流程

```
建行 POST Multipart → Controller → AppService
                                      1. 验签 File_Smry_Inf + Sign_Inf
                                      2. 创建日期目录 {basePath}/YYYY-MM-dd/
                                      3. 保存 zip 文件为 {timestamp}.zip
                                      4. 日志记录
                                      5. 返回 Svc_Rsp_St=00
```

### 改动清单

| 文件 | 改动 |
|---|---|
| `HsbCallbackController` | 新增 `POST /reconciliation` 端点，接收 `@RequestParam File_Smry_Inf`, `@RequestParam Sign_Inf`, `@RequestPart MultipartFile` |
| `HsbCallbackAppService` | 新增 `handleReconciliationCallback(String fileSmryInf, String signInf, MultipartFile file)` 方法 |
| `HsbConfig` | 新增 `reconciliationStoragePath` 字段，默认 `data/reconciliation` |
| `application-local.yml` | 添加 `hsb.reconciliation-storage-path` |
| `application-test.yml` | 添加 `hsb.reconciliation-storage-path` |
| `application-prod.yml` | 添加 `hsb.reconciliation-storage-path` |

### 不需要新增

- 无新 Domain 实体
- 无新 Repository
- 无数据库表
- 无新的 DTO 类

### 文件存储

- 路径格式: `{reconciliationStoragePath}/YYYY-MM-dd/{millis}.zip`
- Docker 部署时需将 `reconciliationStoragePath` 映射为 volume
- 父目录不存在时自动创建

### 错误处理

- 验签失败: 返回 `Svc_Rsp_St=01`，日志记录 warn
- 文件保存失败: 返回 `Svc_Rsp_St=01`，日志记录 error
- 接收到的文件为空: 返回 `Svc_Rsp_St=01`

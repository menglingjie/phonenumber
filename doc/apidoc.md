# 电话号码解析 API 文档

## 概述

电话号码解析服务提供了解析和验证电话号码的功能。该服务可以识别电话号码的有效性、归属地、运营商等信息。

## API 端点

基础URL: `http://localhost:8080/api/phone`

### 1. GET /parse - 解析电话号码

通过GET请求解析电话号码信息。

#### 请求参数

| 参数名 | 类型 | 必需 | 默认值 | 描述 |
|-------|------|------|--------|------|
| number | string | 是 | - | 需要解析的电话号码 |
| region | string | 否 | "CN" | 电话号码的国家/地区代码 (如: CN, US) |

#### 响应字段

| 字段名 | 类型 | 描述 |
|--------|------|------|
| phoneNumber | string | 原始电话号码 |
| valid | boolean | 电话号码是否有效 |
| countryCode | string | 国家代码 |
| countryIsoCode | string | 国家ISO代码 |
| nationalNumber | string | 国内电话号码 |
| region | string | 地区信息 |
| carrier | string | 运营商信息 |
| numberType | string | 号码类型 |

#### 示例请求

```
GET /api/phone/parse?number=13800138000&region=CN
```

#### 示例响应

```json
{
  "phoneNumber": "13800138000",
  "valid": true,
  "countryCode": "86",
  "countryIsoCode": "CN",
  "nationalNumber": "13800138000",
  "region": "China",
  "carrier": "China Mobile",
  "numberType": "MOBILE"
}
```

### 2. POST /parse - 解析电话号码

通过POST请求解析电话号码信息。

#### 请求体

```json
{
  "number": "13800138000",
  "region": "CN"
}
```

| 字段名 | 类型 | 必需 | 默认值 | 描述 |
|--------|------|------|--------|------|
| number | string | 是 | - | 需要解析的电话号码 |
| region | string | 否 | "CN" | 电话号码的国家/地区代码 |

#### 响应字段

与GET方法相同。

#### 示例请求

```json
POST /api/phone/parse
Content-Type: application/json

{
  "number": "13800138000",
  "region": "CN"
}
```

#### 示例响应

```json
{
  "phoneNumber": "13800138000",
  "valid": true,
  "countryCode": "86",
  "countryIsoCode": "CN",
  "nationalNumber": "13800138000",
  "region": "China",
  "carrier": "China Mobile",
  "numberType": "MOBILE"
}
```

## 错误处理

当提供的电话号码无效时，API将返回以下格式的响应：

```json
{
  "phoneNumber": "invalid-number",
  "valid": false,
  "countryCode": null,
  "countryIsoCode": null,
  "nationalNumber": null,
  "region": null,
  "carrier": null,
  "numberType": null
}
```

## 使用示例

### curl 示例

```bash
# GET 请求
curl "http://localhost:8080/api/phone/parse?number=13800138000&region=CN"

# POST 请求
curl -X POST http://localhost:8080/api/phone/parse \
  -H "Content-Type: application/json" \
  -d '{"number":"13800138000","region":"CN"}'
```

### JavaScript 示例

```javascript
// GET 请求
fetch('http://localhost:8080/api/phone/parse?number=13800138000&region=CN')
  .then(response => response.json())
  .then(data => console.log(data));

// POST 请求
fetch('http://localhost:8080/api/phone/parse', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    number: '13800138000',
    region: 'CN'
  })
})
  .then(response => response.json())
  .then(data => console.log(data));
```
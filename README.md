# PhoneNumber 解析服务

PhoneNumber 是一个基于 Spring Boot 和 Google libphonenumber 库的手机号码解析服务。它可以验证电话号码、获取号码归属地、运营商等信息。

## 功能特性

- 验证电话号码的有效性
- 获取电话号码的国家代码
- 获取电话号码的国家/地区 ISO 代码
- 获取电话号码的国内格式
- 获取电话号码归属地信息
- 获取电话号码运营商信息
- 支持 GET 和 POST 请求方式

## 技术栈

- Java 21
- Spring Boot 3.5.6
- Spring WebFlux (响应式编程)
- Google libphonenumber
- Google geocoder
- Google carrier
- Lombok
- Maven

## API 接口

### GET 方式解析电话号码

```
GET /api/phone/parse?number={phoneNumber}&region={regionCode}
```

参数:
- `number` - 要解析的电话号码
- `region` - (可选) 地区代码，默认为 "CN"

### POST 方式解析电话号码

```
POST /api/phone/parse
```

请求体:
```json
{
  "number": "电话号码",
  "region": "地区代码(可选，默认CN)"
}
```

## 返回结果示例

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

## 构建和运行

1. 克隆项目
2. 使用 Maven 构建项目:
   ```
   ./mvnw clean package
   ```
3. 运行应用:
   ```
   ./mvnw spring-boot:run
   ```
   或者
   ```
   java -jar target/phonenumber-0.0.1-SNAPSHOT.jar
   ```

## 使用示例

### curl 示例

GET 请求:
```bash
curl "http://localhost:8080/api/phone/parse?number=%2B13800138000&region=CN"
```

POST 请求:
```bash
curl -X POST http://localhost:8080/api/phone/parse \
  -H "Content-Type: application/json" \
  -d '{"number":"+13800138000","region":"CN"}'
```

## 许可证

本项目采用 Apache License 2.0 许可证，详情请见 [LICENSE](LICENSE) 文件。
# JWT

> Json Web Token

## 인증/인가

1. 사용자 인증 요청(클라이언트 -> 서버)
    - 사용자의 로그인 자격 증명(아이디, 비밀번호)을 서버로 전송
2. 자격 증명 확인(서버)
    - 전달받은 자격 증명이 유효한지 확인
    - 유효한 경우, 3번 진행
3. JWT(이하 토큰) 발급(서버 -> 클라이언트)
    - 사용자 정보를 기반으로 토큰 발행(기타 정보 및 만료 시간등 포함, 서버의 비밀키로 서명)
    - 클라이언트에게 토큰을 전달
    - 클라이언트는 전달 받은 토큰을 저장(로컬 스토리지, 쿠키 등)
4. 인증/인가가 필요한 리소스 요청(클라이언트 -> 서버)
    - 클라이언트는 요청시 전달받은 토큰을 헤더(`Authorization: Bearer <token>`)에 포함하여 서버에 요청
5. 토큰 검증(서버)
    - 인증/인가가 필요한 리소스 요청인 경우, 토큰 유효성 검증
    - 토큰 변조 여부 확인 및 만료 시간 확인
6. 인가(서버)
    - 토큰이 유효한 경우, 토큰 생성시 포함한 정보를 기반으로 요청된 리소스에 대한 접근 권한이 있는지 확인
    - 권한이 있으면 요청 처리, 없다면 접근 거부

## 토큰 탈취에 대한 방안

1. HTTPS 사용
2. 쿠키에 저장시 `Secure`, `HttpOnly` 속성 사용
    - `Secure`: HTTPS 프로토콜을 사용하는 경우에만 쿠키 전송
    - `HttpOnly`: JavaScript 로 쿠키에 접근 불가
3. 짧은 만료 시간 설정
4. 토큰 무효화 메커니즘 구현 => ?
5. CORS 설정
6. XSS 방어 ?
    - CSP(Content Security Policy) 설정
    - 철저한 입력값 검증

## 응답

- 401 Unauthorized: 인증 실패(로그인 필요)
- 403 Forbidden: 인가 실패(권한 없음)

## JWT

### Header

- `alg`: 알고리즘
- `typ`: 토큰 타입
- `cty`: 토큰의 내용 타입

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

### Payload(Claims)

- registered claims
    - `iss`: 토큰 발급자
    - `sub`: 토큰 제목
    - `aud`: 토큰 대상자
    - `exp`: 토큰 만료 시간
    - `nbf`: 토큰 활성 시간
    - `iat`: 토큰 발급 시간
    - `jti`: JWT ID
- public claims
    - `{uri}`: URI 형식의 키
- private claims
    - `custom`: 사용자 정의 정보

```json
{
  "iss": "https://example.com",
  "sub": "user",
  "aud": "https://example.com",
  "exp": 1609459200,
  "nbf": 1609459200,
  "iat": 1609459200,
  "jti": "1234567890",
  "custom": "custom"
}
```

### Signature

...

### References

- [JWT](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1)
- [JWT.io](https://jwt.io/)
- [The Complete Guide to JSON Web Tokens (JWT) and Token Based Authentication](https://dekh.medium.com/the-complete-guide-to-json-web-tokens-jwt-and-token-based-authentication-32501cb5125c)

org.springframework.security.web.session.DisableEncodeUrlFilter@27db45f,
org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@6ec3d8e4,
org.springframework.security.web.context.SecurityContextPersistenceFilter@343db2f6,
org.springframework.security.web.header.HeaderWriterFilter@61dd1c3d,

org.springframework.security.web.authentication.logout.LogoutFilter@18d610e1,

org.springframework.security.web.savedrequest.RequestCacheAwareFilter@769b0752,
org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@517fbf62,
org.springframework.security.web.authentication.AnonymousAuthenticationFilter@1aeff8ca,
org.springframework.security.web.session.SessionManagementFilter@425b5fe2,
org.springframework.security.web.access.ExceptionTranslationFilter@48b2dbc4, => BadCredentialsException
org.springframework.security.web.access.intercept.AuthorizationFilter@38ef1a0a

```json
{
  "data": [
    {
      "codes": [
        "NotBlank.userJoinCommand.password",
        "NotBlank.password",
        "NotBlank.java.lang.String",
        "NotBlank"
      ],
      "arguments": [
        {
          "codes": [
            "userJoinCommand.password",
            "password"
          ],
          "arguments": null,
          "defaultMessage": "password",
          "code": "password"
        }
      ],
      "defaultMessage": "must not be blank",
      "objectName": "userJoinCommand",
      "field": "password",
      "rejectedValue": "",
      "bindingFailure": false,
      "code": "NotBlank"
    }
  ]
}
```

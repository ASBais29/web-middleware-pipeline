# WebMiddlewarePipeline 🚀

A **Spring Boot middleware pipeline** implementing:

A Spring Boot middleware pipeline implementing:

- ✅ JWT Authentication  
- ✅ Redis-backed Rate Limiting  
- ✅ Request Logging  
- ✅ Prometheus Metrics  

designed for **clean, scalable request handling in Java microservices**.

---

## Features

- **📝 Request Logging**: Logs HTTP method and path for observability and debugging.
- **🔐 JWT Authentication**: Validates Bearer tokens, rejecting invalid or missing tokens early.
- **🚦 Redis Rate Limiting**: Enforces per-IP rate limiting using Redis for distributed readiness.
- **📊 Prometheus Metrics**: Tracks both system and custom middleware metrics.
- **🛠️ Modular Pipeline**: Easily extendable middleware chain architecture.

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose

---

1. Clone the repository:
```
git clone https://github.com/yourusername/WebMiddlewarePipeline.git
cd WebMiddlewarePipeline
```

2. Run Locally with Docker

```
docker-compose up --build
```

3. Access on:
```
http://localhost:8080/middlewareTest
```

4. Redis is automatically started and injected into the app.

   

### 📊 Prometheus Metrics
1. Metrics are available at:
```
http://localhost:8080/actuator/prometheus
```

3. Sample Output:
```
middleware_rate_limit_exceeded_total 3.0
```   

---

## Testing

- Use [Postman](https://www.postman.com/) to send requests to your endpoints.
- Add the header:
    ```
    Authorization: Bearer <your_valid_jwt>
    ```
- Expected behavior:
    - ✅ Valid JWT + within rate limit → `200 OK`
    - ✅ Invalid or missing JWT → `401 Unauthorized`
    - ✅ Rate limit exceeded → `429 Too Many Requests`

---

## Project src/main/java/com/middleware/
```
WebMiddlewarePipeline/
├── src/main/java/com/middleware/
│   ├── core/           # Middleware interface & chain
│   ├── middleware/     # JwtAuth, Logging, RateLimit
│   ├── config/         # JwtConfig
│   └── controller/     # Example endpoints
├── src/main/resources/
│   └── application.properties
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```
---

## License

MIT License.

---

## Why This Project

This project demonstrates **middleware pipeline design, JWT-based authentication, Redis-backed rate limiting, and Prometheus observability via Micrometer** using Spring Boot for **production-aligned backend engineering readiness**.


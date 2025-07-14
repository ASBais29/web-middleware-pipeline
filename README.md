# WebMiddlewarePipeline 🚀

A **Spring Boot middleware pipeline** implementing:

✅ **JWT Authentication**  
✅ **Redis-backed Rate Limiting**  
✅ **Request Logging**

designed for **clean, scalable request handling in Java microservices**.

---

## Features

- **📝 Request Logging**: Logs HTTP method and path for observability and debugging.
- **🔐 JWT Authentication**: Validates Bearer tokens, rejecting invalid or missing tokens early.
- **🚦 Redis Rate Limiting**: Enforces per-IP rate limiting using Redis for distributed readiness.
- **🛠️ Modular Pipeline**: Easily extendable middleware chain architecture.

---

## Getting Started

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
http://localhost:8080
```

4. Redis is automatically started and injected into the app. 



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
├── middleware/ # JwtAuth, Logging, RateLimit
├── core/ # Middleware interface and chain
├── controller/ # Example endpoints
└── config/ # Redis config
```
---

## License

MIT License.

---

## Why This Project

This project demonstrates **middleware pipeline design, JWT-based authentication, and Redis-backed rate limiting** using Spring Boot for **production-aligned backend engineering readiness**.

WebMiddlewarePipeline 🚀
A Spring Boot middleware pipeline implementing:

✅ JWT Authentication
✅ Redis-backed Rate Limiting
✅ Request Logging
# WebMiddlewarePipeline 🚀

A **Spring Boot middleware pipeline** implementing:

✅ **JWT Authentication**  
✅ **Redis-backed Rate Limiting**  
✅ **Request Logging**

designed for **clean, scalable request handling in Java microservices**.

---

## Features

- **🔐 JWT Authentication**: Validates Bearer tokens, rejecting invalid or missing tokens early.
- **🚦 Redis Rate Limiting**: Enforces per-IP rate limiting using Redis for distributed readiness.
- **📝 Request Logging**: Logs HTTP method and path for observability and debugging.
- **🛠️ Modular Pipeline**: Easily extendable middleware chain architecture.

---

## Getting Started

1. **Run Redis locally** (using Docker recommended).
2. Clone the repository:
    ```
    git clone [https://github.com/yourusername/WebMiddlewarePipeline.git](https://github.com/ASBais29/web-middleware-pipeline.git)
    cd WebMiddlewarePipeline
    ```
3. Run the application:
    ```
    mvn spring-boot:run
    ```
4. Access on:
    ```
    http://localhost:8080
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

## Project Structure


Designed for clean, scalable request handling in Java microservices.

📌 Features
JWT Auth Middleware: Validates Bearer tokens, rejecting invalid/missing tokens early.

Redis Rate Limiting: Per-IP rate limiting using Redis for distributed readiness.

Request Logging: Logs HTTP method and path for easy debugging and traceability.

Modular Pipeline: Easily extendable middleware architecture.

🚀 Getting Started
1️⃣ Run Redis locally (using Docker recommended).

2️⃣ Clone and run:

git clone https://github.com/ASBais29/web-middleware-pipeline.git
cd WebMiddlewarePipeline
mvn spring-boot:run

3️⃣ App will be available on:
http://localhost:8080
🔐 Testing
Use Postman to send requests to your endpoints.

Add the Authorization: Bearer <valid_jwt> header.

Observe:

Valid JWT: Allowed within rate limits.

Invalid/Missing JWT: 401 Unauthorized.

Rate limit exceeded: 429 Too Many Requests.

🛠️ Project Structure

  src/main/java/com/middleware/
      ├── middleware/       # JwtAuth, Logging, RateLimit
      ├── core/             # Middleware interface and chain
      └── controller/       # Example endpoints
      
📄 License
MIT License.

✨ Why This Project
This project demonstrates middleware pipeline design, scalable authentication, and distributed rate limiting with Redis, showcasing clean engineering practices for backend system design readiness.

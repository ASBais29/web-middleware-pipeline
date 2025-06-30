# Web Middleware Pipeline

A modular Java Spring Boot project demonstrating a customizable middleware pipeline for HTTP request processing. Middleware components such as JWT authentication, logging, and rate limiting can be chained and managed flexibly.

## Features
- Middleware pipeline pattern for HTTP requests
- Built-in middleware examples: JWT Auth, Logging, Rate Limiting
- Easily extendable with custom middleware
- Simple REST endpoint for testing

## Prerequisites
- Java 17 or higher
- Maven 3.6+

## Getting Started

### 1. Clone the repository
```sh
git clone <repo-url>
cd web-middleware-pipeline
```

### 2. Build the project
```sh
mvn clean package
```

### 3. Run the application
You can run the app using Maven:
```sh
mvn spring-boot:run
```
Or run the packaged JAR:
```sh
java -jar target/web-middleware-pipeline-1.0.0.jar
```

The server will start on [http://localhost:8080](http://localhost:8080).

## API Usage

### Test Endpoint
- **URL:** `GET /middlewareTest`
- **Description:** Passes the request through the middleware pipeline.
- **Headers:**
  - `Authorization: Bearer valid-token` (required, see below)

#### Example curl command
```sh
curl -i -H "Authorization: Bearer valid-token" http://localhost:8080/middlewareTest
```
- **Success Response:**
  - Status: `200 OK`
  - Body: `✅ Middleware pipeline passed. Hello!`

If the token is missing or invalid, you will receive a `401 Unauthorized` response.

## Middleware Implementation
- Middleware classes implement the `Middleware` interface.
- The pipeline is managed by `MiddlewareChain`.
- Example middleware: `JwtAuth`, `Logging`, `RateLimit` (see `src/main/java/com/middleware/middleware/`).

## Development Notes
- The JWT middleware expects the token to be exactly `valid-token` for demo purposes.
- You can add or modify middleware in the `com.middleware.middleware` package.
- Configuration can be adjusted in `src/main/resources/application.yml`.

## License
MIT or specify your license here.

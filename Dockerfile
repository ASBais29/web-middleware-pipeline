# Use OpenJDK base image
FROM eclipse-temurin:17-jdk

# Set workdir inside container
WORKDIR /app

# Copy and build the JAR
COPY target/web-middleware-pipeline-1.0.0.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
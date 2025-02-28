# Use OpenJDK 23 as base image
FROM openjdk:23-jdk-slim
# Alternative
# FROM eclipse-temurin:23-jdk

# Set working directory
WORKDIR /app

# Copy JAR file into the container
COPY target/*.jar app.jar

# Expose application port
EXPOSE 8081

# Run Spring Boot application
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${SERVER_PORT:-8081}"]
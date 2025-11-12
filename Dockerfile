# Use Java 17 base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven build files
COPY pom.xml .
COPY src ./src

# Package the application (skip tests for faster builds)
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/clubmanagement-0.0.1-SNAPSHOT.jar"]
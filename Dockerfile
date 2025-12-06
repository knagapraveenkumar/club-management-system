# Step 1: Use a Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the JAR (skip tests for speed)
RUN mvn clean package -DskipTests

# Step 2: Use a smaller runtime image for the app
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built jar from the previous step
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
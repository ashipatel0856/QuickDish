# 1. Stage 1: Use Maven to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build

# 2. Working directory inside container
WORKDIR /app

# 3. Copy everything into the container
COPY . .

# 4. Build the application (skip tests if needed)
RUN mvn clean package -DskipTests

# 5. Stage 2: Use smaller image for running the app
FROM openjdk:17-jdk-slim

# 6. Working directory for runtime container
WORKDIR /app

# 7. Copy the JAR file from the build stage
COPY --from=build /app/target/QuickDish-0.0.1-SNAPSHOT.jar app.jar

# 8. Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

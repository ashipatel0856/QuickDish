# 1. OpenJDK 17 base image use kar rahe hain
FROM openjdk:17-jdk-slim

# 2. Container ke andar ek working directory bana rahe hain
WORKDIR /app

# 3. Target folder se JAR copy kar rahe hain
COPY target/QuickDish-0.0.1-SNAPSHOT.jar app.jar

# 4. App ko run karne ka command
ENTRYPOINT ["java", "-jar", "app.jar"]

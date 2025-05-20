FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y maven && mvn clean install -DskipTests

CMD ["java", "-jar", "target/QuickDish-0.0.1-SNAPSHOT.jar"]

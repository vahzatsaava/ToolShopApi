FROM maven:3.8.6 AS builder
WORKDIR /app
COPY src /app/src
COPY pom.xml /app/


RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=builder /app/target/ToolShopApi-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]

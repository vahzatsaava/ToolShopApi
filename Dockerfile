FROM maven:3.8.4-17-jdk-slim-buster AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package


FROM openjdk:17-jdk-slim-buster
VOLUME /tmp
ARG JAR_FILE=target/ToolShopApi-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM openjdk:11
WORKDIR /app
ADD https://github.com/vahzatsaava/ToolShopApi_Back/raw/master/target/ToolShopApi-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]

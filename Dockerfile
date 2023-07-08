FROM openjdk:11-jdk
WORKDIR /app
COPY target/myShop.jar /app/myShop.jar
ENTRYPOINT ["java", "-jar", "/app/myShop.jar"]

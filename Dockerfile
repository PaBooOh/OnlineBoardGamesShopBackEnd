FROM openjdk:11-jdk
WORKDIR /appback
COPY target/myShop.jar /appback/myShop.jar
ENTRYPOINT ["java", "-jar", "/appback/myShop.jar"]

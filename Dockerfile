FROM adoptopenjdk/openjdk11
WORKDIR /app
COPY target/myShop.jar /app/myShop.jar
ENTRYPOINT ["java", "-jar", "/app/myShop.jar"]

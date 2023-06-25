FROM adoptopenjdk/openjdk11
COPY target/myShop.jar /usr/local/myShop.jar
WORKDIR /usr/local
ENTRYPOINT ["java", "-jar", "/usr/local/myShop.jar"]

FROM adoptopenjdk/openjdk11
WORKDIR /usr/local
COPY target/myShop.jar /usr/local/myShop.jar
ENTRYPOINT ["java", "-jar", "/usr/local/myShop.jar"]

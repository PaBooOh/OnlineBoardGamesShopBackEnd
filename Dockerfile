# 使用 OpenJDK 17 作为基础镜像
FROM openjdk:17-jdk

# 将工作目录设为 /app
WORKDIR /usr/local

# 将你的 jar 文件复制到 Docker 容器中的 /app 目录
COPY target/OnlineBoardGamesShop-Init.jar /usr/local/OnlineBoardGamesShop.jar

# 设置需要暴露的端口号
#EXPOSE 9991

# 设置当 Docker 容器启动时运行的命令
ENTRYPOINT ["java", "-jar", "/usr/local/OnlineBoardGamesShop.jar"]

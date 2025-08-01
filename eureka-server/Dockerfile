FROM eclipse-temurin:21-jdk

EXPOSE 8761

WORKDIR /app

ARG JAR_FILE=target/eureka-server-0.9.0.jar

ADD ${JAR_FILE} app.jar

CMD ["java", "-jar", "app.jar"]
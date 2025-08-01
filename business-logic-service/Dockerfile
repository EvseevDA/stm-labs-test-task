FROM eclipse-temurin:21-jdk

EXPOSE 8081

WORKDIR /app

ARG JAR_FILE=target/business-logic-service-0.9.0.jar

ADD ${JAR_FILE} app.jar

CMD ["java", "-jar", "app.jar"]
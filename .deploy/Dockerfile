FROM maven:3.9.0-eclipse-temurin-19 AS build

WORKDIR /app/5-dana-u-oblacima

COPY . /app

RUN mvn clean package 


FROM amazoncorretto:19

ARG JAR_FILE=5-dana-u-oblacima/target/*.jar

COPY --from=build /app/${JAR_FILE} /application.jar

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]

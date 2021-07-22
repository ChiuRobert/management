FROM maven:3.6.3-jdk-11-slim as maven

COPY ./pom.xml ./pom.xml

COPY ./src ./src

RUN mvn clean install

FROM openjdk:11.0.6-slim

COPY --from=maven target/management.jar management.jar

ENTRYPOINT ["java", "-jar", "management.jar"]


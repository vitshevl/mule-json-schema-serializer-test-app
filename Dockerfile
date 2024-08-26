FROM openjdk:17-jdk-slim
WORKDIR demo

COPY  target/*.jar demo.jar

COPY target/classes/*.jks /security/

EXPOSE 8093
ENTRYPOINT ["java", "-jar", "demo.jar"]

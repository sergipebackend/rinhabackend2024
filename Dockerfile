FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar rinhabackend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/rinhabackend-0.0.1-SNAPSHOT.jar"]
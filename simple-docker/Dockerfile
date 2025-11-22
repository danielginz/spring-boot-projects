FROM openjdk:20-ea-jdk-slim-buster
WORKDIR /app
COPY /target/simple-docker-0.0.1-SNAPSHOT.jar /app/simpledocker.jar
ENTRYPOINT ["java", "-jar", "simpledocker.jar"]
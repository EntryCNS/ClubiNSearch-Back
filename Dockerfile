FROM openjdk:11
WORKDIR /
EXPOSE 8080
COPY build/libs/ClubiNSearch-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
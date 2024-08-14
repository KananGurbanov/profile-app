FROM openjdk:17-jdk-slim
COPY build/libs/profile-app-service-0.0.1-SNAPSHOT.jar profile-app.jar
ENTRYPOINT ["java", "-jar", "profile-app.jar"]
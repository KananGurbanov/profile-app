FROM openjdk:11
COPY build/libs/profile-app-service-0.0.1-SNAPSHOT-plain.jar profile-app.jar
ENTRYPOINT ["java", "-jar", "profile-app.jar"]
FROM openjdk:11-jre-slim
COPY build/libs/zerobase-cms-0.0.1-SNAPSHOT.jar user-api.jar
ENTRYPOINT ["java", "-DSpring.profiles.active=prod", "-Dmailgun.key=${MAILGUN_KEY}", "-jar", "user-api.jar"]
FROM openjdk:11
ADD ./target/notification-service-1.0-SNAPSHOT.jar /usr/src/notification-service-1.0-SNAPSHOT.jar
EXPOSE 8811
WORKDIR usr/src
ENTRYPOINT ["java","-jar","notification-service-1.0-SNAPSHOT.jar"]

FROM openjdk:11

ADD ./target/UserProfile-0.0.1-SNAPSHOT.jar /event/src/UserProfile-0.0.1-SNAPSHOT.jar
EXPOSE 8086
WORKDIR event/src
ENTRYPOINT ["java","-jar","UserProfile-0.0.1-SNAPSHOT.jar"]

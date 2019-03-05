FROM openjdk:11
ADD ./target/recommendation-query-service-0.0.1-SNAPSHOT.jar /event/src/recommendation-query-service-0.0.1-SNAPSHOT.jar
EXPOSE 8081
WORKDIR event/src
ENTRYPOINT ["java","-jar","recommendation-query-service-0.0.1-SNAPSHOT.jar"]

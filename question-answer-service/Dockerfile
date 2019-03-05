FROM openjdk:11

ADD ./target/questionanswerservice-0.0.1-SNAPSHOT.jar /event/src/questionanswerservice-0.0.1-SNAPSHOT.jar
EXPOSE 8090
WORKDIR event/src
ENTRYPOINT ["java","-jar","questionanswerservice-0.0.1-SNAPSHOT.jar"]
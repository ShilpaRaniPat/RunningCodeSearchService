FROM openjdk:11
ADD ./target/recommendationcommandservice-0.0.1-SNAPSHOT.jar /usr/src/recommendationcommandservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
WORKDIR usr/src
ENTRYPOINT ["java","-jar","recommendationcommandservice-0.0.1-SNAPSHOT.jar"]

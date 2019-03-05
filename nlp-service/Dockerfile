FROM openjdk:11
ADD ./target/nlp-0.0.1-SNAPSHOT.jar /usr/src/nlp-0.0.1-SNAPSHOT.jar
EXPOSE 8070
WORKDIR /usr/src
ENTRYPOINT ["java","-jar","nlp-0.0.1-SNAPSHOT.jar"]

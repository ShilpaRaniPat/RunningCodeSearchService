FROM openjdk:11
ADD ./target/user-authentication-service-0.0.1-SNAPSHOT.war /usr/src/user-authentication-service-0.0.1-SNAPSHOT.war
EXPOSE 8084
WORKDIR usr/src
ENTRYPOINT ["java","-jar","user-authentication-service-0.0.1-SNAPSHOT.war"]

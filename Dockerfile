FROM openjdk:11
VOLUME /tmp
EXPOSE 8080/tcp
COPY target/cooperativismo-votacao-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar"]
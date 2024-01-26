FROM openjdk:17
COPY target/uni-manage-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java","-jar","/app.jar"]
HEALTHCHECK CMD curl http://localhost:8080/

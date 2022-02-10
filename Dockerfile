FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/fleetapp_v2-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

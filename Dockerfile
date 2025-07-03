FROM openjdk:21
WORKDIR /app
COPY target/transfer-service2-1.0-SNAPSHOT.jar app.jar
EXPOSE 5500
CMD ["java", "-jar", "app.jar"]
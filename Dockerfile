FROM eclipse-temurin:17-jdk-jammy
ENV JAVA_OPTS=""
WORKDIR /app
COPY target/snakegame-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
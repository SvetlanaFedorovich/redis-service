FROM openjdk:17-jdk-alpine3.14
COPY ./build/libs/redis-service-1.0.1.jar redis-service.jar
ENTRYPOINT ["java", "-jar", "redis-service.jar"]

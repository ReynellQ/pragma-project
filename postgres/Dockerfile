FROM postgres:15.2-alpine
VOLUME /tmp
EXPOSE 8761
ADD ./build/libs/eureka-service-0.0.1-SNAPSHOT.jar eureka-service.jar
ENTRYPOINT ["java", "-jar", "/eureka-service.jar"]
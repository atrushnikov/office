FROM openjdk:11
ADD build/libs/office-0.1.jar /usr/src/office/office-0.1.jar
WORKDIR /usr/src/office
RUN mkdir -p resources
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "office-0.1.jar", "--spring.config.location=resources/application.properties"]
FROM openjdk:11
ADD build/libs/office-1.1.jar /usr/src/office/office.jar
WORKDIR /usr/src/office
RUN mkdir -p resources
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "office.jar", "--spring.config.location=resources/application.properties"]
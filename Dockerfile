FROM openjdk:11
ADD build/libs/office-0.1.jar office-0.1.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "office-0.1.jar"]
FROM openjdk:17
EXPOSE 8080
ADD target/donation-management-system.jar donation-management-system.jar
ENTRYPOINT ["java","-jar","/donation-management-system.jar"]
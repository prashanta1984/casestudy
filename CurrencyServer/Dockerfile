FROM openjdk:8
ADD target/currencyserver.jar currencyserver.jar
EXPOSE 8101
ENTRYPOINT ["java", "-jar", "currencyserver.jar"]
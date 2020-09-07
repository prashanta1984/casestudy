FROM openjdk:8
ADD target/currency-conversion-factor.jar currency-conversion-factor.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "currency-conversion-factor.jar"]
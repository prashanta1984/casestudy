FROM openjdk:8
ADD target/converted-currency.jar converted-currency.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "converted-currency.jar"]
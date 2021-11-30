FROM openjdk:8-jdk-alpine
COPY target/shoppingcart-0.0.1-SNAPSHOT.jar shoppingcart-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/shoppingcart-0.0.1-SNAPSHOT.jar"]

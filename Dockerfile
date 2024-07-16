FROM openjdk:22
WORKDIR /app
ADD build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]


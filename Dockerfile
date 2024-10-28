FROM maven:3.9.8-amazoncorretto-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Biên dịch dự án
RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Chạy ứng dụng
CMD ["java", "-jar", "app.jar"]

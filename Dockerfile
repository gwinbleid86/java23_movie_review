#FROM maven:3.9.8-amazoncorretto-21 AS build
#WORKDIR /build
#COPY src ./src
#COPY pom.xml ./
#RUN mvn clean package -DskipTests

FROM openjdk:21
RUN mkdir /app
COPY ./movie_review*jar ./app/movie_review.jar
WORKDIR /app

EXPOSE 8089
CMD ["java", "-jar", "movie_review.jar"]
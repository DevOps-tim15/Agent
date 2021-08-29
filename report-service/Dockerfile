FROM nikolina97/maven-repo:0.0.1 AS appProductBuild
#FROM maven:3.6.3-ibmjava-8-alpine AS appProductBuild
ARG STAGE=prod
WORKDIR /usr/src/report-service
COPY . .
RUN mvn package -P${STAGE} -DskipTests

FROM openjdk:8-jdk-alpine AS appProductRuntime
WORKDIR /app
COPY --from=appProductBuild /usr/src/report-service/target/report.jar ./
EXPOSE 8083
CMD java -jar report.jar
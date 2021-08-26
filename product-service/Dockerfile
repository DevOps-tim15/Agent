FROM nikolina97/maven-repo:0.0.1 AS appProductBuild
#FROM maven:3.6.3-ibmjava-8-alpine AS appProductBuild
ARG STAGE=prod
WORKDIR /usr/src/product-service
COPY . .
RUN mvn package -P${STAGE} -DskipTests

FROM openjdk:8-jdk-alpine AS appProductRuntime
WORKDIR /app
COPY --from=appProductBuild /usr/src/product-service/target/products.jar ./
EXPOSE 8080
CMD java -jar products.jar
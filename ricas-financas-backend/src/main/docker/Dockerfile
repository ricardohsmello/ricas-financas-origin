FROM openjdk:8-jdk-alpine
MAINTAINER Ricardo Mello
RUN mkdir -p /usr/local/dockerize
ADD ricasfinancas.jar /usr/local/dockerize
RUN java -jar /usr/local/dockerize/ricasfinancas.jar
EXPOSE 9999
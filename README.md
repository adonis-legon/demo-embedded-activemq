# Demo project for using an embedded ActiveMQ in a Spring Boot App

## Run (using Maven)

`$ mvn spring-boot:run`

## Send Messages

`$ curl -X POST http://localhost:10000/api/v1/messages/ --header "Content-Type: plain/text" --data "test abdc"`

# Ricas Finanças personal financial 

This is my personal project named Ricas Finanças.
Ricas Finanças it's a personal financial control project that allows you to track your expenses and income

This repository contains a front-end and a backend application. First of all we need to talk about Ricas-financas-backend which 
is an spring-boot project that exposes all of the rest interfaces for fron-end consume.

To access resources, you must be authenticated. Authentication is done through JWT token which in turn is generated from a valid username and password that is registered in the postgres database containerized in a docker.

The front-end application is a simple angular 8 project.

## Build With
- Spring-boot
- Postgres
- Angular 8

# Usage
## Running
Navigate until ricas-financas-backend root and run:
```
- mvn spring-boot:run
 ```

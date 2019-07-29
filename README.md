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

First of all we need to up our postgres database. For this, we'll go up a instance database image using a docker container.
Navigate until ricas-financas-backend\src\main\docker root and run:
```
docker-compose up -d
```
![Docker ps](https://imagizer.imageshack.com/img924/7782/vdFv2E.png)

As you can see above, we started postgres database and pgadmin
 - ricas-financas_postgres
 - ricas-financas_pgadmin

Check if your database is up accessing pgadmin interface:
```
http://localhost:9090/
```
 - username: ricasfinancas@gmail.com
 - password: postgres

![pgadmin login](https://imagizer.imageshack.com/img924/7372/K9Shkk.png)

Navigate until ricas-financas-backend root and run:
```
- mvn spring-boot:run
 ```

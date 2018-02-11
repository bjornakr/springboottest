# springboottest #

Testing the Spring Boot framework. The idea is to create a REST microservice, package it to docker and serve it from a Kubernetes cluser in the cloud.

### Useful links ###

* [The Spring Boot reference guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* https://cloud.google.com/kubernetes-engine/docs/tutorials/


### Prerequesites ###
* Java 9.0.4
* Postgres server
* Docker
* Maven 3.5.2

### Major Dependencies ###
* Spring Boot
* Flyway
* Postgres
* Docker


### Setup instructions ###
1. Create a Postgres database for the application (see below).
1. Create a user in Postgres with full rights on the database (see below). 
1. Create a deployable .jar of the application.
    1. From the root directory: `mvn clean package`. This creates an self contained web server.
1. Create a local config in a secure directory, outside of the webroot.
    1. Example: in `~/.springboottest/application-test.config`
    1. `chmod -R 700 ~/.springboottest`
    1. See below for example configuration. 
1. Run the application: `java -jar -Dspring.config.location=~/.springboottest/application-test.config`.


#### Database creation example ####
```sql
-- Create database
CREATE DATABASE springboottest;

-- Create user
CREATE ROLE springboot WITH LOGIN PASSWORD '<password>';
GRANT ALL PRIVILEGES ON DATABASE springboottest TO springbootuser;
```

#### Local configuration example ####
```
logging.file=sprinbboottest-test.log
logging.level.root=INFO
logging.level.org.hibernate=INFO
spring.datasource.url= jdbc:postgresql://localhost:5433/<database>
spring.datasource.username=<user>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=validate
```
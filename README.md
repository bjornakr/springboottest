# Codename: Desert Snake #

Testing the Spring Boot framework. The idea is to create a REST microservice, package it to docker and serve it from a Kubernetes cluser in the cloud.

### Useful links ###

* [The Spring Boot reference guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
* https://cloud.google.com/kubernetes-engine/docs/tutorials/
* https://blog.philipphauer.de/dont-use-in-memory-databases-tests-h2/

### Prerequesites ###
* Java 8
* Postgres server
* Docker
* Maven 3.5.2

### Major Dependencies ###
* Spring Boot
* Flyway
* Postgres
* Docker


### Setup instructions ###

#### Development ####

##### No Docker #####

1. Create TWO postgres databases, one for running the application, 
   and one for automated tests. You can use the SQL below.  
1. Create a user in Postgres with full rights on the databases (see below). 
1. In `src/main/java/resources`:
   1. Copy `application-local.properties-example` to `application-local.properties'
   2. Enter the correct database credentials in the local config.
1. Repeat for `src/test/java/resources`.
1. Run `mvn clean install`. The project should compile, and all tests should pass.

##### Docker ###### 

1. \<...>


#### Deploy a self-contained jar ####
1. From the project root directory: `mvn clean package`. This creates an self contained web server.
1. Create a local config in a secure directory, outside of the webroot.
    1. Example: `~/.desertsnake/application.config`
    1. `chmod -R 700 ~/.desertsnake` 
1. Run the application: `java -jar -Dspring.config.location=~/.desertsnake/application.config`.


#### Database creation example ####
```sql
-- Create database
CREATE DATABASE desertsnake;

-- Create user
CREATE ROLE developer WITH LOGIN PASSWORD '<password>';
GRANT ALL PRIVILEGES ON DATABASE desertsnake TO developer;
```

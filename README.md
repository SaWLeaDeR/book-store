# Book-Store-App
# Book-Store Application built with Spring Cloud

[![Build Status](https://travis-ci.org/spring-petclinic/spring-petclinic-microservices.svg?branch=master) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Starting services locally without Docker

Every microservice is a Spring Boot application and can be started locally using IDE ([Lombok](https://projectlombok.org/) plugin has to be set up) or `../mvnw spring-boot:run` command. Please note that supporting services (Config and Discovery Server) must be started before any other application (Customers, Vets, Visits and API).
Startup of Tracing server, Admin server, Grafana and Prometheus is optional.
If everything goes well, you can access the following services at given location:
* Config Server - http://localhost:8888/config-server
* Tracing Server (Zipkin) - http://localhost:9411/zipkin/ (we use [openzipkin](https://github.com/openzipkin/zipkin/tree/master/zipkin-server))
* Book-Store (Spring Boot Admin) - http://localhost:8081/book-store
* Customer - http://localhost:8083/customer
* Book-Service - http://localhost:8083/book-service
* MongoDB - http://localhost:27017  //   username : root & password : example
* Mongo Express - http://localhost:8082 - Dockerized tool for using mongo
* Mongo Client - http://localhost:3030 - Dockerized tool for manage mongo db
* Grafana Dashboards - http://localhost:3000
* Prometheus - http://localhost:9091

You can tell Config Server to use your local Git repository by using `native` Spring profile and setting
`GIT_REPO` environment variable, for example:
`-Dspring.profiles.active=native -DGIT_REPO=/projects/springboothomework-configuration`

Once build `base-parent` and `base-service` with this command for add custom dependencies to local repo
```
cd base-parent
mvn clean install
...
cd ../base-service
mvn clean install
```
Change the Application boostrap file change it to localhost not network in docker
First run docker compose without config-server and customer and book-service and book-store services. (For Mongo Database)
Run config-server first
After Run customer, book-service and book-store with `-Dspring.profiles.active=test` (test-profile)

For build Docker image EX:config-server
```
cd config-server
./mvnw clean install -P buildDocker
```

There is so much to do here. Licence is open. We can make it better

### Swagger

Swagger Client written but not configured because of the version of my spring version. There is a bug with keyword is
`Failed to start bean 'documentationPluginsBootstrapper'` which cause of Spring 2.6 changes
the default strategy for matching request paths against registered Spring MVC handler mappings has changed from AntPathMatcher to PathPatternParser.
[Swagger problem with spring fox](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.6-Release-Notes#pathpattern-based-path-matching-strategy-for-spring-mvc).


## Starting services locally with docker-compose
In order to start entire infrastructure using Docker, you have to build images by executing `./mvnw clean install -P buildDocker`
from each project root. Once images are ready, you can start them with a single command
`docker-compose up`. Containers startup order is coordinated with [`dockerize` script](https://github.com/jwilder/dockerize).
After starting services it takes a while for composition of the syncing with service registry,

### Dockker Compose

For easy to use all images are built and pushed to dockerhub and users can use single command
`docker-compose up` is enough for the compilation

The `master` branch uses an  Alpine linux  with JRE 8 as Docker base. You will find a Java 11 version in the `release/java11` branch.

*NOTE: Under MacOSX or Windows, make sure that the Docker VM has enough memory to run the microservices. The default settings
are usually not enough and make the `docker-compose up` painfully slow.*

## Understanding the Book-Store Application

### Postman collection
https://www.getpostman.com/collections/17eff8ef62ee49081edc

You can then access Store File here: http://localhost:8081/store-api

### 1) Firstly Get Token Uses JWT
```
localhost:8081/book-store/authenticate
```
Body:
```
{
    "username" : "baseUser",
    "password" : "password"
}
```
There is only one user can join the system. Because Token database is not exist.

### 2) SaveCustomer
```
localhost:8081/book-store/customer
```


After running container 
You can test with these commands
```
docker ps
---
CONTAINER ID   IMAGE                                   COMMAND                  CREATED          STATUS                             PORTS                               NAMES
15ce523b6f44   fatihkoyuncuoglu/customer:latest        "./dockerize -wait=t…"   12 minutes ago   Up 12 minutes               0.0.0.0:8083->8083/tcp             customer
f1a105863b79   mongoclient/mongoclient                 "./entrypoint.sh nod…"   12 minutes ago   Up 12 minutes               0.0.0.0:3030->3000/tcp             readingisgood-mongo-client-1
adf4da3e74ce   mongo-express                           "tini -- /docker-ent…"   12 minutes ago   Up 11 minutes               0.0.0.0:8082->8081/tcp             readingisgood-mongo-express-1
ab267d62a8d4   fatihkoyuncuoglu/book-service:latest    "./dockerize -wait=t…"   12 minutes ago   Up 12 minutes               0.0.0.0:8084->8084/tcp             book-service
879044119f53   fatihkoyuncuoglu/book-store:latest      "./dockerize -wait=t…"   12 minutes ago   Up 12 minutes               0.0.0.0:8081->8081/tcp             book-store
bb9820b5e261   readingisgood_grafana-server            "/run.sh"                12 minutes ago   Up 12 minutes               0.0.0.0:3000->3000/tcp             grafana-server
b99f8eaaf9c8   mongo                                   "docker-entrypoint.s…"   12 minutes ago   Up 12 minutes               0.0.0.0:27017->27017/tcp           readingisgood-mongo-1
d2d0e7684eda   openzipkin/zipkin                       "start-zipkin"           12 minutes ago   Up 12 minutes (healthy)     9410/tcp, 0.0.0.0:9411->9411/tcp   tracing-server
257d94deb993   fatihkoyuncuoglu/config-server:latest   "java org.springfram…"   12 minutes ago   Up 12 minutes (unhealthy)   0.0.0.0:8888->8888/tcp             config-server

```
After this command you need to go inside container with this command
```
docker exec -it 6fea65ba853c sh                         # book-store containerId
```
Now you are in container In this path you can see files those you saved before.
All file names hashed by algorithm and stored by that name.
```
cd /var/log/java
```

For Log 
```
docker logs -f containerId 
## For Example customer container id 
## docker logs -f 15ce523b6f44
```

### Logging

Specialized Logging when any document effected for your attraction please look logs

### GetFileInfo
That gives you file info that you stored before.
You will use the hash and extension for getting file
```
localhost:8081/store-api/file/info
```




Locally saves file
Get file info from mysql

// TODO:

There is not enough time for writes these endpoints

Get File

Get File Infos



## Database configuration

In its default configuration, customer and any other uses an 
database (Mongo) which gets populated at startup with data.
Database only stores collections of your customer or book

### Start a Mongo database

You may start a Mongo database with docker:

```
docker run -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=bootdb -p 27017:27017 mongo:latest
```
or download and install the MongoDb database (e.g., Mongo Server 4.x), which can be found here: https://hub.docker.com/_/mongo

## Custom metrics monitoring

Grafana and Prometheus are included in the `docker-compose.yml` configuration, and the public facing applications
have been instrumented with [MicroMeter](https://micrometer.io) to collect JVM and custom business metrics.

A JMeter load testing script is available to stress the application and generate metrics:



### Using Prometheus

* Prometheus can be accessed from your local machine at http://localhost:9091

### Using Grafana with Prometheus

* An anonymous access and a Prometheus datasource are setup.
* A `Lorem Ipsum Metrics` Dashboard is available at the URL http://localhost:3000/d/69JXeR0iw/lorem-ipsum-title.
  You will find the JSON configuration file here: Json configuration not completed
* You may create your own dashboard or import the [Micrometer/SpringBoot dashboard](https://grafana.com/dashboards/4701) via the Import Dashboard menu item.
  The id for this dashboard is `4701`.

### Custom metrics
Spring Boot registers a lot number of core metrics: JVM, CPU, Tomcat, Logback...
The Spring Boot auto-configuration enables the instrumentation of requests handled by Spring MVC.
All those three REST controllers `OwnerResource`, `PetResource` and `VisitResource` have been instrumented by the `@Timed` Micrometer annotation at class level.

# Contributing

The [issue tracker](https://github.com/SaWLeaDeR/book-store/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>.


[Spring Boot Actuator Production Ready Metrics]: https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html

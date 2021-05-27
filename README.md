# R2DBC Sample Application

This repository contains a simple ([Apache Maven](https://maven.apache.org/)-based) Java application. The application contains [Spring Framework](https://spring.io/) dependencies, including [Spring Boot](https://spring.io/projects/spring-boot) and [Spring Data R2DBC](https://spring.io/projects/spring-data-r2dbc). 

The purpose of this application is to demonstrate capabilities of [MariaDB Connector/R2DBC](https://mariadb.com/docs/clients/connector-r2dbc/) and [Spring Data R2DBC](https://spring.io/projects/spring-data-r2dbc).

This application was used as the live demonstration in a [MariaDB Webinar](https://go.mariadb.com/21Q3-WBN-GLBL-OSSG-Unleash-Reactive-Programming-R2DBC-2021-05-27_Registration-LP.html?_ga=2.230885407.1007250852.1621861445-706426216.1609965524&_gac=1.27734478.1619720874.Cj0KCQjwsqmEBhDiARIsANV8H3aWS3PquCgfiIt-oP0XuLmENXrOOSQ0kCuQme1Iqv8FmpVJi-cwrSIaAjoaEALw_wcB).

## Preparing the database

In order to use this application you will need to first run the following SQL commands on a running instance of MariaDB. 

```sql 
CREATE DATABASE todo;

CREATE TABLE todo.tasks (
  id INT(11) unsigned NOT NULL AUTO_INCREMENT,
  description VARCHAR(500) NOT NULL,
  completed BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);
```

## Configuring the application

Next, make sure you you update the database connection settings in the [application.properties](src/main/resources/application.properties) file.

```xml
spring.r2dbc.url=r2dbc:mariadb://127.0.0.1:3306/todo
spring.r2dbc.username=app_user
spring.r2dbc.password=Password123!
```

## Support and Contribution <a name="support-contribution"></a>

Thanks so much for taking a look at this sample demo app! Please feel free to submit PR's to the project to include your modifications!

If you have any questions, comments, or would like to contribute to this or future projects like this please reach out to us directly at [developers@mariadb.com](mailto:developers@mariadb.com) or on [Twitter](https://twitter.com/mariadb).

## License <a name="license"></a>
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=plastic)](https://opensource.org/licenses/MIT)
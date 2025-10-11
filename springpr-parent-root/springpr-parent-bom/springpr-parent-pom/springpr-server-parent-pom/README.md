> README FILE

---

# Maven Server Parent POM for Spring Boot based Paved Road.

* The POM provides dependency definitions and dependencies management for most commonly used Spring Boot features when the application is deployed as a web server.

## Summary

This project provides a pom file that may be used as a parent pom for a Spring Boot based application that is deployed as a web server.

The pom has the following required dependencies:

- spring-web
- springdoc-openapi-starter-common
    - for automatically generating of REST API documentation
- reactor-core
    - support reactive programming

## Usage

### format pom.xml file

```
mvn spotless:apply
```

### Build POM and install it to local Maven repository

```
mvn clean install
```

> README FILE

---

## An Spring Boot based application targets ePaas deployment.

This project is a template project that provide a fast adoption of Spring Boot enterprise features and quick ePaaS ready application creation.

Once an ePaaS project has been created, please use this project as a base template of your application, you should be have a live Spring Boot based application with all important
production-ready and enterprise features deployed to E1/E2/E3 immediately.

### Out-of-box Features:

#### REST API support

- REST endpoints - Spring MVC over Tomcat
- REST request validation and error handling
- REST JSON serialization/deserialization
- Pagination Support
- Automatic REST API documentation with OpenAPI 3.0 and Swagger UI - springdoc-openapi
- HATEOAS support
- CORS support
  <br/><br/>

---

### Build and Run Instructions

#### formatting code

under directory: springpr-rest:

```
mvn spotless:apply
```

#### build the project as library that may be used in Maven as dependency

```
mvn -Plib clean install
```

#### Start application locally

change to directory: springpr-rest/springpr-rest-example

```
mvn -P'local,!lib' spring-boot:run
```

#### Checking the application

```
http://localhost:60201/springpr-rest-example/welcome
```

## 🏆 Contributing

See [contributing guidelines](./CONTRIBUTING.md)

## Custodians

- **Lead Maintainer:** [Yang Li](mailto:yangli136@gmail.com)

---

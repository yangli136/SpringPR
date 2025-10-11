> README.md

# Paved Road based on Spring Boot
---

## Common Non-Functional Features for Spring Boot based Paved Road.

This project is a base module that provides common out-of-box non-functional features from Spring Boot.

### Build and Run Instructions

#### formatting code

under directory springpr-common

```
mvn spotless:apply
```

#### build the project as library that may be used in Maven as dependency

```
mvn -Plib clean install
```

#### Start application locally

change to directory springpr-common/springpr-common-exmaple

```
mvn -P'local,!lib' spring-boot:run
```

---

#### Forcing updates of all libraries

```
mvn dependency:purge-local-repository clean install
mvn dependency:sources
```

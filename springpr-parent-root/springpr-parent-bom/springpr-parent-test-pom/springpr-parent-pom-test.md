> springpr-parent-pom-test.md

---

# Scope and features

## Delegate dependency management to spring-boot-dependencies.
- majority versions of library dependencies and Maven plugin dependencies are defined inside spring-boot-dependencies

## Define standard Build Environment Settings
- developers
- distributionManagement
- licenses
- pluginRepositories
- repositories
- scm

- Source code encoding
- Java compiler version

## logging over slf4j and log4j2
- removed logback dependencies
- removed Apache commons-logging dependencies

## Common dependencies
- Language features
    - lombok
    - AOP

- Spring Boot Fundamentals
    - spring-boot-starter
    - spring-boot-starter-json
    - spring-boot-starter-validation
    - spring-retry

- Local Cache
    - spring-boot-starter-cache
    - caffeine

- Production Support
    - spring-boot-starter-actuator

- Observability
    - micrometer-registry-prometheus
    - micrometer-registry-jmx
    - micrometer-observation
    - micrometer-tracing-bridge-brave
    - zipkin-reporter-brave

- Testing
    - junit 4 & 5
    - mock
        - awaitility
        - mockito
        - wiremock
        - reactor-test
    - Testcontainers

- devtool
    - spring-boot-devtools

- Spring configuration metadata for contextual help in IDE
    - spring-boot-configuration-processor

## Standard Build Activities
- maven-enforcer-plugin
    - enforce Java version, required files etc.
- spotless-maven-plugin
    - formatting java, md, sql, pom etc.
- google-java-format
    - enterprise wide Java code format (Google format)

- maven-compiler-plugin
- maven-surefire-plugin
    - unit test
- maven-failsafe-plugin
    - integration test

- git-commit-id-plugin
    - create a git.properties file with build information for each build
- maven-resources-plugin
- maven-assembly-plugin
    - create eCP deployable package

- spring-boot-maven-plugin
    - runnable Spring application

## Standard Build Prifiles
- create a library as dependency
- build in a build server
- build a runnable Spring Boot app

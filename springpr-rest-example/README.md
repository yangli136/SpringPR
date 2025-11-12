> README FILE

---

## Common Interfaces between REST Services and Data Stores.

This project is a base module that provides common out-of-box non-functional features from Spring Boot.

### Out-of-box Features:
### Build and Run Instructions

#### formatting code

```
mvn spotless:apply
```

#### build the project as library that may be used in Maven as dependency

```
mvn -Plib clean install
```

#### Start application locally

```
mvn -P'local,!lib' spring-boot:run
```

#### Checking the application

```
http://localhost:60101/springpr-base/welcome
```

## 🏆 Contributing

See [contributing guidelines](./CONTRIBUTING.md)

## Custodians

- **Lead Maintainer:** [Yang Li](mailto:yangli136@gmail.com)

---

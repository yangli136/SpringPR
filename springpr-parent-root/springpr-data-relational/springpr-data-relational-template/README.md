> README FILE

---

## Spring Boot based application targets ePaas deployment.

This project is a template project that provide a fast adoption of Spring Boot enterprise features and quick ePaaS ready application creation.

### Out-of-box Features:

- H2 Embeded data source for local environment and DB Unit Tests in Jenkins Build.
- PostgreSQL data source for server environments
- Hikari connection pooling
- Spring JdbcTemplate support and DAO examples
- JPA support and examples
- Transaction Management
- DAO Exception Hierarchy/Translation

---

## Usage

```
  <dependencies>
    <dependency>
      <groupId>com.aexp.springpr</groupId>
      <artifactId>springpr-data-relational-base</artifactId>
      <version>${springpr-data-relational-base.version}</version>
    </dependency>
  </dependencies>
```

---

> features.md

---

# Features of Paved Road based on Spring Boot (SpringPR)
1.  Features are available out-of-box from SpringPR
2.  Features available through Spring Boot and estimation of effort to be ready in SpringPR

| category | feature | implementation | effort | Notes |
|--|--|--|--|--|
| **Resiliency** | Liveness Probes | SpringPR | Out-of-box | SpringMVC based on Tomcat<br>or<br/>Spring WebFlux based on Netty |
| | Readiness Probes | SpringPR | Out-of-box | Vault<br/>Cassandra<br/>Kafka<br/>PostgresSQL<br/> |
| | Automatic Retry | SpringPR | Out-of-box | Multiple backoff strategies<br><br/>Fixed delay<br/>Uniform Random<br/>Exponential<br/>Exponential Random<br/><br/>only specific Exceptions |
| | Circuit Breaker | SpringPR | Out-of-box | log error only when fallback |
| | Graceful Shutdown | SpringPR | Out-of-box | Spring Boot Actuator Endpoint |
| **Observability** | Dimensional Metrics | SpringPR | Out-of-box | CPU<br/>JVM Heap<br/>thread<br/>Service Methods<br/>Cassandra<br/>Kafka<br/>Hikari Connection Pool<br/>Database Methods<br/><br/>**Metrics**<br/>timer<br/>gauges<br/>counters<br/>distribution summaries<br/>long task timers<br/><br/>**Supported observability systems**<br/>Prometheus<br/>JMX |
| | Logging | SpringPR | Out-of-box | Slf4j over Log4j2<br/><br/>Correlation ID<br/>Cassandra<br/>HttpClient<br/>JDBC Connection Pool<br/>kafka-clients |
| | Distributed Tracing | SpringPR | Out-of-box | Micrometer over Zipkin Brave<br/><br/>**Instrumentation**<br/>JVM<br/>Cassandra<br/>HttpClient<br/>JDBC Connection Pool<br/>jms<br/>kafka-clients<br/>servlet<br/>spring-webmvc<br/>Spring REST<br/>spring-beans |
| | Distributed Tracing | SpringPR | Out-of-box | Micrometer over Zipkin Brave<br/><br/>**Instrumentation**<br/>JVM<br/>Cassandra<br/>HttpClient<br/>JDBC Connection Pool<br/>jms<br/>kafka-clients<br/>servlet<br/>spring-webmvc<br/>Spring REST<br/>spring-beans |
| | Health Checks | SpringPR | Out-of-box | Vault<br/>Cassandra<br/>Kafka<br/>PostgresSQL<br/> |
| **Managability** | Configuration | Spring Boot | Out-of-box | Externalized Configuration(covering default and customizable mechanisms for production, dev, test)<br/><br/>1. Default properties(specified by setting `SpringApplication.setDefaultProperties`)<br/>2. @PropertySource annotations on your @Configuration classes<br/>3. Config data (such as application.properties files)<br/>4. A RandomValuePropertySource that has properties only in random.*.<br/>5. OS environment variables<br/>6. Java System properties (System.getProperties())<br/>7. JNDI attributes from java:comp/env<br/>8. ServletContext init parameters<br/>9. ServletConfig init parameters<br/>10. Properties from SPRING_APPLICATION_JSON<br/>11. Command line arguments<br/>12. properties attribute on your tests. Available on @SpringBootTest and the test annotations for testing a particular slice of your application<br/>13. @DynamicPropertySource annotations in your tests<br/>14. @TestPropertySource annotations on your tests<br/>15. Devtools global settings properties in the $HOME/.config/spring-boot directory when devtools is active |
| | Secrets | SpringPR | Out-of-box | ePaaS Vault Integration |
| | Admin API Endpoints | environment properties | Out-of-box | |
| | | Spring Beans Information | Out-of-box | |
| | | Spring Boot Conditions | Out-of-box | |
| | | Web Endpoints | Out-of-box | |
| | | Dynamic adjustment of logging levels | Out-of-box | |
| **Security** | Encryption | kafka-secure-serializers Integration | Out-of-box | |
| **Relational Database** | PostgreSQL | SpringPR | Out-of-box | |
| | H2 Embedded | Spring Boot | Out-of-box | Local & Server Build Unit Tests |
| | DAO | SpringPR | Out-of-box | Spring |
| | JPA | SpringPR | Out-of-box | Spring Data JPA |
| | Transaction | Spring | Out-of-box | Spring |
| | DAO Exception Hierarchy/Translation | SpringPR | Out-of-box | Spring |
| **Key Value Stores** | Cassandra | Cassandra Repository | Out-of-box | Spring boot Cassandra |
| | | Cassandra Client | Out-of-box | DataStax |
| | | Exception Translation | Out-of-box | Spring Boot Cassandra |
| | |  Customized Cassandra Operation | Out-of-box | Consistency Level<br/>TTL<br/>timeout |
| | |  Batch Operation | SpringPR | Spring boot Cassandra |
| | |  Observability | Out-of-box | Spring boot Cassandra |
| | |  Test Containers | Out-of-box | Spring boot Cassandra |
| **Event Streaming** | Kafka | Asynchronous Producer | Out-of-box | Spring Boot Kafka |
| | | Batch Operation | Out-of-box | Spring Boot Kafka |
| | | Concurrent Kafka Listener | Out-of-box | Spring Boot Kafka |
| | | kafka-secure-serializers Integration | Out-of-box | |
| | | Observability | Out-of-box | Spring Boot Kafka |
| | | Test Containers | Out-of-box | Spring Boot Kafka |
| | | Sophisticated Error Handling & Recovery | Out-of-box | Spring Boot Kafka<br/><br/>Retry Strategies<br/>Backoff Strategies<br/>Dead Letter Queue<br/> |
| | | | | |
| **OTHER OUT-OF-BOX FEATURES** | | | | |
| **Dependency Management** | Maven | SpringPR | Out-of-box | delegating to spring-boot-dependencies |
| **REST API Support** | REST API Implementation | Spring MVC |  1 hour | |
| | REST Request Validation | SpringPR | Out-of-box | |
| | Error Handling | SpringPR | Out-of-box | |
| | Pagination | Spring MVC | Out-of-box | |
| | OpenAPI Documentation | Spring MVC | Out-of-box | |
| | CORS | Spring MVC | Out-of-box | |
| | HATEOAS | Spring MVC | Out-of-box | |
| **REACTIVE API Support** | API | Spring WebFlux |  Out-of-box | |
| | Client | Spring WebClient |  Out-of-box | |
| **Programming Features** | Lombok | SpringPR | Out-of-box | |
| | Service Method Validation | Spring | Out-of-box | |
| | Domain Object Validation | Spring | Out-of-box | |
| | AOP | Spring | Out-of-box | |
| | Expression Language | Spring | Out-of-box | Spring Expression Language (SpEL)<br/>querying and manipulating an object graph at runtime |
| **Development** | Source Code Formatting | SpringPR | Out-of-box | Java<br/>POM<br/>Markdown<br/>gitignore |
| | build information | SpringPR | Out-of-box | build artifact<br/>build time<br/>build version |
| | Auto Restart after code changes | SpringPR | Out-of-box | Spring Boot DevTool |
| **Common Non-functional Features** | Asynchronous Thread Pool | SpringPR | Out-of-box | |
| | Scheduled Task | SpringPR | Out-of-box | |
| | Application Life-cycle Event | SpringPR | Out-of-box | |
| | Transaction Bound Events | SpringPR | Out-of-box | |
| **In-memory Cache** | Caffeine | SpringPR | Out-of-box | |
| **Testing** | JUnit4&5 | Spring | Out-of-box | |
| | Asynchronous System Testing | SpringPR | Out-of-box | Awaitility |
| | Mocking | SpringPR | Out-of-box | Mockito |
| | Test Containers | Spring Boot | Out-of-box | PostgreSql<br/>kafka<br/>Cassandra | |
| **Java** | java 17 | | Out-of-box | |
| | java 11 | | Out-of-box | |
| **Virtual Thread for REST API** | Spring MVC over Tomcat | Spring | Out-of-box | [5 - 10X increase of maximum number of concurrent requests per second](https://blogs.oracle.com/weblogicserver/post/the-promise-of-using-java-virtual-threads-with-oracle-weblogic-server)<br/><br/>[9-10X reduction of response time when number of request increase](https://medium.com/naukri-engineering/virtual-thread-performance-gain-for-microservices-760a08f0b8f3)<br/><br/>[Optimaized solution based on virtual thread achieve performance comparable to a minimalist Netty server](https://medium.com/helidon/helidon-n%C3%ADma-helidon-on-virtual-threads-130bb2ea2088) |
| | | | | |
| | | | | |
| **FEATURES REQUIRED TO-BE-IMPLEMENTED** | | | | |
| **Observability** | Dimensional Metrics | Spring Boot | 1 month | Micrometer over Dynatrace<br/><br/>**Metrics**<br/>timer<br/>gauges<br/>counters<br/>distribution summaries<br/>long task timers |
| | Distributed Tracing | Spring Boot | 1 month | Micrometer over Zipkin Brave<br/><br/>**Instrumentation**<br/>Couchbase<br/>grpc<br/>jms<br/>mongodb<br/>sparkjava<br/> |
| | ELF | ELF Framework Integration | 1 week | |
| **Resiliency** | Readiness Probes | SpringPR | 1 week | Vault<br/>Consul<br/> |
| | Circuit Breaker | Spring Cloud Circuit Breaker | 1 week | Advanced Circuit Breaker feature |
| **Manageability** | Configuration | spring-cloud-consul | 1 week | Remote Configuration over Consul<br/><br/>Spring Cloud Consul<br/>Dynamic Configuration Properties loading (@RefreshScope)<br>Properties default /Overwrite |
| **Security** | Encryption | Category based PII Data Encryption | 2 months | PostgreSQL<br/>Cassandra |
| | | Key Based Hashing | 1 month | |
| | Authentication | IDaaS | 1 week - 1 month | IDaaS Admin |
| | | JWT | 1 week - 1 month | WebClient Integration |
| **Relational Database** | Oracle | Spring Boot | 1 day | |
| | MySQL | Spring Boot | 1 week | |
| **Key Value Stores** | Couchbase | Spring Boot Couchbase | 1 week - 1 month | |
| | Reactive Couchbase | Spring Boot Couchbase Reactive | 1 week - 1 month | |
| | Redis | Spring Boot Redis | 1 week - 1 month | |
| | Reactive Redis | Spring Boot Redis Reactive | 1 week - 1 month | |
| | Reactive Cassandra | Spring Boot Cassandra Reactive | 1 week - 1 month | |
| | | | | |
| **OTHER FEATURES TO-BE-IMPLEMENTED** | | | | |
| **Testing** | Test Containers | Spring Boot | 2 weeks | Couchbase<br/>Redis |
| **Dependency Management** | Gradle | SpringPR | 1 month | delegating to spring-boot-dependencies |
| **Java** | java 8 | | 1 mouth - 6 months | |
| | java 21 | | 1 week | September 2023 |
| **MongoDB** | | Spring Boot Mongodb | 1 week - 1 month | |
| **Reactive MongoDB** | | Spring Boot Mongodb Reactive | 1 week - 1 month | |
| **Elasticsearch** | | Spring Boot Elasticsarch | 1 week - 1 month | |
| **GraphQL** | | Spring for GraphQL | 1 week - 1 month | |
| | | | | |
| | | | | |
| **EFFORT FOR APPLICATION TEAMS** | | | | |
| **Resiliency** | Readiness Probes | SpringPR | 1 day - 1 week | Application specific customization |
| | Circuit Breaker | Spring Retry Circuit Breaker | 1 week | Application specific customized fallback call |
| **Observability** | Dimensional Metrics | Annotation | 30 Minute | applying public method |
| | | Spring AOP | 3 Hours | no change to existing code |
| | Distributed Tracing | Annotation | 30 Minute | applying to public method |
| | | Spring AOP | 3 Hours | no change to existing code |
| | ELF | ELF Integration with Application | 1 - 3 Months | |
| | | | | |
| | | | | |
| **FEATURES NOT IMMEDIATE NEED** | | | | |
| **Observability** | Dimensional Metrics | Spring Boot | 1 month | Micrometer<br/><br/>**Metrics**<br/>timer<br/>gauges<br/>counters<br/>distribution summaries<br/>long task timers<br/><br/>**Supported observability systems**<br/>Elastic<br/>Graphite<br/>OpenTelemetry |
| | Distributed Tracing | Spring Boot | 1 month | <br/>Micrometer over otel (for languages other than Java )<br/><br/>**Instrumentation**<br/>Cassandra<br/>Couchbase<br/>grpc<br/>HttpClient<br/>kafka-clients<br/>mongodb<br/>sparkjava<br/> |
| | logging | Logback | 1 day | Spring Boot Logback starter |
| | | | | |
| | | | | |
| **In-memory Cache** | EhCache | Spring | 1 day | |
| | Redis | Spring Boot | 1 week | |
| | Couchbase | Spring Boot | 1 week | |
| **GraalVM** | | Spring GraalVM Native Image Support | 1 month - 3 months | |
| **Testing** | Test Containers | Spring Boot | 1 month | MongoDB<br/>elasticsearch<br/>Vault |
| | | | | |
| | | | | |

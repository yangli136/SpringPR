> README FILE

---

## Protobuf Installation - optional

```
 brew install protobuf
 protoc --version
 where proto
  ```

## Configure Java trust store to include self-signed certificates

**configure and use a trust store with SpringPR self sign certificates.**

In order to connect to ELF receivers (OpenTelementry endpoints)

You can find trust store under setup/cacerts_CertaaSOnDemandNonProdIssuingCAII
put it in a directory (e.g. ~/.java_certs/) then set up a system property: JAVA_CERTS

```
export JAVA_CERTS=~/.java_certs/cacerts_CertaaSOnDemandNonProdIssuingCAII
```

${JAVA_CERTS} is used by spring-boot-maven-plugin and maven-surefire-plugin defined in springpr-parent pom.

## Spring Boot based Paved Road - gRPC Module

This project is a base module that provides common out-of-box non-functional features from Spring Boot.

### Build and Run Instructions

#### formatting code

under directory springpr-grpc

```
mvn spotless:apply
```

#### build the project as library that may be used in Maven as dependency

```
mvn -Plib clean install
```

#### Start gRPC sever locally

change to directory springpr-common/springpr-common-exmaple

```
mvn -P'grpc-server,!lib' spring-boot:run --define spring-boot.run.arguments="--spring.profiles.active=local-e0,e0"
```

--define spring-boot.run.arguments="--spring.profiles.active=e0" is used for Spring DevTools to restart application with correct Spring Boot profiles.

#### Checking the application

```
http://localhost:9090/springpr-common-example/welcome
```

#### Start gRPC client locally

change to directory springpr-common/springpr-common-exmaple

```
mvn -P'grpc-client,!lib' spring-boot:run  --define spring-boot.run.arguments="--spring.profiles.active=local-e0,e0,client-e0"
```

#### Checking the application

```
http://localhost:9091/springpr-common-example/welcome
```

#### Test client

```
http://localhost:9091/hello/Allen
```

---

## 🏆 Contributing

See [contributing guidelines](./CONTRIBUTING.md)

## Custodians

- **Lead Maintainer:** [Yang Li](mailto:Yang.Li@springpr.dev)


### Validating Simple Service

call Simple service:

```
grpcurl -plaintext -d '{"name": "abc"}' localhost:9090 Simple/SayHello
```

result:

```
{
  "message": "Hello ==\u003e abc"
}
```

## Generating Java Classes Manually directly from protobuf

```
brew install protobuf
brew install protoc-gen-grpc-java

protoc --version


protoc --proto_path=./ --java_out=./src/main/java --grpc-java_out=./src/main/java my_service.proto


protoc --proto_path=./ --java_out=./java-generated --grpc-java_out=./java-generated proto/addressbook/addressbook.proto

protoc --proto_path=./ --java_out=./java-generated --grpc-java_out=./java-generated proto/service/service.proto

protoc --proto_path=./ --java_out=./java-generated --grpc-java_out=./java-generated proto/service/service2.proto

```

### Reflection Service for API Docuementation

all available gRPC services:

```
grpcurl -plaintext localhost:9090 list
```

result:

```
Simple
grpc.health.v1.Health
grpc.reflection.v1.ServerReflection
```

details of a service

```
grpcurl -plaintext localhost:9090 describe grpc.reflection.v1.ServerReflection
```

result:

```
service ServerReflection {
  rpc ServerReflectionInfo ( stream .grpc.reflection.v1.ServerReflectionRequest ) returns ( stream .grpc.reflection.v1.ServerReflectionResponse );
}
```

details of a method:

```
grpcurl -plaintext localhost:9090 describe grpc.reflection.v1.ServerReflection.ServerReflectionInfo
```

```
grpc.reflection.v1.ServerReflection.ServerReflectionInfo is a method:
rpc ServerReflectionInfo ( stream .grpc.reflection.v1.ServerReflectionRequest ) returns ( stream .grpc.reflection.v1.ServerReflectionResponse );
```

```
grpcurl -plaintext localhost:9090 describe grpc.reflection.v1.ServerReflectionRequest
```

result:

```
message ServerReflectionRequest {
  string host = 1;
  oneof message_request {
    string file_by_filename = 3;
    string file_containing_symbol = 4;
    .grpc.reflection.v1.ExtensionRequest file_containing_extension = 5;
    string all_extension_numbers_of_type = 6;
    string list_services = 7;
  }
}
```

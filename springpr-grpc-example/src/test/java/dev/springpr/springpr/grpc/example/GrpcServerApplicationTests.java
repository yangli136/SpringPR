/* (C)2025 */
package dev.springpr.springpr.grpc.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod;
import org.springframework.test.annotation.DirtiesContext;

import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.grpc.example.hello.proto.HelloReply;
import dev.springpr.springpr.grpc.example.hello.proto.HelloRequest;
import dev.springpr.springpr.grpc.example.hello.proto.SimpleGrpc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        properties = {
            "spring.grpc.server.servlet.enabled=false",
            "spring.grpc.server.host=0.0.0.0",
            "spring.grpc.server.port=9090",
            "spring.grpc.server.enabled=true",
            "spring.grpc.client.default-channel.address=0.0.0.0:9090"
        },
        useMainMethod = UseMainMethod.ALWAYS)
// @ActiveProfiles(profiles = "client-e0")
@DirtiesContext
@Slf4j
public class GrpcServerApplicationTests {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run();
    }

    @Autowired private SimpleGrpc.SimpleBlockingStub stub;

    @Test
    void contextLoads() {}

    @Test
    void serverResponds() {
        log.info("Testing");
        HelloReply response = stub.sayHello(HelloRequest.newBuilder().setName("Alien").build());
        assertEquals("Hello ==> Alien", response.getMessage());
    }
}

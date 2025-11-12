/* (C)2025 */
package dev.springpr.springpr.grpc.example;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.grpc.client.GrpcChannelFactory;
import org.springframework.grpc.test.LocalGrpcPort;
import org.springframework.test.annotation.DirtiesContext;

import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.grpc.example.hello.proto.HelloReply;
import dev.springpr.springpr.grpc.example.hello.proto.HelloRequest;
import dev.springpr.springpr.grpc.example.hello.proto.SimpleGrpc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.grpc.server.servlet.enabled=false", "spring.grpc.server.port=0"})
@Slf4j
public class ListenOnTwoPortsTests {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class, ExtraConfiguration.class)
                .run(
                        "--spring.grpc.server.servlet.enabled=false",
                        "--spring.grpc.server.port=9091");
    }

    @Autowired private SimpleGrpc.SimpleBlockingStub stub;

    @Test
    @DirtiesContext
    void contextLoads() {}

    @Test
    @DirtiesContext
    void serverResponds() {
        log.info("Testing");
        HelloReply response = stub.sayHello(HelloRequest.newBuilder().setName("Alien").build());
        assertEquals("Hello ==> Alien", response.getMessage());
    }

    @TestConfiguration
    static class ExtraConfiguration {
        @Bean("jwtTokenSupplier")
        Supplier<String> doNothingSupplier() {
            return () -> "EMPTY_TOKE";
        }

        @Bean
        @Lazy
        SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels, @LocalGrpcPort int port) {
            log.info("LocalGrpcPort:{}", port);
            return SimpleGrpc.newBlockingStub(channels.createChannel("0.0.0.0:" + port));
        }
    }
}

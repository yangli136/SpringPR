/* (C)2025 */
package dev.springpr.springpr.grpc.example.hello.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.grpc.client.GrpcChannelFactory;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.reflection.v1.ServerReflectionGrpc;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.grpc.example.hello.proto.SimpleGrpc;

@Configuration
@Profile("client-e0")
@Slf4j
public class LocalGrpcHelloClientConfiguration {
    @Value("${spring.grpc.client.channels.local.address}")
    private String localAddress;

    @Bean
    @Primary
    Channel localChannel(GrpcChannelFactory grpcChannelFactory) {
        log.info("Channel localAddress:{}", localAddress);
        ManagedChannel originChannel = grpcChannelFactory.createChannel(localAddress);
        return originChannel;
    }

    @Bean
    SimpleGrpc.SimpleBlockingV2Stub SimpleBlockingV2Stub(Channel channel) {
        return SimpleGrpc.newBlockingV2Stub(channel);
    }

    @Bean
    ServerReflectionGrpc.ServerReflectionStub serverReflectionStub(Channel channel) {
        return ServerReflectionGrpc.newStub(channel);
    }

    //    @Bean
    SimpleGrpc.SimpleBlockingV2Stub simpleBlockingStub(GrpcChannelFactory channels) {
        return SimpleGrpc.newBlockingV2Stub(channels.createChannel("local"));
    }
}

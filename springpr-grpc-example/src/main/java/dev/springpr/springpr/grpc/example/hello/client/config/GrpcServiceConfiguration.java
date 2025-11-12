/* (C)2025 */
package dev.springpr.springpr.grpc.example.hello.client.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.server.ServerServiceDefinitionFilter;

import io.grpc.health.v1.HealthGrpc;
import io.grpc.reflection.v1.ServerReflectionGrpc;

import dev.springpr.springpr.grpc.example.hello.service.GrpcHelloService;

@Configuration
public class GrpcServiceConfiguration {
    @Bean
    ServerServiceDefinitionFilter grpcServiceFilter() {
        return (serviceDefinition, __) ->
                !Set.of(
                                GrpcHelloService.SERVICE_NAME,
                                HealthGrpc.SERVICE_NAME,
                                ServerReflectionGrpc.SERVICE_NAME)
                        .contains(serviceDefinition.getServiceDescriptor().getName());
    }
}

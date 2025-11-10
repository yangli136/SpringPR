/* (C)2025 */
package org.springpr.springpr.grpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.server.GlobalServerInterceptor;

import io.grpc.ServerInterceptor;

@Configuration
public class ServerConfiguration {

    @Bean
    @Order(100)
    @GlobalServerInterceptor
    ServerInterceptor globalLoggingInterceptor() {
        return new HeaderServerInterceptor();
    }

    @Bean
    CustomGlobalServerInterceptor globalServerInterceptor() {
        return new CustomGlobalServerInterceptor();
    }

    @Bean
    DefaultGrpcExceptionHandler defaultGrpcExceptionHandler() {
        return new DefaultGrpcExceptionHandler();
    }
}

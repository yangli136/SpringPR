/* (C)2025 */
package org.springpr.springpr.grpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.server.GlobalServerInterceptor;

import io.grpc.ServerInterceptor;

@Configuration
public class CustomLoggingInterceptor {

    @Bean
    @Order(100)
    @GlobalServerInterceptor
    ServerInterceptor myGlobalLoggingInterceptor() {
        return new HeaderServerInterceptor();
    }
}

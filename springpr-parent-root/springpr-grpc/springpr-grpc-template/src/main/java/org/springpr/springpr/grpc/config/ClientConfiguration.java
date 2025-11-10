/* (C)2025 */
package org.springpr.springpr.grpc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.client.GrpcChannelBuilderCustomizer;

import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Profile("client-e0 | client-e1")
@Slf4j
public class ClientConfiguration {
    @Value("${spring.grpc.client.channels.local.address}")
    private String localAddress;

    @Bean
    @Order(200)
    <T extends ManagedChannelBuilder<T>> GrpcChannelBuilderCustomizer<T> retryChannelCustomizer() {
        return (name, builder) -> builder.enableRetry().maxRetryAttempts(5);
    }

    //       @Qualifier("jwtTokenSupplier") private final Supplier<String> tokenSupplier;

    //    @Bean
    //    Channel localChannel(GrpcChannelFactory grpcChannelFactory) {
    //        log.info("Channel localAddress:{}", localAddress);
    //        ClientInterceptor interceptor = new SampleHeaderClientInterceptor();
    //        ManagedChannel originChannel = grpcChannelFactory.createChannel(localAddress);
    //        Channel channel = ClientInterceptors.intercept(originChannel, interceptor);
    //        return channel;
    //    }

    //    @Bean
    //    Channel localChannel(GrpcChannelFactory grpcChannelFactory) {
    //        log.info("Channel localAddress:{}", localAddress);
    //        ClientInterceptor headerClientInterceptor = new SampleHeaderClientInterceptor();
    //        ClientInterceptor bearerTokenAuthenticationInterceptor =
    //                new JwtBearerHeaderClientInterceptor(tokenSupplier);
    //        ManagedChannel originChannel = grpcChannelFactory.createChannel(localAddress);
    //        Channel channel =
    //                ClientInterceptors.intercept(
    //                        originChannel,
    //                        List.of(headerClientInterceptor,
    // bearerTokenAuthenticationInterceptor));
    //        return channel;
    //    }

    //    https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html#oauth2-client
    //    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
    //    spring:
    //    	  security:
    //    	    oauth2:
    //    	      client:
    //    	        registration:
    //    	          my-oauth2-client:
    //    	            provider: my-auth-server
    //    	            client-id: my-client-id
    //    	            client-secret: my-client-secret
    //    	            authorization-grant-type: authorization_code
    //    	            scope: message.read,message.write
    //    	        provider:
    //    	          my-auth-server:
    //    	            issuer-uri: https://my-auth-server.com
    //    @Bean
    //    @Lazy
    //    SimpleGrpc.SimpleBlockingStub basic(GrpcChannelFactory channels,
    // ClientRegistrationRepository registry) {
    //    	ClientRegistration reg = registry.findByRegistrationId("spring");
    //    	return SimpleGrpc.newBlockingStub(channels.createChannel("0.0.0.0:9090",
    // ChannelBuilderOptions.defaults()
    //    		.withInterceptors(List.of(new BearerTokenAuthenticationInterceptor(() ->
    // token(reg))))));
    //    }
    //
    //    private String token(ClientRegistration reg) {
    //    	RestClientClientCredentialsTokenResponseClient creds = new
    // RestClientClientCredentialsTokenResponseClient();
    //    	String token = creds.getTokenResponse(new OAuth2ClientCredentialsGrantRequest(reg))
    //    		.getAccessToken()
    //    		.getTokenValue();
    //    	return token;
    //    }
}

/* (C)2025 */
package dev.springpr.springpr.grpc.example.hello.client.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.grpc.client.ChannelBuilderOptions;
import org.springframework.grpc.client.GrpcChannelFactory;
import org.springframework.grpc.client.interceptor.security.BearerTokenAuthenticationInterceptor;
import org.springframework.security.oauth2.client.endpoint.OAuth2ClientCredentialsGrantRequest;
import org.springframework.security.oauth2.client.endpoint.RestClientClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.grpc.example.hello.proto.SimpleGrpc;

@Configuration
@Profile("remote-e1")
@Slf4j
public class RemoteGrpcHelloClientConfiguration {

    // OAuth 2 Client
    @Bean
    @Lazy
    SimpleGrpc.SimpleBlockingStub basic(
            GrpcChannelFactory channels, ClientRegistrationRepository registry) {
        ClientRegistration reg = registry.findByRegistrationId("springpr");
        return SimpleGrpc.newBlockingStub(
                channels.createChannel(
                        "local",
                        ChannelBuilderOptions.defaults()
                                .withInterceptors(
                                        List.of(
                                                new BearerTokenAuthenticationInterceptor(
                                                        () -> token(reg))))));
    }

    private String token(ClientRegistration reg) {
        RestClientClientCredentialsTokenResponseClient creds =
                new RestClientClientCredentialsTokenResponseClient();
        String token =
                creds.getTokenResponse(new OAuth2ClientCredentialsGrantRequest(reg))
                        .getAccessToken()
                        .getTokenValue();
        return token;
    }
}

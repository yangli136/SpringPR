/* (C)2023 */
package dev.springpr.springpr.grpc.example;

import org.apache.coyote.UpgradeProtocol;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.SpringPrBaseApplicationWebServerInitializedListener;
import dev.springpr.springpr.base.config.SpringPrBaseMetricsServiceCounterConfiguration;

@SpringBootApplication(
        // disable Spring Security authentication/authorization of web endpoints
        exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class},
        scanBasePackages = {
            "dev.springpr.springpr.base",
            "dev.springpr.springpr.grpc",
            "dev.springpr.springpr.webclient",
            "io.opentelemetry.instrumentation.spring.autoconfigure"
        })
@ConfigurationPropertiesScan({"dev.springpr.springpr.base", "dev.springpr.springpr.grpc"})
@EnableCaching
@EnableRetry
@EnableScheduling
@OpenAPIDefinition
@PropertySource(ignoreResourceNotFound = true, value = "file:/opt/epaas/vault/secrets/secrets")
@Slf4j
public class Application {
    private static ConfigurableApplicationContext context;

    @SuppressWarnings("squid:S4823")
    public static void main(String[] args) {
        log.info("### ### ### SpringPr Spring Boot gPRC Application starting...");
        SpringApplication app =
                new SpringApplication(
                        Application.class, SpringPrBaseMetricsServiceCounterConfiguration.class);
        app.setApplicationStartup(new BufferingApplicationStartup(2048));
        app.addListeners(new SpringPrBaseApplicationWebServerInitializedListener());
        context = app.run(args);
    }

    @Bean
    TomcatConnectorCustomizer customizer() {
        return (connector) -> {
            for (UpgradeProtocol protocol : connector.findUpgradeProtocols()) {
                if (protocol instanceof Http2Protocol http2Protocol) {
                    http2Protocol.setOverheadWindowUpdateThreshold(0);
                }
            }
        };
    }

    @Bean
    static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propsConfig =
                new PropertySourcesPlaceholderConfigurer();
        propsConfig.setLocation(new ClassPathResource("git.properties"));
        propsConfig.setIgnoreResourceNotFound(true);
        propsConfig.setIgnoreUnresolvablePlaceholders(true);
        return propsConfig;
    }

    public static void restart() {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);

        Thread thread =
                new Thread(
                        () -> {
                            context.close();
                            context =
                                    SpringApplication.run(Application.class, args.getSourceArgs());
                        });

        thread.setDaemon(false);
        thread.start();
    }
}

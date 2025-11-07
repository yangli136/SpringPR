/* (C)2023 */
package org.springpr.springpr.base.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.base.SpringPrBaseApplicationWebServerInitializedListener;
import org.springpr.springpr.base.config.SpringPrBaseMetricsServiceCounterConfiguration;

@SpringBootApplication(scanBasePackages = {"org.springpr.springpr.base"})
@ComponentScan(basePackages = {"io.opentelemetry.instrumentation.spring.autoconfigure"})
@OpenAPIDefinition
@EnableCaching
@EnableRetry
@EnableScheduling
@ConfigurationPropertiesScan({"org.springpr.springpr.base"})
@PropertySource(ignoreResourceNotFound = true, value = "file:/opt/epaas/vault/secrets/secrets")
@Slf4j
public class Application {

    @SuppressWarnings("squid:S4823")
    public static void main(String[] args) {
        log.info("### ### ### SpringPr Spring Boot Base Application starting...");
        SpringApplication app =
                new SpringApplication(
                        Application.class, SpringPrBaseMetricsServiceCounterConfiguration.class);
        app.setApplicationStartup(new BufferingApplicationStartup(2048));
        app.addListeners(new SpringPrBaseApplicationWebServerInitializedListener());
        app.run(args);
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
}

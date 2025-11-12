/* (C)2023 */
package dev.springpr.springpr.serviceinterface.template.employee.internal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.SpringPrBaseApplicationWebServerInitializedListener;
import dev.springpr.springpr.base.config.SpringPrBaseMetricsServiceCounterConfiguration;

@SpringBootApplication
@ComponentScan(
        basePackages = {"dev.springpr.springpr.base", "dev.springpr.springpr.serviceinterface"},
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASPECTJ,
                    pattern = "dev.springpr.springpr.base.internal..*")
        })
@OpenAPIDefinition
@EnableCaching
@EnableRetry
@EnableScheduling
@ConfigurationPropertiesScan({"dev.springpr.springpr.base", "dev.springpr.springpr.serviceinterface"})
@PropertySource(ignoreResourceNotFound = true, value = "file:/opt/epaas/vault/secrets/secrets")
@Slf4j
public class Application {

    @SuppressWarnings("squid:S4823")
    public static void main(String[] args) {
        log.info("### ### ### SpringPr Spring Boot Rest Interface Application starting...");
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

    // To have the @Observed support we need to register this aspect
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }
}

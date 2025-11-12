/* (C)2023 */
package dev.springpr.springpr.rest.example.employee.internal;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.SpringPrBaseApplicationWebServerInitializedListener;
import dev.springpr.springpr.base.config.SpringPrBaseMetricsServiceCounterConfiguration;

@SpringBootApplication
@ComponentScan(
        basePackages = {"dev.springpr.springpr.base", "dev.springpr.springpr.rest"},
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
    private static ConfigurableApplicationContext context;

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

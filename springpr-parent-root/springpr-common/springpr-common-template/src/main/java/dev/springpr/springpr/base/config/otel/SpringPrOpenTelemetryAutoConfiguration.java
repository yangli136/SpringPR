/* (C)2024 */
package dev.springpr.springpr.base.config.otel;

import org.springdoc.core.discoverer.SpringDocParameterNameDiscoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterNameDiscoverer;

// @Configuration
public class SpringPrOpenTelemetryAutoConfiguration {

    @Bean
    @Primary
    ParameterNameDiscoverer parameterNameDiscoverer() {
        return new SpringDocParameterNameDiscoverer();
    }
}

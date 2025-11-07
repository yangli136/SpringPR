/* (C)2023 */
package org.springpr.springpr.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class SpringPrBaseMetricsServiceCounterConfiguration {

    private static final String CAT = "cat";
    private static final String SERVICE = "service";

    @Bean
    Counter userErrorCounter(MeterRegistry meterRegistry) {
        return meterRegistry.counter("user.id.error", CAT, SERVICE);
    }

    @Bean
    Counter userAccessCounter(MeterRegistry meterRegistry) {
        return meterRegistry.counter("user.id.access", CAT, SERVICE);
    }
}

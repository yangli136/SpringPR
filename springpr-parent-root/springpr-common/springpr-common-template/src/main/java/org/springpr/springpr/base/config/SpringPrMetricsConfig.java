/* (C)2023 */
package org.springpr.springpr.base.config;

// import java.util.Arrays;
// import java.util.Objects;
// import java.util.stream.Collectors;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.NamingConvention;
import io.prometheus.metrics.model.snapshots.PrometheusNaming;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SpringPrMetricsConfig {
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(String applicationName) {
        return registry ->
                registry.config()
                        .namingConvention(
                                new NamingConvention() {
                                    @Override
                                    public String name(
                                            String name, Meter.Type type, String baseUnit) {
                                        final String sanitizedName =
                                                PrometheusNaming.sanitizeMetricName(name);
                                        log.trace(
                                                "%%% name:{}, sanitizedName:{}, type:{},"
                                                        + " baseUnit:{}",
                                                name, sanitizedName, type, baseUnit);
                                        return sanitizedName;
                                        //            return "PREFIX" +
                                        // Arrays.stream(name.split("\\."))
                                        //                .filter(Objects::nonNull)
                                        //                .collect(Collectors.joining("_"));
                                    }
                                });
    }
}

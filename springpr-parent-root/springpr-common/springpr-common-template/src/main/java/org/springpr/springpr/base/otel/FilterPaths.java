/* (C)2025 */
package org.springpr.springpr.base.otel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.contrib.sampler.RuleBasedRoutingSampler;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;
import io.opentelemetry.semconv.UrlAttributes;

@Configuration
public class FilterPaths {

    @Bean
    AutoConfigurationCustomizerProvider otelCustomizer() {
        return p ->
                p.addSamplerCustomizer(
                        (fallback, config) ->
                                RuleBasedRoutingSampler.builder(SpanKind.SERVER, fallback)
                                        .drop(UrlAttributes.URL_PATH, "^/actuator")
                                        .build());
    }
}

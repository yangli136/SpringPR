/* (C)2023 */
package org.springpr.springpr.base.retry;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import lombok.RequiredArgsConstructor;

import org.springpr.springpr.base.exception.RecoverableFailureException;

@Configuration
@EnableRetry
@RequiredArgsConstructor
public class RetryTemplateConfiguration {

    @SuppressWarnings("squid:S3749")
    private final RetryTemplateConfigProperties config;

    @SuppressWarnings("squid:S3749")
    private final RetryLoggingListener retryLoggingListener;

    @Bean
    RetryTemplate retryTemplate() {

        //      FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        //      fixedBackOffPolicy.setBackOffPeriod(2000l);
        //      retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        final Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
        exceptionMap.put(RecoverableFailureException.class, true);

        final RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(config.getTotalRetries(), exceptionMap));
        retryTemplate.registerListener(retryLoggingListener);

        final ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(config.getIntialInterval());
        backOffPolicy.setMaxInterval(config.getMaxInterval());
        backOffPolicy.setMultiplier(config.getMaxInterval());
        retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }
}

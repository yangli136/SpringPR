/* (C)2023 */
package org.springpr.springpr.base.retry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.base.config.otel.SpringPrOpenTelemetryAutoConfiguration;
import org.springpr.springpr.base.exception.RecoverableFailureException;

@SpringBootTest(
        classes = {
            RetryLoggingListener.class,
            RetryTemplateConfiguration.class,
            RetryTemplateConfigProperties.class,
            AServiceImpl.class,
            SpringPrOpenTelemetryAutoConfiguration.class
        },
        properties = {
            "springpr.base.retry.intialInterval=10",
            "springpr.base.retry.maxInterval=6000",
            "springpr.base.retry.multiplier=2",
            "springpr.base.retry.totalRetries=6",
            "server.port=8080",
            "otel.instrumentation.annotations.enabled=false"
        })
@EnableAutoConfiguration
@Slf4j
class RetryTemplateTest {

    @Autowired private AService aService;

    @Autowired private RetryTemplate retryTemplate;

    @MockitoBean private BService bService;

    @Test
    void givenTemplateRetryService_whenCallWithException_thenRetry() {
        if (log.isInfoEnabled()) {
            log.info("***************************0");
            log.info("{}", this.retryTemplate.hasListeners());
        }
        Assertions.assertThrows(
                RecoverableFailureException.class,
                () ->
                        retryTemplate.execute(
                                arg0 -> {
                                    log.info("***************************1");
                                    aService.templateRetryService();
                                    return null;
                                }));
    }
}

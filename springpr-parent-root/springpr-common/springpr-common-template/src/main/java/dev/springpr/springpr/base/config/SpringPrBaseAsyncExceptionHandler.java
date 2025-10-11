/* (C)2023 */
package dev.springpr.springpr.base.config;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.exception.RetryExhaustedException;
import dev.springpr.springpr.base.exception.SpringPrApplicationException;
import dev.springpr.springpr.base.exception.SpringPrBaseAlertMessage;

@Service
@EnableAsync
@Slf4j
public class SpringPrBaseAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(final Throwable t, final Method m, final Object... params) {

        log.info("$$$ $$$ $$$ Method name:{}", m.getName());
        for (Object param : params) {
            log.info("$$$ $$$ $$$ Parameter value:{}", param);
        }
        if (t instanceof RetryExhaustedException) {
            log.error(
                    "{} *** *** *** method:{} failed with recoverable exception and all retries"
                            + " exhausted. Message:{}",
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_RETRY_EXHAUSTED,
                    m,
                    t.getMessage(),
                    t);
        } else if (t instanceof SpringPrApplicationException) {
            log.error(
                    "{} *** *** ***  method:{} falied with Un-recoverable Exception. Message:{}",
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_UN_RECOVERABLE_FAILURE,
                    m,
                    t.getMessage(),
                    t);
        } else {
            log.error(
                    "{} *** *** ***  method:{} failed with Un-categoried Exception. Message:{}",
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_UN_CATEGORIZED_EXCEPTION,
                    m,
                    t.getMessage(),
                    t);
        }
    }
}

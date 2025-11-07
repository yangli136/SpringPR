/* (C)2023 */
package org.springpr.springpr.base.retry;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.util.ReflectionUtils;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RetryListenerConfiguration {

    @Bean
    List<RetryListener> retryListeners() {

        return List.of(
                new RetryListener() {
                    long start;

                    @Override
                    public <T, E extends Throwable> boolean open(
                            RetryContext context, RetryCallback<T, E> callback) {
                        // The 'context.name' attribute has not been set on the context yet. So we
                        // have to use
                        // reflection.
                        Field labelField =
                                ReflectionUtils.findField(callback.getClass(), "val$label");
                        ReflectionUtils.makeAccessible(labelField);
                        String label = (String) ReflectionUtils.getField(labelField, callback);
                        log.trace("Starting retryable method {}", label);
                        log.info("*** *** *** starting retry...");
                        start = System.currentTimeMillis();
                        return true;
                    }

                    @Override
                    public <T, E extends Throwable> void onError(
                            RetryContext context,
                            RetryCallback<T, E> callback,
                            Throwable throwable) {
                        log.warn(
                                "Retryable method {} threw {}th exception {}",
                                context.getAttribute("context.name"),
                                context.getRetryCount(),
                                throwable.toString());
                    }

                    @Override
                    public <T, E extends Throwable> void close(
                            RetryContext context,
                            RetryCallback<T, E> callback,
                            Throwable throwable) {
                        if (log.isTraceEnabled()) {
                            log.trace(
                                    "*** *** *** Finished retryable method {}, it took:{} ms",
                                    context.getAttribute("context.name"),
                                    System.currentTimeMillis() - start);
                        }
                        if (log.isInfoEnabled()) {
                            log.info(
                                    "*** *** *** Finished retryable, it took:{} ms",
                                    System.currentTimeMillis() - start);
                        }
                    }
                });
    }
}

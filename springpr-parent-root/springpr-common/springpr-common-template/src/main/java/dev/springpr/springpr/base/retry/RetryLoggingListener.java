/* (C)2023 */
package dev.springpr.springpr.base.retry;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RetryLoggingListener implements RetryListener {

    @Override
    public <T, E extends Throwable> void close(
            RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        if (context.getRetryCount() > 0) {
            if (log.isInfoEnabled()) {
                log.info(
                        "%%% %%% %%% [{}] retry closing, count:{}",
                        context.getAttribute("context.name"), context.getRetryCount());
            }
        }
        RetryListener.super.close(context, callback, throwable);
    }

    @Override
    public <T, E extends Throwable> void onError(
            RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        if (context.getRetryCount() > 0) {

            if (log.isInfoEnabled()) {
                log.info(
                        "%%% %%% %%% [{}] retry onError, count: {}{}, exception type:{}",
                        context.getAttribute("context.name"),
                        context.getRetryCount(),
                        throwable.getClass(),
                        throwable);
            }
        }
        RetryListener.super.onError(context, callback, throwable);
    }

    @Override
    public <T, E extends Throwable> boolean open(
            RetryContext context, RetryCallback<T, E> callback) {
        log.debug("%%% %%% %%%, retry opening...");
        return RetryListener.super.open(context, callback);
    }
}

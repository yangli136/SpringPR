/* (C)2023 */
package dev.springpr.springpr.base.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.exception.RecoverableFailureException;

@Service
@Slf4j
public class AServiceImpl implements AService {

    private final BService bService;

    public AServiceImpl(BService bService) {
        this.bService = bService;
    }

    @Override
    @Retryable(
            retryFor = {RecoverableFailureException.class},
            maxAttempts = 6,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 1800000),
            listeners = {"retryLoggingListener"})
    public String aFind(String name) {
        return this.bService.bFind(name);
    }

    @Override
    public void templateRetryService() {
        log.info("throw RecoverableFailureException in method templateRetryService()");
        throw new RecoverableFailureException("Retry Test");
    }
}

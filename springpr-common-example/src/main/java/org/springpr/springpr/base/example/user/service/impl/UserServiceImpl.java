/* (C)2023 */
package org.springpr.springpr.base.example.user.service.impl;

import java.lang.ref.SoftReference;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.observation.annotation.Observed;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.base.example.user.model.User;
import org.springpr.springpr.base.example.user.service.UserService;
import org.springpr.springpr.base.logging.Log4jDiagnosticContextEnable;
import org.springpr.springpr.base.stereotype.ValidatedService;

@ValidatedService
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @SuppressWarnings("squid:S3749")
    private final SecureRandom random;

    @Autowired
    @SuppressWarnings("squid:S3749")
    private final Counter userAccessCounter;

    private SoftReference<Map<Long, User>> userMapRef = new SoftReference<>(new HashMap<>());

    @Timed(
            extraTags = {"cat", "service"},
            percentiles = {0.50, 0.95, 0.99},
            histogram = false)
    @Log4jDiagnosticContextEnable
    @Observed(
            name = "UserServiceImpl",
            contextualName = "userService",
            lowCardinalityKeyValues = {"operation", "get"})
    @WithSpan
    @Override
    public String getAccount(@NotNull final User user) {
        userAccessCounter.increment();
        String account = user.getId() + "-" + random.nextInt(100000000);
        log.info("account:{}", account);
        return account;
    }

    /*
     * Example of asynchronous execution
     */
    @Async
    @Timed(
            extraTags = {"cat", "service"},
            percentiles = {0.50, 0.95, 0.99},
            histogram = false)
    @Log4jDiagnosticContextEnable
    @Override
    public CompletableFuture<String> getIdAndName(@NotNull final User user) {
        userAccessCounter.increment();
        return CompletableFuture.completedFuture(user.getId() + "-" + user.getName());
    }

    /*
     * Example of handling AsyncUncaughtExceptionHandler
     */
    @Async
    @Timed(
            extraTags = {"cat", "service"},
            percentiles = {0.50, 0.95, 0.99},
            histogram = false)
    @Log4jDiagnosticContextEnable
    @Override
    public void asyncFailure(final User user) {
        userAccessCounter.increment();
        throw new RuntimeException("TEST EXCEPTION");
    }

    @Timed(
            extraTags = {"cat", "service"},
            percentiles = {0.50, 0.95, 0.99},
            histogram = false)
    @Log4jDiagnosticContextEnable
    @Override
    public synchronized User getUser(long id) {
        Map<Long, User> userMap = this.getUserMap();
        User user = userMap.get(id);
        if (user == null) {
            user = createUser(id);
        }

        log.info("user:{}", user);

        return user;
    }

    @Timed(
            extraTags = {"cat", "service"},
            percentiles = {0.50, 0.95, 0.99},
            histogram = false)
    @Log4jDiagnosticContextEnable
    @Observed(
            name = "userService",
            contextualName = "updateUser",
            lowCardinalityKeyValues = {"operation", "update"})
    @WithSpan
    @Override
    public synchronized User updateUser(long id) {
        Map<Long, User> userMap = this.getUserMap();
        User user = userMap.get(id);
        if (user == null) {
            user = createUser(id);
        } else {
            user.setName(String.valueOf(random.nextInt(100)));
        }

        log.info("updated user:{}", user);
        return user;
    }

    private Map<Long, User> getUserMap() {
        Map<Long, User> userMap = this.userMapRef.get();
        if (userMap == null) {
            this.userMapRef = new SoftReference<>(new ConcurrentHashMap<>());
        }
        return userMapRef.get();
    }

    private User createUser(long id) {
        User user;
        user = new User();
        user.setId(id);
        user.setName(String.valueOf(random.nextInt(100)));
        return user;
    }
}

/* (C)2023 */
package org.springpr.springpr.base.example.user.web;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.base.example.user.model.User;
import org.springpr.springpr.base.example.user.service.UserService;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @SuppressWarnings("squid:S3749")
    @Qualifier("userErrorCounter") private final Counter userErrorCounter;

    @SuppressWarnings("squid:S3749")
    private final UserService userService;

    @Observed(
            name = "userController",
            contextualName = "user",
            lowCardinalityKeyValues = {"operation", "get"})
    @GetMapping(value = "/user/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String user(@PathVariable(value = "id") @Min(1) final Long id) {
        User user = new User();
        user.setId(id);
        user.setName("name");
        String account = this.userService.getAccount(user);
        log.info("PaaS Demo App running: User[{}], accout[{}]", user.getId(), account);
        return "<h1>SpringPR Base App running: User["
                + user.getId()
                + "], accout["
                + account
                + "]</h1>";
    }

    /*
     * Example of optional parameters for get request
     */
    @GetMapping(value = "/user", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    @SuppressWarnings("squid:S1192")
    public String sendMessageThroughKafkaSender(
            @RequestParam("id") Optional<Long> id, @RequestParam("name") Optional<String> name)
            throws InterruptedException, ExecutionException, TimeoutException {
        User user = new User();
        user.setId(id.orElse(1L));
        user.setName(name.orElse("name"));
        if (id.orElse(1L) > 0) {
            Future<String> idAndName = this.userService.getIdAndName(user);
            log.info(
                    "SpringPR Base App running: User[{}], idAndName[{}]",
                    user.getId(),
                    idAndName.get(1, TimeUnit.SECONDS));
            return "<h1>SpringPR Base App running: User["
                    + user.getId()
                    + "], idAndName["
                    + idAndName.get()
                    + "]</h1>";
        } else if (id.orElse(1L) == 0) {
            // check exception
            CompletableFuture<String> result = this.userService.getIdAndName(new User());

            result.handle(
                    (rs, ex) -> {
                        if (rs != null) {
                            log.info("**** -> successfully received:{}", rs);
                            return rs;
                        } else {
                            log.info("**** -> Service call failed.", ex);
                            throw new RuntimeException("Service call failed:", ex);
                        }
                    });

            return "<h1>" + result.get() + "</h1>";
        } else {
            // check Async Uncaught Exception
            this.userService.asyncFailure(null);
            return "FAILED";
        }
    }

    @Cacheable(value = "springPrDefaultCache")
    @GetMapping(value = "/cache/get/user/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getUserFromCache(@PathVariable(value = "id") @Min(1) final Long id) {
        User user = this.userService.getUser(id);
        return "<h1>SpringPR Base App running: getUser["
                + user.getId()
                + "], name["
                + user.getName()
                + "]</h1>";
    }

    @CachePut(cacheNames = "springPrDefaultCache", key = "#id")
    @GetMapping(value = "/cache/put/user/{id}", produces = MediaType.TEXT_HTML_VALUE)
    @Observed(
            name = "userController",
            contextualName = "updateUserInCache",
            lowCardinalityKeyValues = {"operation", "update"})
    public String updateUserInCache(@PathVariable(value = "id") @Min(1) final Long id) {
        User user = this.userService.updateUser(id);
        return "<h1>SpringPR Base App running: getUser["
                + user.getId()
                + "], name["
                + user.getName()
                + "]</h1>";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        userErrorCounter.increment();
        return new ResponseEntity<>(
                "Not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

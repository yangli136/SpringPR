/* (C)2023 */
package dev.springpr.springpr.rest.example.employee.web;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.observation.annotation.Observed;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    @CrossOrigin(maxAge = 3600)
    @Observed(
            name = "userController",
            contextualName = "user",
            lowCardinalityKeyValues = {"operation", "get"})
    @GetMapping(value = "/user/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String user(@PathVariable(value = "id") @Min(1) final Long id) {
        String account = "new account";
        log.info("PaaS Demo App running: accout[{}]", id, account);
        return "<h1>SpringPR Base App running: User[" + id + "], accout[" + account + "]</h1>";
    }
}

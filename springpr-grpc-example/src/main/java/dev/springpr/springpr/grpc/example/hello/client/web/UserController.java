/* (C)2023 */
package dev.springpr.springpr.grpc.example.hello.client.web;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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
public class UserController {

    @Observed(
            name = "userController",
            contextualName = "user",
            lowCardinalityKeyValues = {"operation", "get"})
    @GetMapping(value = "/user/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String user(@PathVariable() @Min(1) final Long id) {
        return "<h1>SpringPR Base App running: User[" + id + "]</h1>";
    }
}

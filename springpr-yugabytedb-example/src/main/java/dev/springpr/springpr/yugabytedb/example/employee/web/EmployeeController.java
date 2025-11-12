/* (C)2023 */
package dev.springpr.springpr.yugabytedb.example.employee.web;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.exception.RecoverableFailureException;
import dev.springpr.springpr.base.stereotype.ValidationGroup;
import dev.springpr.springpr.yugabytedb.example.Application;
import dev.springpr.springpr.yugabytedb.example.employee.model.Employee;
import dev.springpr.springpr.yugabytedb.example.service.EmployeeService;

@RestController
@RequestMapping(path = "/api/v1/employees")
@Validated
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Observed(
            name = "webclient.employee",
            contextualName = "saveEmployee",
            lowCardinalityKeyValues = {"userType", "userType6"})
    @Retryable(
            retryFor = {RecoverableFailureException.class},
            maxAttempts = 6,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 1800000),
            listeners = {"retryLoggingListener"})
    public ResponseEntity<EmployeeDto> addEmployee(
            @Validated(ValidationGroup.OnCreate.class) @RequestBody EmployeeDto employeeDto) {
        log.info("endpoint[post /api/v1/employees] - creating employee:{}", employeeDto);
        final Employee employee = new Employee();
        log.info("employeeDto:{}", employeeDto);
        BeanUtils.copyProperties(employeeDto, employee);
        employeeService.save(employee);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getById(@PathVariable String id) {
        final Optional<Employee> employeeOptinal = employeeService.getById(id);
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employeeOptinal.get(), employeeDto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PostMapping("/restart")
    public ResponseEntity<String> restart() {
        Application.restart();
        return ResponseEntity.ok("success.");
    }
}

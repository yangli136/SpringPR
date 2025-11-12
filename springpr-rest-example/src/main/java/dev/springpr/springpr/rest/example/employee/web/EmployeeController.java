/* (C)2025 */
package dev.springpr.springpr.rest.example.employee.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.stereotype.ValidationGroup;
import dev.springpr.springpr.rest.example.employee.service.dto.EmployeeDto;

@RestController
@RequestMapping(path = "/api/v1/employees")
@Validated
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto getEmployeeRestClient(
            @RequestHeader HttpHeaders headers, @PathVariable String id) {
        log.info(
                "endpoint[get /api/v1/employees/{id}] - finding employee with id:{}, headers:{}",
                id,
                headers);
        final EmployeeDto dto = new EmployeeDto();
        dto.setBand(40);
        dto.setEmail("email" + id);
        dto.setId(id);
        dto.setName("name" + id);
        dto.setOrder(10);
        return dto;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Observed(
            name = "restexample.employee",
            contextualName = "saveEmployee",
            lowCardinalityKeyValues = {"userType", "userType6"})
    public EmployeeDto addEmployee(
            @RequestHeader HttpHeaders headers,
            @Validated(ValidationGroup.OnCreate.class) @RequestBody EmployeeDto employeeDto) {
        log.info(
                "endpoint[post /api/v1/employees] - creating employee:{}, headers:{}",
                employeeDto,
                headers);
        employeeDto.setBand(50);
        return employeeDto;
    }
}

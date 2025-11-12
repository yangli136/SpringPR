/* (C)2025 */
package dev.springpr.springpr.yugabytedb.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.yugabytedb.example.employee.model.Employee;
import dev.springpr.springpr.yugabytedb.example.employee.repository.EmployeeRepository;
import dev.springpr.springpr.yugabytedb.example.service.EmployeeService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> getById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findByEmail(String email) {

        List<Employee> employees = employeeRepository.findByEmail("user@one.com");

        log.info("Query returned: {}", employees);
        return employees;
    }

    @Override
    public void save(Employee employee) {
        //        Employee employee = new Employee("sl1", "User One", "user@one.com");

        employeeRepository.save(employee);
    }
}

/* (C)2025 */
package dev.springpr.springpr.yugabytedb.example.service;

import java.util.List;
import java.util.Optional;

import dev.springpr.springpr.yugabytedb.example.employee.model.Employee;

public interface EmployeeService {

    void save(Employee employee);

    List<Employee> findByEmail(String email);

    Optional<Employee> getById(String id);
}

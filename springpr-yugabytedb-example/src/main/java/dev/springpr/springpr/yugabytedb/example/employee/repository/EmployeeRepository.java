/* (C)2025 */
package dev.springpr.springpr.yugabytedb.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.springpr.springpr.yugabytedb.example.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByEmail(String email);
}

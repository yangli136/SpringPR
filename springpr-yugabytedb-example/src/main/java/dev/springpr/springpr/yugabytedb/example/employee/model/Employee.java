/* (C)2025 */
package dev.springpr.springpr.yugabytedb.example.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Getter @Id @Setter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private String email;

    public Employee(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

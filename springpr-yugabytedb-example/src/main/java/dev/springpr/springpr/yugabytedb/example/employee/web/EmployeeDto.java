/* (C)2023 */
package dev.springpr.springpr.yugabytedb.example.employee.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import dev.springpr.springpr.base.openapi.SwaggerView;
import dev.springpr.springpr.base.stereotype.ValidationGroup;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = false)
public class EmployeeDto extends IdentifiableResourceDto {

    @JsonView(SwaggerView.NonHidden.class)
    @NotBlank(
            message = "Name is mandatory",
            groups = {Default.class, ValidationGroup.OnCreate.class})
    private String name;

    @JsonView(SwaggerView.NonHidden.class)
    @NotBlank(
            message = "Email is mandatory",
            groups = {Default.class, ValidationGroup.OnCreate.class})
    private String email;
}

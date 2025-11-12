/* (C)2024 */
package dev.springpr.springpr.serviceinterface.template.employee.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import dev.springpr.springpr.base.openapi.SwaggerView;
import dev.springpr.springpr.base.stereotype.ValidationGroup;
import dev.springpr.springpr.serviceinterface.resource.model.HiddenIdentifiableResourceDto;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = false)
public class EmployeeHiddenIdDto extends HiddenIdentifiableResourceDto {

    @JsonView(SwaggerView.NonHidden.class)
    @NotBlank(
            message = "Name is mandatory",
            groups = {Default.class, ValidationGroup.OnCreate.class})
    private String name;

    @JsonView(SwaggerView.NonHidden.class)
    @Min(
            value = 20,
            groups = {Default.class, ValidationGroup.OnCreate.class})
    @Max(
            value = 90,
            groups = {Default.class, ValidationGroup.OnCreate.class})
    private int band;

    @JsonView(SwaggerView.NonHidden.class)
    @NotBlank(
            message = "Name is mandatory",
            groups = {Default.class, ValidationGroup.OnCreate.class})
    private String email;
}

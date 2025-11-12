/* (C)2023 */
package dev.springpr.springpr.yugabytedb.example.employee.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;
import lombok.Data;

import dev.springpr.springpr.base.openapi.SwaggerView;
import dev.springpr.springpr.base.stereotype.ValidationGroup;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class IdentifiableResourceDto {
    @JsonView(SwaggerView.NonHidden.class)
    @NotBlank(message = "Id is mandatory.") @NotBlank(
            message = "Id is mandatory.",
            groups = {Default.class, ValidationGroup.OnCreate.class})
    private String id;

    @JsonView(SwaggerView.NonHidden.class)
    @Min(0) @Min(
            value = 0,
            groups = {Default.class, ValidationGroup.OnCreate.class})
    private int order;
}

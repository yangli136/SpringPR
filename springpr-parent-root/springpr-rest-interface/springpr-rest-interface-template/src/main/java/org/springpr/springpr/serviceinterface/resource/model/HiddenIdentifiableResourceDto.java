/* (C)2024 */
package org.springpr.springpr.serviceinterface.resource.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.groups.Default;
import lombok.Data;

import org.springpr.springpr.base.openapi.SwaggerView;
import org.springpr.springpr.base.stereotype.ValidationGroup;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class HiddenIdentifiableResourceDto {
    @JsonView(SwaggerView.Hidden.class)
    @NotBlank(message = "Id is mandatory.") @Null(
            message = "Id should be null when the entity is created.",
            groups = {ValidationGroup.OnCreate.class})
    private String id;

    @JsonView(SwaggerView.NonHidden.class)
    @Min(0) @Min(
            value = 0,
            groups = {Default.class, ValidationGroup.OnCreate.class})
    private int order;
}

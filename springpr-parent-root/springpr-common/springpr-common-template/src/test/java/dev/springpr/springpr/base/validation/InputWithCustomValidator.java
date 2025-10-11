/* (C)2023 */
package dev.springpr.springpr.base.validation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import dev.springpr.springpr.base.stereotype.IpAddress;
import dev.springpr.springpr.base.stereotype.ValidationGroup.OnCreate;
import dev.springpr.springpr.base.stereotype.ValidationGroup.OnUpdate;

@Data
public class InputWithCustomValidator {

    @NotNull(groups = OnUpdate.class) @Null(groups = OnCreate.class) private Long id;

    @Min(1) @Max(10) private int numberBetweenOneAndTen;

    @IpAddress private String ipAddress;
}

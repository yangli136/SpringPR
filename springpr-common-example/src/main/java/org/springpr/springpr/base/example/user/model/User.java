/* (C)2023 */
package org.springpr.springpr.base.example.user.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class User {
    @Min(1) private long id;

    @NotBlank private String name;
}

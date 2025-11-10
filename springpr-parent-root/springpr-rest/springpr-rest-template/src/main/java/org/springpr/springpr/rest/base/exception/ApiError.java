/* (C)2023 */
package org.springpr.springpr.rest.base.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ApiError {
    @Getter @Setter private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }
}

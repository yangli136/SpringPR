/* (C)2023 */
package com.aexp.springpr.rest.base.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class ContentNotAllowedException extends RuntimeException {
    @Getter List<ObjectError> errors;

    public static ContentNotAllowedException createWith(List<ObjectError> errors) {
        return new ContentNotAllowedException(errors);
    }

    private ContentNotAllowedException(List<ObjectError> errors) {
        this.errors = errors;
    }
}

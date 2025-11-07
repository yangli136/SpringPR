/* (C)2023 */
package com.aexp.springpr.rest.base.exception;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.aexp.springpr.base.exception.EntityNotFoundException;

import static java.util.stream.Collectors.toList;

// @ControllerAdvice
public class RestControllerGlobalExceptionHandler {

    /** Provides handling for exceptions throughout this service. */
    @ExceptionHandler({EntityNotFoundException.class, ContentNotAllowedException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof EntityNotFoundException unfe) {
            HttpStatus status = HttpStatus.NOT_FOUND;

            return handleEntityNotFoundException(unfe, headers, status, request);
        }
        if (ex instanceof ContentNotAllowedException cnae) {
            HttpStatus status = HttpStatus.BAD_REQUEST;

            return handleContentNotAllowedException(cnae, headers, status, request);
        }
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /** Customize the response for EntityNotFoundException. */
    protected ResponseEntity<ApiError> handleEntityNotFoundException(
            EntityNotFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = List.of(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    /** Customize the response for ContentNotAllowedException. */
    protected ResponseEntity<ApiError> handleContentNotAllowedException(
            ContentNotAllowedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errorMessages =
                ex.getErrors().stream()
                        .map(
                                contentError ->
                                        contentError.getObjectName()
                                                + " "
                                                + contentError.getDefaultMessage())
                        .collect(toList());

        return handleExceptionInternal(ex, new ApiError(errorMessages), headers, status, request);
    }

    /** A single place to customize the response body of all Exception types. */
    protected ResponseEntity<ApiError> handleExceptionInternal(
            Exception ex,
            ApiError body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}

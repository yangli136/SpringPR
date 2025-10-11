/* (C)2023 */
package dev.springpr.springpr.base.exception;

@SuppressWarnings("serial")
public class ValidationFailureException extends SpringPrApplicationException {

    public ValidationFailureException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ValidationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationFailureException(String code, String message) {
        super(code, message);
    }

    public ValidationFailureException(String message) {
        super(message);
    }
}

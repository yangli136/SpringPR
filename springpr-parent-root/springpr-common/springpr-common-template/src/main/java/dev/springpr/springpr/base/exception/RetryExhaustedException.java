/* (C)2023 */
package dev.springpr.springpr.base.exception;

@SuppressWarnings("serial")
public class RetryExhaustedException extends SpringPrApplicationException {

    public RetryExhaustedException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public RetryExhaustedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetryExhaustedException(String code, String message) {
        super(code, message);
    }

    public RetryExhaustedException(String message) {
        super(message);
    }
}

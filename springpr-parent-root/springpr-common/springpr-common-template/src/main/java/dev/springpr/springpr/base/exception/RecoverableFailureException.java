/* (C)2023 */
package dev.springpr.springpr.base.exception;

@SuppressWarnings("serial")
public class RecoverableFailureException extends SpringPrApplicationException {

    public RecoverableFailureException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public RecoverableFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecoverableFailureException(String code, String message) {
        super(code, message);
    }

    public RecoverableFailureException(String message) {
        super(message);
    }
}

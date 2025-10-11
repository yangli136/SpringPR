/* (C)2023 */
package dev.springpr.springpr.base.exception;

@SuppressWarnings("serial")
public class TransformationFailureException extends SpringPrApplicationException {

    public TransformationFailureException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public TransformationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformationFailureException(String code, String message) {
        super(code, message);
    }

    public TransformationFailureException(String message) {
        super(message);
    }
}

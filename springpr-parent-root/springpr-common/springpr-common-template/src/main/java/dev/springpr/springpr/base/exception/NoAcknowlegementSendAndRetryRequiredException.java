/* (C)2023 */
package dev.springpr.springpr.base.exception;

@SuppressWarnings("serial")
public class NoAcknowlegementSendAndRetryRequiredException extends SpringPrApplicationException {

    public NoAcknowlegementSendAndRetryRequiredException(
            String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public NoAcknowlegementSendAndRetryRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAcknowlegementSendAndRetryRequiredException(String code, String message) {
        super(code, message);
    }

    public NoAcknowlegementSendAndRetryRequiredException(String message) {
        super(message);
    }
}

/* (C)2023 */
package dev.springpr.springpr.base.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class EntityNotFoundException extends SpringPrApplicationException {
    private String id;

    public EntityNotFoundException(String code, String id, String message, Throwable cause) {
        super(code, message, cause);
        this.id = id;
    }

    public EntityNotFoundException(String id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public EntityNotFoundException(String code, String id, String message) {
        super(code, message);
        this.id = id;
    }

    public EntityNotFoundException(String id, String message) {
        super(message);
        this.id = id;
    }
}

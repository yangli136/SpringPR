/* (C)2023 */
package dev.springpr.springpr.base.exception;

import java.util.UUID;

import lombok.Getter;

@SuppressWarnings("serial")
public class SpringPrApplicationException extends RuntimeException {

    @Getter private final String id = UUID.randomUUID().toString();
    @Getter protected final String code;

    public SpringPrApplicationException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SpringPrApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.code = this.id;
    }

    public SpringPrApplicationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SpringPrApplicationException(String message) {
        super(message);
        this.code = this.id;
    }

    @Override
    public String toString() {
        return "SpringPrApplicationException [id=" + id + ", code=" + code + "]";
    }
}

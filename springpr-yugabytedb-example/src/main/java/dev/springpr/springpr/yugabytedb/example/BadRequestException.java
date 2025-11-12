/* (C)2025 */
package dev.springpr.springpr.yugabytedb.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String payload) {
        super("Request is not valid:[" + payload + "]");
    }
}

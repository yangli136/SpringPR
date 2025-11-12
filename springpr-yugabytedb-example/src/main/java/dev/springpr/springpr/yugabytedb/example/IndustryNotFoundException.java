/* (C)2025 */
package dev.springpr.springpr.yugabytedb.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class IndustryNotFoundException extends RuntimeException {
    public IndustryNotFoundException(String sic8Cd) {
        super("Could not find industry with id " + sic8Cd);
    }
}

/* (C)2025 */
package dev.springpr.springpr.yugabytedb.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MerchantNotFoundException extends RuntimeException {
    public MerchantNotFoundException(String merId) {
        super("Could not find merchant with id " + merId);
    }
}

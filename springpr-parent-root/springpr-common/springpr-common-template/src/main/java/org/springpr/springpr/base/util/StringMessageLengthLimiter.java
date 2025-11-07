/* (C)2023 */
package org.springpr.springpr.base.util;

import jakarta.validation.constraints.Positive;

public interface StringMessageLengthLimiter {

    String limit(String message);

    String largeLimit(String message);

    String limit(String message, @Positive int limitLength);
}

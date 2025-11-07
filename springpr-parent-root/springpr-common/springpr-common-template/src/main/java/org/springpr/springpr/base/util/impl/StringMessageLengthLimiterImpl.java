/* (C)2023 */
package org.springpr.springpr.base.util.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Positive;

import org.springpr.springpr.base.util.StringMessageLengthLimiter;

@Validated
@Component
public class StringMessageLengthLimiterImpl implements StringMessageLengthLimiter {
    @Value("${springpr.log.message.length.normal:100}")
    private int normalLimitLength;

    @Value("${springpr.log.message.length.large:200}")
    private int largeLimitLength;

    @Override
    public String limit(String message, @Positive int limitLength) {
        if (message != null) {
            String messageAsString = message;
            int messageLength = messageAsString.length();
            if (messageLength <= limitLength) {
                return messageAsString;
            }
            return messageAsString.substring(0, limitLength / 2 - 1)
                    + " ... "
                    + messageAsString.substring(messageLength - limitLength / 2, messageLength);
        }
        return "Message is null.";
    }

    @Override
    public String limit(String message) {
        return this.limit(message, this.normalLimitLength);
    }

    @Override
    public String largeLimit(String message) {
        return this.limit(message, this.largeLimitLength);
    }
}

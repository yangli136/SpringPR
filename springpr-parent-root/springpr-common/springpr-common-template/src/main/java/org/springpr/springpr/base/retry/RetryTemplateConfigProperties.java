/* (C)2023 */
package org.springpr.springpr.base.retry;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "springpr.base.retry")
@Data
public class RetryTemplateConfigProperties {

    private int intialInterval;
    private int maxInterval;
    private int multiplier;
    private int totalRetries;
}

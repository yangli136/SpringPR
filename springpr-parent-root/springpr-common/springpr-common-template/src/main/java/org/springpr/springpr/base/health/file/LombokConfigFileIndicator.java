/* (C)2023 */
package org.springpr.springpr.base.health.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@ConditionalOnBean(SpringPrBaseFileValidationService.class)
@RequiredArgsConstructor
public class LombokConfigFileIndicator implements HealthIndicator {
    @Value("${required.file.absolute.path}")
    private final String requiredFilePath;

    private final SpringPrBaseFileValidationService springPrBaseFileValidationService;

    @Override
    public Health health() {
        boolean result = check();
        if (result) {
            return Health.up()
                    .withDetail("Lombok.config file NOT found in location", this.requiredFilePath)
                    .build();
        }
        return Health.down()
                .withDetail("Lombok.config file found in location", this.requiredFilePath)
                .build();
    }

    private boolean check() {
        return springPrBaseFileValidationService.isFileExists(requiredFilePath);
    }
}

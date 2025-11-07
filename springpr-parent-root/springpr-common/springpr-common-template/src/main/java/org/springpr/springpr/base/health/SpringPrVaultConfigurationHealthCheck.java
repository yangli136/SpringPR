/* (C)2023 */
package org.springpr.springpr.base.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractReactiveHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springpr.springpr.base.stereotype.ValidatedService;

@ValidatedService
@RequiredArgsConstructor
public class SpringPrVaultConfigurationHealthCheck extends AbstractReactiveHealthIndicator {

    private static final String IS_NOT_AVAILABLE = "is not available";

    private static final String IS_AVAILABLE = "is available";

    @SuppressWarnings("squid:S3749")
    @Value("${VAULT_DEMO_PROPERTY}")
    private final String vaultDemoPassword;

    @SuppressWarnings("squid:S3749")
    @Value("${VAULT_DEMO_USERNAME}")
    private final String vaultDemoUsername;

    private boolean checkAndDisplay(
            final String propertyValue, final String propertyName, final String defaultEmptyValue) {
        return propertyValue != null
                && !propertyValue.isEmpty()
                && propertyValue.compareToIgnoreCase(defaultEmptyValue) != 0;
    }

    private boolean check(
            final String propertyValue, final String propertyName, final String defaultEmptyValue) {
        return propertyValue != null
                && !propertyValue.isEmpty()
                && propertyValue.compareToIgnoreCase(defaultEmptyValue) != 0;
    }

    @Override
    protected Mono<Health> doHealthCheck(Builder builder) {
        final Health health;
        if (checkAndDisplay(vaultDemoUsername, "VAULT_DEMO_USERNAME", "${VAULT_DEMO_USERNAME}")
                && check(vaultDemoPassword, "VAULT_DEMO_PROPERTY", "${VAULT_DEMO_PROPERTY}")) {
            health =
                    builder.up()
                            .withDetail("VAULT_DEMO_USERNAME", vaultDemoUsername)
                            .withDetail("VAULT_DEMO_PROPERTY", IS_AVAILABLE)
                            .build();
        } else {
            Builder tempBuilder =
                    builder.down().withDetail("VAULT_DEMO_USERNAME", vaultDemoUsername);
            if (check(vaultDemoPassword, "VAULT_DEMO_PROPERTY", "${VAULT_DEMO_PROPERTY}")) {
                tempBuilder.withDetail("VAULT_DEMO_PROPERTY", IS_AVAILABLE);
            } else {
                tempBuilder.withDetail("VAULT_DEMO_PROPERTY", IS_NOT_AVAILABLE);
            }
            health = tempBuilder.build();
        }

        return Mono.just(health);
    }
}

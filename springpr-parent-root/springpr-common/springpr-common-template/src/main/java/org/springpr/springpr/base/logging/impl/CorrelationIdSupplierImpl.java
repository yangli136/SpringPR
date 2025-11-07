/* (C)2023 */
package org.springpr.springpr.base.logging.impl;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springpr.springpr.base.logging.CorrelationIdSupplier;

/** Generates correlation IDs */
@Service
@RequiredArgsConstructor
public final class CorrelationIdSupplierImpl implements CorrelationIdSupplier {

    @SuppressWarnings("squid:S3749")
    private final Clock clock;

    @SuppressWarnings("squid:S3749")
    @Qualifier("hostnameProperty") private final String hostname;

    /**
     * @return a new generated correlation id
     */
    @Override
    public String createCorrelationId() {

        return hostname + "-" + LocalDateTime.now(clock).getSecond() + "-" + UUID.randomUUID();
    }
}

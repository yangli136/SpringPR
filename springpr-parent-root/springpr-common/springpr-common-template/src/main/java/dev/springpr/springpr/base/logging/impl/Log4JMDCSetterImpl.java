/* (C)2023 */
package dev.springpr.springpr.base.logging.impl;

import java.net.InetAddress;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.exception.SpringPrBaseAlertMessage;
import dev.springpr.springpr.base.logging.CorrelationIdSupplier;
import dev.springpr.springpr.base.logging.Log4jMDCSetter;

/**
 * An utility class sets application information in Log4j2 MDC (Mapped Diagnostic Context).
 *
 * <p>values are going to be populated into MDC: 1. hostname 2. application name 3. correlation id
 */
@Service
@RequiredArgsConstructor
@Slf4j
public final class Log4JMDCSetterImpl implements Log4jMDCSetter {

    private static final String APPLICATION_KEY = "APPLICATION";
    private static final String CORRELATION_ID_KEY = "CORRELATION_ID";
    private static final String HOSTNAME_KEY = "HOSTNAME";
    private static final String UNIQUE_ID = "UNIQUE_ID";

    @SuppressWarnings("squid:S3749")
    private final CorrelationIdSupplier correlationIdSupplier;

    @SuppressWarnings("squid:S3749")
    private final InetAddress inetAddress;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void clear() {
        ThreadContext.clearAll();
    }

    @Override
    public void setHostAndAppInfoIfMissing() {
        String correlationId = ThreadContext.get(CORRELATION_ID_KEY);
        if (!StringUtils.isBlank(correlationId)) {
            return;
        }

        ThreadContext.put(APPLICATION_KEY, applicationName);
        ThreadContext.put(CORRELATION_ID_KEY, createCorrelationId());
        ThreadContext.put(HOSTNAME_KEY, inetAddress.getHostName());
    }

    @Override
    public void addParameter(String key, String value) {
        ThreadContext.put(key, value);
    }

    @Override
    public void setUniqueId(String uniqueId) {
        ThreadContext.put(UNIQUE_ID, uniqueId);
    }

    @Override
    public String getUniqueId() {
        final String uniqueId = ThreadContext.get(UNIQUE_ID);
        return uniqueId != null ? uniqueId : "NO UNIQIE_ID";
    }

    private String createCorrelationId() {
        try {
            return correlationIdSupplier.createCorrelationId();
        } catch (Exception e) {
            log.error(
                    "{} ### ### ### Exception when generating correlation id",
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_INTERNAL_EXCEPTION,
                    e);
            return UUID.randomUUID().toString();
        }
    }
}

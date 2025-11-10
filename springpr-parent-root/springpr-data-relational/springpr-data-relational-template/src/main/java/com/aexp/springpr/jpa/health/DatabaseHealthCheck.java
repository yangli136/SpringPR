/* (C)2023 */
package com.aexp.springpr.jpa.health;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseHealthCheck implements HealthIndicator {
    private final JdbcTemplate template;

    @Value("${spring.datasource.hikari.connectionTestQuery}")
    private String connectionTestQuery;

    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 1) {
            return Health.down().withDetail(connectionTestQuery, "failed").build();
        }
        return Health.up().build();
    }

    public int check() {
        List<Object> results = template.query(connectionTestQuery, new SingleColumnRowMapper<>());
        return results.size();
    }
}

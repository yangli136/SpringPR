/* (C)2025 */
package org.springpr.springpr.batch.common.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.cloud.task.configuration.TaskProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConditionalOnClass(name = "org.springframework.cloud.task.configuration.TaskProperties")
@RequiredArgsConstructor
@Slf4j
public class CustomTaskConfiguration {
    // Access to Task Metadata Tables
    @Bean
    DefaultTaskConfigurer defaultTaskConfigurer(
            @BatchDataSource DataSource dataSource, TaskProperties taskProperties) {
        DefaultTaskConfigurer defaultTaskConfigurer =
                new DefaultTaskConfigurer(dataSource, taskProperties.getTablePrefix(), null);
        log.info("taskProperties.getTablePrefix():{}", taskProperties.getTablePrefix());
        return defaultTaskConfigurer;
    }
}

/* (C)2025 */
package org.springpr.springpr.batch.common.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DatasourceConfiguration {
    private final ApplicationContext context;

    // Batch Metedata Data Source
    @Bean
    @ConfigurationProperties("spring.datasource.batch")
    DataSourceProperties batchDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @BatchDataSource
    @ConfigurationProperties("spring.datasource.batch.hikari")
    DataSource batchDataSource() {
        DataSourceProperties batchDataSourceProperties = batchDataSourceProperties();
        log.info("batchDataSourceProperties.Type:{}", batchDataSourceProperties.getType());
        log.info(
                "batchDataSourceProperties.DriverClassName:{}",
                batchDataSourceProperties.getDriverClassName());
        log.info("batchDataSourceProperties.JndiName:{}", batchDataSourceProperties.getJndiName());
        log.info("batchDataSourceProperties.Name:{}", batchDataSourceProperties.getName());
        log.info("batchDataSourceProperties.Url:{}", batchDataSourceProperties.getUrl());
        log.info("batchDataSourceProperties.Username:{}", batchDataSourceProperties.getUsername());
        log.info("batchDataSourceProperties.Password:{}", batchDataSourceProperties.getPassword());
        return batchDataSourceProperties.initializeDataSourceBuilder().build();
    }

    // Batch Business Data Source 1
    @Bean
    @ConfigurationProperties("spring.datasource.database1")
    DataSourceProperties database1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    // @Bean(defaultCandidate = false)
    @ConfigurationProperties("spring.datasource.database1.hikari")
    DataSource database1DataSource() {
        DataSourceProperties database1DataSourceProperties = database1DataSourceProperties();
        log.info(
                "database1DataSourceProperties.getDriverClassName():{}",
                database1DataSourceProperties.getDriverClassName());
        return database1DataSourceProperties.initializeDataSourceBuilder().build();
    }

    // Batch Business Data Source 2
    @Bean
    @ConfigurationProperties("spring.datasource.database2")
    @ConditionalOnProperty(prefix = "spring.datasource.database2", name = "url")
    DataSourceProperties database2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.database2.hikari")
    @ConditionalOnProperty(prefix = "spring.datasource.database2", name = "url")
    DataSource database2DataSource() {
        DataSourceProperties database2DataSourceProperties = database2DataSourceProperties();
        log.info(
                "database2DataSourceProperties.getDriverClassName():{}",
                database2DataSourceProperties.getDriverClassName());
        return database2DataSourceProperties.initializeDataSourceBuilder().build();
    }

    // Batch Business Data Source 3
    @Bean
    @ConfigurationProperties("spring.datasource.database3")
    @ConditionalOnProperty(prefix = "spring.datasource.database3", name = "url")
    DataSourceProperties database3DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.database3.hikari")
    @ConditionalOnProperty(prefix = "spring.datasource.database3", name = "url")
    DataSource database3DataSource() {
        DataSourceProperties database3DataSourceProperties = database3DataSourceProperties();
        log.info(
                "database3DataSourceProperties.getDriverClassName():{}",
                database3DataSourceProperties.getDriverClassName());
        return database3DataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    PlatformTransactionManager transactionManager() {
        PlatformTransactionManager transactionManager = null;
        try {
            Class.forName("jakarta.persistence.EntityManager");
            if (this.context != null
                    && this.context.getBeanNamesForType(EntityManager.class).length > 0) {
                log.debug("EntityManager was found, using JpaTransactionManager");
                transactionManager = new JpaTransactionManager();
            }
        } catch (ClassNotFoundException ignore) {
            log.warn("No EntityManager was found, using DataSourceTransactionManager");
        } finally {
            if (transactionManager == null) {
                transactionManager = new JdbcTransactionManager(database1DataSource());
            }
        }
        //        if (this.dataSource == null) {
        //            log.debug("No DataSource was found, using ResourcelessTransactionManager");
        //            transactionManager = new ResourcelessTransactionManager();
        //        }
        return transactionManager;
    }

    @Bean
    @BatchTransactionManager
    PlatformTransactionManager batchTransactionManager() {
        return new JdbcTransactionManager(batchDataSource());
    }

    // if need a customized JdbcTemplate
    //    @Bean
    //    @Primary
    //    JdbcTemplate jdbcTemplate(JdbcProperties properties) {
    //        JdbcTemplate jdbcTemplate = new JdbcTemplate(database1DataSource());
    //        JdbcProperties.Template template = properties.getTemplate();
    //        jdbcTemplate.setFetchSize(template.getFetchSize());
    //        jdbcTemplate.setMaxRows(template.getMaxRows());
    //        if (template.getQueryTimeout() != null) {
    //            jdbcTemplate.setQueryTimeout((int) template.getQueryTimeout().getSeconds());
    //        }
    //        return jdbcTemplate;
    //    }
}

/* (C)2025 */
package org.springpr.springpr.yugabytedb.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Profile("local-yugabyte | server-yugabyte")
@Configuration
@Slf4j
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.yugabytedb")
    YBClusterAwareDataSourceProperties yugabytedbDataSourceProperties() {
        return new YBClusterAwareDataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    HikariConfig yugabytedbHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    DataSource yugabytedbDataSource(
            YBClusterAwareDataSourceProperties yugabytedbDataSourceProperties,
            HikariConfig hikariConfig) {
        log.info("yugabytedbDataSourceProperties:{}", yugabytedbDataSourceProperties);
        log.info("hikariConfig.PoolName:{}", hikariConfig.getPoolName());
        log.info("hikariConfig.DataSourceClassName:{}", hikariConfig.getDataSourceClassName());
        log.info("hikariConfig.Schema:{}", hikariConfig.getSchema());
        log.info("hikariConfig.Username:{}", hikariConfig.getUsername());
        log.info("hikariConfig.MaximumPoolSize:{}", hikariConfig.getMaximumPoolSize());

        Properties poolProperties = new Properties();
        poolProperties.setProperty(
                "dataSourceClassName", yugabytedbDataSourceProperties.getDataSourceClassName());
        poolProperties.setProperty(
                "dataSource.serverName", yugabytedbDataSourceProperties.getServerName());
        poolProperties.setProperty(
                "dataSource.portNumber", yugabytedbDataSourceProperties.getPortNumber());
        poolProperties.setProperty(
                "dataSource.databaseName", yugabytedbDataSourceProperties.getDatabaseName());
        poolProperties.setProperty("dataSource.user", yugabytedbDataSourceProperties.getUser());
        if (yugabytedbDataSourceProperties.getAdditionalEndpoints() != null
                && !yugabytedbDataSourceProperties.getAdditionalEndpoints().isBlank()) {
            poolProperties.setProperty(
                    "dataSource.additionalEndpoints",
                    yugabytedbDataSourceProperties.getAdditionalEndpoints());
        }
        if (yugabytedbDataSourceProperties.getTopologyKeys() != null
                && !yugabytedbDataSourceProperties.getTopologyKeys().isBlank()) {
            poolProperties.setProperty(
                    "dataSource.topologyKeys", yugabytedbDataSourceProperties.getTopologyKeys());
        }

        poolProperties.setProperty("poolName", yugabytedbDataSourceProperties.getPoolName());
        poolProperties.setProperty(
                "maximumPoolSize", String.valueOf(hikariConfig.getMaximumPoolSize()));
        poolProperties.setProperty("connectionTestQuery", hikariConfig.getConnectionTestQuery());
        poolProperties.setProperty(
                "connectionTimeout", String.valueOf(hikariConfig.getConnectionTimeout()));
        poolProperties.setProperty("idleTimeout", String.valueOf(hikariConfig.getIdleTimeout()));
        poolProperties.setProperty(
                "leakDetectionThreshold", String.valueOf(hikariConfig.getLeakDetectionThreshold()));
        poolProperties.setProperty("maxLifetime", String.valueOf(hikariConfig.getMaxLifetime()));
        poolProperties.setProperty("minimumIdle", String.valueOf(hikariConfig.getMinimumIdle()));
        poolProperties.setProperty(
                "validationTimeout", String.valueOf(hikariConfig.getValidationTimeout()));

        HikariConfig poolHikariConfig = new HikariConfig(poolProperties);
        poolHikariConfig.validate();
        return new HikariDataSource(poolHikariConfig);
    }
}

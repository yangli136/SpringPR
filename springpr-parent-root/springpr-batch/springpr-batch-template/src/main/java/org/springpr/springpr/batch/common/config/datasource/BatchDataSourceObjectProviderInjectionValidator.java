/* (C)2025 */
package org.springpr.springpr.batch.common.config.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BatchDataSourceObjectProviderInjectionValidator {
    public BatchDataSourceObjectProviderInjectionValidator(
            DataSource dataSource,
            @BatchDataSource DataSource batchDataSource,
            @BatchDataSource ObjectProvider<DataSource> dataSourceObjectProvider) {
        DataSource objectProviderDataSource = dataSourceObjectProvider.getIfAvailable();
        if (objectProviderDataSource == null) {
            log.info("objectProviderDataSource is null");
        } else {
            log.info("objectProviderDataSource is NOT null");
        }

        if (log.isInfoEnabled()) {
            log.info("dataSource == batchDataSource:{}", dataSource == batchDataSource);
            log.info(
                    "dataSource == objectProviderDataSource:{}",
                    dataSource == objectProviderDataSource);
            log.info(
                    "batchDataSource == objectProviderDataSource:{}",
                    batchDataSource == objectProviderDataSource);
        }
    }
}

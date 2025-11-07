/* (C)2024 */
package org.springpr.springpr.batch.common.config.datasource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JobOperatorConfiguration {

    /**
     * All injected dependencies for this bean are provided by the @EnableBatchProcessing
     * infrastructure out of the box.
     */
    @Bean
    SimpleJobOperator asyncJobOperator(
            JobExplorer jobExplorer,
            JobRepository jobRepository,
            JobRegistry jobRegistry,
            JobLauncher jobLauncher) {

        SimpleJobOperator asyncJobOperator = new SimpleJobOperator();
        asyncJobOperator.setJobExplorer(jobExplorer);
        asyncJobOperator.setJobRepository(jobRepository);
        asyncJobOperator.setJobRegistry(jobRegistry);
        asyncJobOperator.setJobLauncher(jobLauncher);

        return asyncJobOperator;
    }
    //    @Bean
    //    JobExplorer jobExplorer() throws Exception {
    //        JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
    //        factoryBean.setDataSource(dataSource);
    //        factoryBean.setTablePrefix("boot3_batch");
    //        return factoryBean.getObject();
    //    }
}

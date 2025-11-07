/* (C)2024 */
package org.springpr.springpr.batch.common;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("*** *** *** starting job: {}", jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("*** *** *** completed job: {}", jobExecution);
    }
}

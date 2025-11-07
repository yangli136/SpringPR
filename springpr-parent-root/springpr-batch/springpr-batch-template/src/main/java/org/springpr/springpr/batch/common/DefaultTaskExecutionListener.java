/* (C)2024 */
package org.springpr.springpr.batch.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ConditionalOnClass(name = "org.springframework.cloud.task.repository.TaskExecution")
public class DefaultTaskExecutionListener {

    @BeforeTask
    public void beforeTask(TaskExecution taskExecution) {
        log.info("### Starting a task:{}", taskExecution);
    }

    @AfterTask
    public void afterTask(TaskExecution taskExecution) {
        log.info("### Completed a task:{}", taskExecution);
    }

    @FailedTask
    public void failedTask(TaskExecution taskExecution, Throwable throwable) {
        log.info(
                "### failed a task:{} with exception:{}",
                taskExecution,
                throwable.getMessage(),
                throwable);
    }
}

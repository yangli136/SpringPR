/* (C)2023 */
package dev.springpr.springpr.base.scheduler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;

@Component
@EnableAsync
@Slf4j
@Observed(name = "scheduledTasks")
@ConditionalOnProperty(
        prefix = "springpr.common",
        name = "scheduleFixedRateTask.enabled",
        havingValue = "true",
        matchIfMissing = false)
public class ScheduledTasks {
    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void scheduleFixedRateTask() {
        if (log.isTraceEnabled()) {
            log.trace("One second notification - {}", System.currentTimeMillis() / 1000);
        }
    }

    @Scheduled(cron = "@hourly")
    public void scheduleTaskUsingCronExpression() {

        if (log.isTraceEnabled()) {
            log.trace("One hour notification - {}", System.currentTimeMillis() / 1000);
        }
    }
}

// @Configuration
// @EnableScheduling
// public class DynamicSchedulingConfig implements SchedulingConfigurer {
//
//    @Autowired
//    private TickService tickService;
//
//    @Bean
//    public Executor taskExecutor() {
//        return Executors.newSingleThreadScheduledExecutor();
//    }
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(taskExecutor());
//        taskRegistrar.addTriggerTask(
//          new Runnable() {
//              @Override
//              public void run() {
//                  tickService.tick();
//              }
//          },
//          new Trigger() {
//              @Override
//              public Date nextExecutionTime(TriggerContext context) {
//                  Optional<Date> lastCompletionTime =
//                    Optional.ofNullable(context.lastCompletionTime());
//                  Instant nextExecutionTime =
//                    lastCompletionTime.orElseGet(Date::new).toInstant()
//                      .plusMillis(tickService.getDelay());
//                  return Date.from(nextExecutionTime);
//              }
//          }
//        );
//    }

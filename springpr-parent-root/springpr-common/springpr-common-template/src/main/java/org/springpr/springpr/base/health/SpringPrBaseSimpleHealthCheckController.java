/* (C)2023 */
package org.springpr.springpr.base.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.base.metrics.SystemMetricsScheduler;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/${app.id}")
public class SpringPrBaseSimpleHealthCheckController {
    private final SystemMetricsScheduler systemMetricsScheduler;

    @Autowired(required = false)
    @Qualifier("applicationTaskExecutor") private SimpleAsyncTaskExecutor simpleAsyncTaskExecutor;

    @Autowired(required = false)
    @Qualifier("taskScheduler") private SimpleAsyncTaskScheduler simpleAsyncTaskScheduler;

    @PostConstruct
    private void init() {
        log.error("*** *** ***");
        log.error("*** *** ***");
        log.error("*** *** ***");
        if (this.simpleAsyncTaskExecutor != null) {
            log.error(
                    "simpleAsyncTaskExecutor - ConcurrencyLimit:{}",
                    this.simpleAsyncTaskExecutor.getConcurrencyLimit());
        } else {
            log.error("simpleAsyncTaskExecutor is null");
        }

        if (this.simpleAsyncTaskScheduler != null) {
            log.error(
                    "simpleAsyncTaskScheduler - ConcurrencyLimit:{}",
                    this.simpleAsyncTaskScheduler.getConcurrencyLimit());
        } else {
            log.error("simpleAsyncTaskScheduler is null");
        }
    }

    @GetMapping(value = "/simpleHealthCheck", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> simpleHealthCheck() {
        return ResponseEntity.ok("Simple Health Check Successful.");
    }

    @GetMapping("/gc")
    public ResponseEntity<String> gc() throws Exception {
        System.gc();
        System.runFinalization();
        return ResponseEntity.ok("success.");
    }

    @GetMapping("/max-system-metrics")
    public ResponseEntity<String> getMaxSystemMetrics() {
        return ResponseEntity.ok(systemMetricsScheduler.getMaxMetrics());
    }

    @GetMapping("/reset-max-system-metrics")
    public ResponseEntity<String> resetMaxSystemMetrics() {
        systemMetricsScheduler.reset();
        return ResponseEntity.ok(systemMetricsScheduler.getMaxMetrics());
    }

    @GetMapping("/sample-max-system-metrics")
    public ResponseEntity<String> sampleMaxSystemMetrics() {
        return ResponseEntity.ok(systemMetricsScheduler.getSystemMetricsSample());
    }
}

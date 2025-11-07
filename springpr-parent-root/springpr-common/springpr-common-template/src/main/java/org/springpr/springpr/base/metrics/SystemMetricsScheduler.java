/* (C)2024 */
package org.springpr.springpr.base.metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sun.management.OperatingSystemMXBean;

import io.micrometer.observation.annotation.Observed;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@EnableAsync
@EnableScheduling
@Slf4j
@Observed(name = "scheduledTasks")
@ConditionalOnProperty(
        prefix = "springpr.common",
        name = "systemMetricsScheduler.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class SystemMetricsScheduler {
    private static final String METRICS_HEADER =
            "***************************************************";
    private static final int _1M = 1000000;
    private ThreadMXBean threadMXBean;
    private MemoryMXBean memoryMXBean;
    private RuntimeMXBean runtimeMXBean;
    private OperatingSystemMXBean operatingSystemMXBean;

    private AtomicReference<SystemMetrics> oldMetricsRef = new AtomicReference<>();
    private ServerInfo serverInfo;
    private AtomicInteger count = new AtomicInteger();

    @Value("${app.hostname}")
    private String hostName;

    public String getMaxMetrics() {
        return this.getMaxMetrics(this.oldMetricsRef.get());
    }

    private String getMaxMetrics(SystemMetrics metrics) {
        StringBuilder builder = new StringBuilder(METRICS_HEADER);
        builder.append(System.lineSeparator())
                .append("count:")
                .append(String.valueOf(count.get()))
                .append(System.lineSeparator())
                .append(this.serverInfo)
                .append(System.lineSeparator())
                .append(metrics)
                .append(System.lineSeparator())
                .append(METRICS_HEADER);
        return builder.toString().replace(",", System.lineSeparator());
    }

    @PostConstruct
    private void init() {
        reset();
    }

    public void reset() {
        this.count.set(0);
        this.oldMetricsRef.set(SystemMetrics.builder().freeMemorySize(Long.MAX_VALUE).build());
        threadMXBean = ManagementFactory.getThreadMXBean();
        memoryMXBean = ManagementFactory.getMemoryMXBean();
        runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        operatingSystemMXBean =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        log.info(METRICS_HEADER);
        serverInfo =
                ServerInfo.builder()
                        .hostname(this.hostName)
                        .vmName(runtimeMXBean.getVmName())
                        .vmVersion(runtimeMXBean.getVmVersion())
                        .arch(operatingSystemMXBean.getArch())
                        .availableProcessors(operatingSystemMXBean.getAvailableProcessors())
                        .totalMemorySize(operatingSystemMXBean.getTotalMemorySize() / _1M)
                        .build();
        log.info("Server Info:{}", serverInfo);
        log.info(METRICS_HEADER);
    }

    @Scheduled(cron = "${springpr.common.systemMetricsScheduler.sampling.cron}")
    public void scheduleFixedRateTask() {
        sample();
    }

    private SystemMetrics sample() {
        log.info(METRICS_HEADER);
        count.incrementAndGet();
        final MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        final MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        final SystemMetrics newMetrics =
                SystemMetrics.builder()
                        .systemLoadAverage(operatingSystemMXBean.getSystemLoadAverage())
                        .cpuLoad(operatingSystemMXBean.getCpuLoad())
                        .processCpuLoad(operatingSystemMXBean.getProcessCpuLoad())
                        .totalMemorySize(operatingSystemMXBean.getTotalMemorySize() / _1M)
                        .committedVirtualMemorySize(
                                operatingSystemMXBean.getCommittedVirtualMemorySize() / _1M)
                        .freeMemorySize(operatingSystemMXBean.getFreeMemorySize() / _1M)
                        .heapMemoryCommitted(heapMemoryUsage.getCommitted() / _1M)
                        .heapMemoryUsed(heapMemoryUsage.getUsed() / _1M)
                        .heapMemoryMax(heapMemoryUsage.getMax() / _1M)
                        .nonHeapMemoryCommitted(nonHeapMemoryUsage.getCommitted() / _1M)
                        .nonHeapMemoryUsed(nonHeapMemoryUsage.getUsed() / _1M)
                        .nonHeapMemoryMax(nonHeapMemoryUsage.getMax() / _1M)
                        .threadCount(threadMXBean.getThreadCount())
                        .threadPeak(threadMXBean.getPeakThreadCount())
                        .threadTotalStarted(threadMXBean.getTotalStartedThreadCount())
                        .build();

        boolean newMaxValueAdded = false;

        while (!newMaxValueAdded) {
            final SystemMetrics oldMetrics = oldMetricsRef.get();
            log.info("old SystemMetrics:{}", oldMetrics);
            log.info("New SystemMetrics:{}", newMetrics);

            final SystemMetrics maxMetrics = oldMetrics.getMax(newMetrics);
            newMaxValueAdded = oldMetricsRef.compareAndSet(oldMetrics, maxMetrics);

            log.info("Max SystemMetrics:{}", maxMetrics);
            log.info(METRICS_HEADER);
        }

        return oldMetricsRef.get();
    }

    public String getSystemMetricsSample() {
        SystemMetrics sample = this.sample();
        return this.getMaxMetrics(sample);
    }
}

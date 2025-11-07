/* (C)2024 */
package org.springpr.springpr.base.metrics;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SystemMetrics {
    private int count;
    private double systemLoadAverage;
    private double cpuLoad;
    private double processCpuLoad;
    private long heapMemoryCommitted;
    private long heapMemoryUsed;
    private long heapMemoryMax;
    private long nonHeapMemoryCommitted;
    private long nonHeapMemoryUsed;
    private long nonHeapMemoryMax;
    private int threadCount;
    private int threadPeak;
    private long threadTotalStarted;
    private long totalMemorySize;
    private long committedVirtualMemorySize;
    private long freeMemorySize;

    public SystemMetrics getMax(final SystemMetrics newSystemMetrics) {
        final SystemMetricsBuilder systemMetricsBuilder = SystemMetrics.builder();

        if (newSystemMetrics.getCpuLoad() > this.getCpuLoad()) {
            systemMetricsBuilder.cpuLoad(newSystemMetrics.getCpuLoad());
        } else {
            systemMetricsBuilder.cpuLoad(this.getCpuLoad());
        }
        if (newSystemMetrics.getTotalMemorySize() > this.getTotalMemorySize()) {
            systemMetricsBuilder.totalMemorySize(newSystemMetrics.getTotalMemorySize());
        } else {
            systemMetricsBuilder.totalMemorySize(this.getTotalMemorySize());
        }
        if (newSystemMetrics.getCommittedVirtualMemorySize()
                > this.getCommittedVirtualMemorySize()) {
            systemMetricsBuilder.committedVirtualMemorySize(
                    newSystemMetrics.getCommittedVirtualMemorySize());
        } else {
            systemMetricsBuilder.committedVirtualMemorySize(this.getCommittedVirtualMemorySize());
        }
        if (newSystemMetrics.getFreeMemorySize() < this.getFreeMemorySize()) {
            systemMetricsBuilder.freeMemorySize(newSystemMetrics.getFreeMemorySize());
        } else {
            systemMetricsBuilder.freeMemorySize(this.getFreeMemorySize());
        }
        if (newSystemMetrics.getHeapMemoryCommitted() > this.getHeapMemoryCommitted()) {
            systemMetricsBuilder.heapMemoryCommitted(newSystemMetrics.getHeapMemoryCommitted());
        } else {
            systemMetricsBuilder.heapMemoryCommitted(this.getHeapMemoryCommitted());
        }
        if (newSystemMetrics.getHeapMemoryUsed() > this.getHeapMemoryUsed()) {
            systemMetricsBuilder.heapMemoryUsed(newSystemMetrics.getHeapMemoryUsed());
        } else {
            systemMetricsBuilder.heapMemoryUsed(this.getHeapMemoryUsed());
        }
        if (newSystemMetrics.getHeapMemoryMax() > this.getHeapMemoryMax()) {
            systemMetricsBuilder.heapMemoryMax(newSystemMetrics.getHeapMemoryMax());
        } else {
            systemMetricsBuilder.heapMemoryMax(this.getHeapMemoryMax());
        }
        if (newSystemMetrics.getNonHeapMemoryCommitted() > this.getNonHeapMemoryCommitted()) {
            systemMetricsBuilder.nonHeapMemoryCommitted(
                    newSystemMetrics.getNonHeapMemoryCommitted());
        } else {
            systemMetricsBuilder.nonHeapMemoryCommitted(this.getNonHeapMemoryCommitted());
        }
        if (newSystemMetrics.getNonHeapMemoryUsed() > this.getNonHeapMemoryUsed()) {
            systemMetricsBuilder.nonHeapMemoryUsed(newSystemMetrics.getNonHeapMemoryUsed());
        } else {
            systemMetricsBuilder.nonHeapMemoryUsed(this.getNonHeapMemoryUsed());
        }
        if (newSystemMetrics.getNonHeapMemoryMax() > this.getNonHeapMemoryMax()) {
            systemMetricsBuilder.nonHeapMemoryMax(newSystemMetrics.getNonHeapMemoryMax());
        } else {
            systemMetricsBuilder.nonHeapMemoryMax(this.getNonHeapMemoryMax());
        }
        if (newSystemMetrics.getProcessCpuLoad() > this.getProcessCpuLoad()) {
            systemMetricsBuilder.processCpuLoad(newSystemMetrics.getProcessCpuLoad());
        } else {
            systemMetricsBuilder.processCpuLoad(this.getProcessCpuLoad());
        }
        if (newSystemMetrics.getSystemLoadAverage() > this.getSystemLoadAverage()) {
            systemMetricsBuilder.systemLoadAverage(newSystemMetrics.getSystemLoadAverage());
        } else {
            systemMetricsBuilder.systemLoadAverage(this.getSystemLoadAverage());
        }
        if (newSystemMetrics.getThreadCount() > this.getThreadCount()) {
            systemMetricsBuilder.threadCount(newSystemMetrics.getThreadCount());
        } else {
            systemMetricsBuilder.threadCount(this.getThreadCount());
        }
        if (newSystemMetrics.getThreadPeak() > this.getThreadPeak()) {
            systemMetricsBuilder.threadPeak(newSystemMetrics.getThreadPeak());
        } else {
            systemMetricsBuilder.threadPeak(this.getThreadPeak());
        }
        if (newSystemMetrics.getThreadTotalStarted() > this.getThreadTotalStarted()) {
            systemMetricsBuilder.threadTotalStarted(newSystemMetrics.getThreadTotalStarted());
        } else {
            systemMetricsBuilder.threadTotalStarted(this.getThreadTotalStarted());
        }
        return systemMetricsBuilder.build();
    }
}

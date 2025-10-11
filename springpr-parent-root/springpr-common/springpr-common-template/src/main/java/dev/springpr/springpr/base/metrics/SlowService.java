/* (C)2024 */
package dev.springpr.springpr.base.metrics;

import jakarta.validation.constraints.Min;

public interface SlowService {

    String delay(@Min(1) int delayInMillis);
}

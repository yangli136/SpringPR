/* (C)2024 */
package org.springpr.springpr.base.metrics;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServerInfo {
    private String hostname;
    private String vmName;
    private String vmVersion;
    private String arch;
    private int availableProcessors;
    private long totalMemorySize;
}

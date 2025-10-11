/* (C)2023 */
package dev.springpr.springpr.base.logging;

public interface CorrelationIdSupplier {

    /**
     * @return a new generated correlation id
     */
    String createCorrelationId();
}

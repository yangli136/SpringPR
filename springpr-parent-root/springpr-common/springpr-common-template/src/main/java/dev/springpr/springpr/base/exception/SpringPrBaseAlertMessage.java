/* (C)2023 */
package dev.springpr.springpr.base.exception;

public enum SpringPrBaseAlertMessage {
    SPRINGPR_BASE_APP_UN_RECOVERABLE_FAILURE(3001, "An un-recoverable failure caught.", 1),
    SPRINGPR_BASE_APP_RECOVERABLE_FAILURE(3001, "A recoverable failure caught.", 1),

    // Abnormal un-categorized application exception, need to investigate but normal operation will
    // not be interrupted
    SPRINGPR_BASE_APP_INTERNAL_EXCEPTION(4001, "Internal server exception caught.", 2),
    SPRINGPR_BASE_APP_EXTERNAL_EXCEPTION(4002, "External application exception caught.", 2),

    SPRINGPR_BASE_APP_JSON_MSG_PARSING_EXCEPTION(4003, "failed to process JSON.", 4),
    SPRINGPR_BASE_APP_UN_CATEGORIZED_EXCEPTION(
            4004, "Un-categorized application exception caught.", 4),

    // All retries have been exhausted, long term communicate issue found.
    SPRINGPR_BASE_APP_RETRY_EXHAUSTED(4005, "All retries failed.", 4);

    private final long code;
    private final String description;
    private final int alertLevel;

    private SpringPrBaseAlertMessage(long code, String description, int alertLevel) {
        this.code = code;
        this.description = description;
        this.alertLevel = alertLevel;
    }

    public long code() {
        return code;
    }

    public String description() {
        return description;
    }

    public int alertLevel() {
        return alertLevel;
    }
}

/* (C)2023 */
package org.springpr.springpr.base.logging;

public interface Log4jMDCSetter {

    void clear();

    void setHostAndAppInfoIfMissing();

    void addParameter(String key, String value);

    void setUniqueId(String uniqueId);

    String getUniqueId();
}

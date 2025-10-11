/* (C)2023 */
package dev.springpr.springpr.base.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(MapProperties.class)
@Slf4j
class MapPropertiesTest {
    @Autowired private MapProperties mapProperties;

    @Test
    void test() {
        if (log.isInfoEnabled()) {
            log.info("************************{}", this.mapProperties.getName());
            log.info("************************{}", this.mapProperties.getPropertiesMap());
        }
    }
}

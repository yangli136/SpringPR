/* (C)2023 */
package dev.springpr.springpr.base.util;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@PropertySource("classpath:mapProperties.properties")
@ConfigurationProperties(prefix = "util")
@Getter
@Setter
@ToString
public class MapProperties {

    private Map<String, List<String>> propertiesMap;
    private String name;
}

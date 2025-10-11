/* (C)2023 */
package dev.springpr.springpr.base.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.time.Clock;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.annotation.Validated;

import dev.springpr.springpr.base.exception.SpringPrApplicationException;
import dev.springpr.springpr.base.exception.SpringPrBaseAlertMessage;

@Configuration
@Validated
@EnableAsync
public class SpringPrBaseCommonBeansConfiguration {
    @Value("${springpr.base.timezoneid:US/Arizona}")
    private String zoneId;

    @Bean
    SecureRandom random() {
        return new SecureRandom();
    }

    @Bean
    String hostnameProperty(final Environment env) {
        String hostname = env.getProperty("HOSTNAME");
        if (null == hostname) {
            try {
                hostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
                throw new IllegalStateException("Failed to retrieve hostname.", ex);
            }
        }
        return hostname;
    }

    @Bean
    Clock clock() {
        try {
            return Clock.system(ZoneId.of(this.zoneId));
        } catch (Exception ex) {
            throw new IllegalStateException("invalid zone id:[" + this.zoneId + "]", ex);
        }
    }

    @Bean
    InetAddress inetAddress() {

        try {
            return InetAddress.getLocalHost();

        } catch (Exception ex) {
            throw new SpringPrApplicationException(
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_INTERNAL_EXCEPTION
                            + " ### ### ### Reading local inet address failed. Message:"
                            + ex.getMessage(),
                    ex);
        }
    }
    //
    //    @Bean
    //    MeterRegistryCustomizer<MeterRegistry> configurer(
    //            @Value("${spring.application.name}") String applicationName) {
    //        return registry -> registry.config().commonTags("application", applicationName);
    //    }
}

/* (C)2023 */
package dev.springpr.springpr.base;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(0)
@RequiredArgsConstructor
@Slf4j
class SpringPrBaseApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Value("${app.id}")
    private String contextPath;

    @Value("${server.port}")
    private String port;

    @Qualifier("hostnameProperty") private final String hostnameProperty;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info(
                "@@@ @@@ @@@ SpringPr Base Application is ready and started with profile:{}.",
                activeProfiles);
        if (this.activeProfiles.contains("e0")) {
            log.info(
                    "@@@ @@@ @@@ SpringPr Base Application - check application"
                            + " info:http://localhost:{}/{}/welcome",
                    this.port,
                    contextPath);
        } else {
            log.info(
                    "@@@ @@@ @@@ SpringPr Base Application - check application"
                            + " info:https://{}:{}/{}/welcome",
                    this.hostnameProperty,
                    this.port,
                    contextPath);
        }
    }
}

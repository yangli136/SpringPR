/* (C)2023 */
package dev.springpr.springpr.base;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringPrBaseApplicationWebServerInitializedListener
        implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        if (log.isInfoEnabled()) {
            log.info(
                    "@@@ @@@ @@@ SpringPr Base Application - Web Server is ready at port:{}",
                    event.getWebServer().getPort());
        }
    }
}

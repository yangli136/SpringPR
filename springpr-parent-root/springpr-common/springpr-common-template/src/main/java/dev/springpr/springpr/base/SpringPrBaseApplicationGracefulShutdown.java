/* (C)2023 */
package dev.springpr.springpr.base;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpringPrBaseApplicationGracefulShutdown
        implements ApplicationListener<ContextClosedEvent>, ExitCodeGenerator {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("@@@ @@@ @@@ SpringPr Base Application is closed by event:{}.", event.getSource());
    }

    @Override
    public int getExitCode() {
        return 0;
    }
}

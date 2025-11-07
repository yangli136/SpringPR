/* (C)2023 */
package org.springpr.springpr.serviceinterface.resource.event.config;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.serviceinterface.resource.model.ResourceDtoEvent;

@Component
@Slf4j
public class ResourceDtoEventListener<T> {

    @EventListener
    void handleReturnedEvent(ResourceDtoEvent<T> event) {
        log.info("### ### ### ### ### ### Datadto:{} may be created or updated.", event.getDto());
    }
}

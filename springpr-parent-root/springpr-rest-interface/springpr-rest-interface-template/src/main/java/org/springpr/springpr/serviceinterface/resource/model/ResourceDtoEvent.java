/* (C)2023 */
package org.springpr.springpr.serviceinterface.resource.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class ResourceDtoEvent<T> {
    private final T dto;
}

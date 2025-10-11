/* (C)2023 */
package dev.springpr.springpr.base.message.service.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class Car {

    private final String color;
    private final String type;

    @JsonCreator
    public Car(@JsonProperty("color") String color, @JsonProperty("type") String type) {
        this.color = color;
        this.type = type;
    }
}

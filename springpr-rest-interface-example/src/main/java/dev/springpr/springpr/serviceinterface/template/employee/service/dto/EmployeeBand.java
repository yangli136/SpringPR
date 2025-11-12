/* (C)2023 */
package dev.springpr.springpr.serviceinterface.template.employee.service.dto;

import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmployeeBand {
    TWENTY(20),
    THIRTY(30),
    FOURTY(40),
    FIFTY(50);

    private final int band;

    EmployeeBand(int band) {
        this.band = band;
    }

    private static final Map<Integer, EmployeeBand> bandMap = new HashMap<>();

    static {
        bandMap.put(20, TWENTY);
        bandMap.put(30, THIRTY);
        bandMap.put(40, FOURTY);
        bandMap.put(50, FIFTY);
    }

    @JsonCreator
    public static @Nullable EmployeeBand forValue(int band) {
        return bandMap.get(band);
    }

    @JsonValue
    public int getBand() {
        return this.band;
    }
}

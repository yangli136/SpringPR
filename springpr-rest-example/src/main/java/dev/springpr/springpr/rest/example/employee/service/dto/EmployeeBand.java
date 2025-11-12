/* (C)2023 */
package dev.springpr.springpr.rest.example.employee.service.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmployeeBand {
    TWENTY(20),
    THIRTY(30),
    FOURTY(40),
    FIFTY(50);

    private int band;

    EmployeeBand(int band) {
        this.band = band;
    }

    private static Map<Integer, EmployeeBand> bandMap = new HashMap<>();

    static {
        bandMap.put(20, TWENTY);
        bandMap.put(30, THIRTY);
        bandMap.put(40, FOURTY);
        bandMap.put(50, FIFTY);
    }

    @JsonCreator
    public static EmployeeBand forValue(int band) {
        return bandMap.get(band);
    }

    @JsonValue
    public int getBand() {
        return this.band;
    }
}

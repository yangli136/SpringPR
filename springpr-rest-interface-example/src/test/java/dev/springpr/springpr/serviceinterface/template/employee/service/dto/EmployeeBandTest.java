/* (C)2025 */
package dev.springpr.springpr.serviceinterface.template.employee.service.dto;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class EmployeeBandTest {

    @Test
    void getBand() {
        int band = EmployeeBand.FOURTY.getBand();
        assertEquals(40, band);
    }

    @Test
    void forValue() {
        EmployeeBand band_40 = EmployeeBand.forValue(40);
        assertEquals(EmployeeBand.FOURTY, band_40);
    }
}

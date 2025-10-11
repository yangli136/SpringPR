/* (C)2023 */
package dev.springpr.springpr.base.validation;

// import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import jakarta.validation.ConstraintViolationException;

import dev.springpr.springpr.base.retry.BService;

@SpringBootTest(
        classes = {Application.class, ValidatingServiceWithGroups.class},
        properties = {"server.port=8080", "otel.instrumentation.annotations.enabled=false"})
class ValidatingServiceWithGroupsTest {
    @Autowired ValidatingServiceWithGroups service;
    @MockitoBean private BService bService;

    @Test
    void whenInputIsInvalidForCreate_thenThrowsException() {
        InputWithCustomValidator input = validInput();
        input.setId(42L);
        Assertions.assertThrows(
                ConstraintViolationException.class, () -> service.validateForCreate(input));
    }

    private InputWithCustomValidator validInput() {
        InputWithCustomValidator input = new InputWithCustomValidator();
        input.setNumberBetweenOneAndTen(1);
        input.setIpAddress("111.111.111.111");
        return input;
    }

    @Test
    void whenInputIsInvalidForUpdate_thenThrowsException() {
        InputWithCustomValidator input = validInput();
        input.setId(null);
        Assertions.assertThrows(
                ConstraintViolationException.class, () -> service.validateForUpdate(input));
    }
}

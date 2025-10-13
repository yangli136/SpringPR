/* (C)2023 */
package dev.springpr.springpr.base.example.user.model;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.springpr.springpr.base.example.user.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidationFalse() {
        final User user = new User();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        LOGGER.info("violations:{}", violations);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testValidationTrue() {
        final User user = new User();
        user.setId(1);
        user.setName("name");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        LOGGER.info("violations:{}", violations);
        assertTrue(violations.isEmpty());
    }
}

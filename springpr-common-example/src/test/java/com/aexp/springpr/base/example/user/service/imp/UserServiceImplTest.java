/* (C)2023 */
package org.springpr.springpr.base.example.user.service.imp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolationException;

import org.springpr.springpr.base.config.SpringPrBaseAsyncExceptionHandler;
import org.springpr.springpr.base.config.SpringPrBaseCommonBeansConfiguration;
import org.springpr.springpr.base.config.SpringPrBaseMetricsServiceCounterConfiguration;
import org.springpr.springpr.base.example.user.model.User;
import org.springpr.springpr.base.example.user.service.UserService;
import org.springpr.springpr.base.example.user.service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(
        classes = {
            SpringPrBaseAsyncExceptionHandler.class,
            SpringPrBaseCommonBeansConfiguration.class,
            SpringPrBaseMetricsServiceCounterConfiguration.class,
            UserServiceImpl.class
        },
        properties = {"SOLACE_ASISMSGHANDLER_COCURRENCY=3", "server.port=70000"})
@EnableAutoConfiguration
class UserServiceImplTest {
    @Autowired private UserService userService;

    @Test
    void whenInputIsInvalidThenThrowsException() {
        try {
            User user = null;

            assertThrows(ConstraintViolationException.class, () -> userService.getAccount(user));
        } catch (Exception e) {
        }
    }

    @Test
    void test() {
        assertTrue(true);
    }
}

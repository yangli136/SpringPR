/* (C)2023 */
package org.springpr.springpr.base.retry;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springpr.springpr.base.exception.RecoverableFailureException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@ActiveProfiles("retry")
class SpringRetryTest {

    @Autowired private AService aService;

    @MockitoBean private BService bService;

    @BeforeAll
    public static void setUp() {
        System.setProperty("spring.profiles.active", "retry");
    }

    /*
     * thenThrow(RecoverableFailureException.class, RecoverableFailureException.class,
     *          RecoverableFailureException.class, RecoverableFailureException.class,
     *          RecoverableFailureException.class)
     */
    @Test
    void retriesAfterOneFailAndThenPass() {
        aService.aFind("name");
        verify(bService, times(2)).bFind("name");
    }

    @Configuration
    @EnableRetry
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    @Profile("retry")
    public static class Application {
        private final BService bService;

        public Application(BService bService) {
            this.bService = bService;
        }

        @Bean
        AService aService() {
            when(bService.bFind("name"))
                    .thenThrow(RecoverableFailureException.class)
                    .thenReturn("AAA");
            return Mockito.spy(new AServiceImpl(bService));
        }

        @Bean
        RetryLoggingListener retryLoggingListener() {
            return new RetryLoggingListener();
        }
    }
}

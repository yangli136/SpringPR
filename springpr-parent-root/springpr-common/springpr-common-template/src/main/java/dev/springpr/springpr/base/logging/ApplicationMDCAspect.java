/* (C)2023 */
package dev.springpr.springpr.base.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

/** Aspect configuration to set the host and app info into mdc context */
@Aspect
@Configuration
@RequiredArgsConstructor
public class ApplicationMDCAspect {

    @SuppressWarnings("squid:S3749")
    private final Log4jMDCSetter log4jMdcSetter;

    @Pointcut(
            "execution(* *(..)) &&"
                    + " @annotation(dev.springpr.springpr.base.logging.Log4jDiagnosticContextEnable)")
    public void log4jDiagnosticContextEnabled() {
        // do nothing.
    }

    @Before("dev.springpr.springpr.base.logging.ApplicationMDCAspect.log4jDiagnosticContextEnabled()")
    public void setMdcToResourceBean(JoinPoint joinPoint) {
        log4jMdcSetter.setHostAndAppInfoIfMissing();
    }
}

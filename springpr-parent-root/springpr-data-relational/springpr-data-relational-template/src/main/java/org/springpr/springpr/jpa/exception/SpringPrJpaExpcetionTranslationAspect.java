/* (C)2023 */
package org.springpr.springpr.jpa.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.transaction.TransactionException;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.base.exception.RecoverableFailureException;
import org.springpr.springpr.base.exception.SpringPrApplicationException;

@Aspect
@Configuration
@Order(0)
@Slf4j
public class SpringPrJpaExpcetionTranslationAspect {
    @Pointcut("@within(org.springpr.springpr.jpa.stereotype.SpringPrJpaService)")
    public void anyJpaServiceMethod() {}

    @Before(
            "org.springpr.springpr.jpa.exception.SpringPrJpaExpcetionTranslationAspect.anyJpaServiceMethod()")
    public void beforeAdvice(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("\n======= Inside @Before() =======");
            log.info(joinPoint.getSignature().toShortString());
        }
    }

    @After(
            "org.springpr.springpr.jpa.exception.SpringPrJpaExpcetionTranslationAspect.anyJpaServiceMethod()")
    public void afterAdvice(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("\n======= Inside @After() =======");
            log.info(joinPoint.getSignature().toShortString());
        }
    }

    @AfterReturning(
            pointcut =
                    "org.springpr.springpr.jpa.exception.SpringPrJpaExpcetionTranslationAspect.anyJpaServiceMethod()",
            returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        if (log.isInfoEnabled()) {
            log.info("\n======= Inside @AfterReturning() =======");
            log.info(joinPoint.getSignature().toShortString());
            log.info("result: {}", result);
        }
    }

    @AfterThrowing(
            pointcut =
                    "org.springpr.springpr.jpa.exception.SpringPrJpaExpcetionTranslationAspect.anyJpaServiceMethod()",
            throwing = "ex")
    public void AfterThrowing(JoinPoint joinPoint, Throwable ex) {
        if (log.isInfoEnabled()) {
            log.info("\n=======  Inside @AfterThrowing() =======");
            log.info(joinPoint.getSignature().toShortString());
            log.info("ex: {}", ex);
        }
    }

    @Around(
            "org.springpr.springpr.jpa.exception.SpringPrJpaExpcetionTranslationAspect.anyJpaServiceMethod()")
    public Object intercept(ProceedingJoinPoint thisJoinPoint) {
        if (log.isInfoEnabled()) {
            log.info(
                    "intercepting a JPA exception inside Join Point: {}",
                    thisJoinPoint.toLongString());
        }
        try {
            return thisJoinPoint.proceed();
            // TransactionException
        } catch (TransactionException ex) {
            log.error(
                    "Exception translated to RecoverableFailureException from: {}.",
                    ex.getClass().getCanonicalName() + "[" + ex.getMessage() + "]",
                    ex);
            throw new RecoverableFailureException("JPA TransactionException.", ex);
            // DataAccessException
        } catch (TransientDataAccessException ex) {
            log.error(
                    "Exception translated to RecoverableFailureException from: {}.",
                    ex.getClass().getCanonicalName() + "[" + ex.getMessage() + "]",
                    ex);
            throw new RecoverableFailureException("JPA DataAccessException.", ex);
        } catch (NonTransientDataAccessResourceException ex) {
            log.error(
                    "Exception translated to RecoverableFailureException from: {}.",
                    ex.getClass().getCanonicalName() + "[" + ex.getMessage() + "]",
                    ex);
            throw new RecoverableFailureException("JPA DataAccessException.", ex);
        } catch (UncategorizedDataAccessException ex) {
            log.error(
                    "Exception translated to RecoverableFailureException from: {}.",
                    ex.getClass().getCanonicalName() + "[" + ex.getMessage() + "]",
                    ex);
            throw new RecoverableFailureException("JPA DataAccessException.", ex);
        } catch (ConstraintViolationException ex) {
            log.error(
                    "Exception translated to RecoverableFailureException from: {}.",
                    ex.getClass().getCanonicalName() + "[" + ex.getMessage() + "]",
                    ex);
            throw new SpringPrApplicationException("JPA DataAccessException.", ex);
        } catch (Throwable tr) {
            log.error(
                    "Exception translated to SpringPrApplicationException from: {}.",
                    tr.getClass().getCanonicalName() + "[" + tr.getMessage() + "]",
                    tr);
            throw new SpringPrApplicationException("JPA Other Exception.", tr);
        }
    }
}

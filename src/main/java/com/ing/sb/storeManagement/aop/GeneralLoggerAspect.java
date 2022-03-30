package com.ing.sb.storeManagement.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GeneralLoggerAspect {
    private Logger logger = LoggerFactory.getLogger(GeneralLoggerAspect.class);

    @Pointcut("execution(* com.ing.sb.storeManagement.handlers.GlobalExceptionHandler.*(..)) && args(exception)")
    private void pHandleGlobalException(Exception exception) {
    }

    @Before("pHandleGlobalException(exception)")
    public void handleGlobalExceptionLogging(JoinPoint jp, Exception exception) {
        handleExceptionLogging(jp, exception);
    }

    private void handleExceptionLogging(JoinPoint jp, Exception exception) {
        logger.info("[ ORIGINATOR EXCEPTION HANDLER ] {}", jp.getSignature());
        logger.info("[ EXCEPTION THROWN ] {} with message {}", exception.getClass(), exception.getMessage(), exception);
    }
}

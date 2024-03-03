package com.example.GB_JAVA_SpringCore_HW4.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class UserActionAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserActionAspect.class);

    @Before("@annotation(com.example.GB_JAVA_SpringCore_HW4.aspects.TrackUserAction)")
    public void trackUserAction(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("Method '{}' called with arguments: {}", methodName, Arrays.toString(methodArgs));
    }
}

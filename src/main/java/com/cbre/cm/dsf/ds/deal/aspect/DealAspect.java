package com.cbre.cm.dsf.ds.deal.aspect;

import javassist.bytecode.SignatureAttribute;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class DealAspect {
    private static final Logger logger = LoggerFactory.getLogger(DealAspect.class);


    @Around(value = "execution(* com.cbre.cm.dsf..*.*(..)) ")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        logger.debug(point.getSignature().getDeclaringType().getTypeName() + "::"+
                point.getSignature().getName() +":: parameters :: "+ Arrays.toString(point.getArgs()));
       Object result = point.proceed();
        logger.info(
                "Time taken to execute {}.{} :: {}",
                point.getSignature().getDeclaringType().getTypeName(),
                point.getSignature().getName(),
                System.currentTimeMillis() - start
        );
        logger.debug(point.getSignature().getDeclaringType().getTypeName() + "::"+
                point.getSignature().getName() +":: returnValue ::"+ result);

        return result;
    }


}

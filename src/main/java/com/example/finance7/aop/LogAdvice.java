package com.example.finance7.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAdvice {

    private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);

    @Around("execution(* com.example.finance7.cart.controller..*Controller*.*(..))"
            +" || execution(* com.example.finance7.cart..*Service*.*(..))"
            +" || execution(* com.example.finance7.cart..*Repository.*(..))"
            +" || execution(* com.example.finance7.member.controller..*Controller*.*(..))"
            +" || execution(* com.example.finance7.member..*Service*.*(..))"
            +" || execution(* com.example.finance7.member..*Repository.*(..))"
            +" || execution(* com.example.finance7.product.controller..*Controller*.*(..))"
            +" || execution(* com.example.finance7.product..*Service*.*(..))"
            +" || execution(* com.example.finance7.product..*Repository.*(..))")
    public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //객체명
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();

        //proceedingJoinPoint.getSignature().getName() <- 실행 메서드명
        log.info("[[START]]"+type+"."+proceedingJoinPoint.getSignature().getName()+"() <=================");
        log.info("Argument/Parameter : "+ Arrays.toString(proceedingJoinPoint.getArgs()));//<-파라미터
        log.info("================[[END : "+proceedingJoinPoint.getSignature().getName()+"()]]==================");

        return proceedingJoinPoint.proceed();
    }

}
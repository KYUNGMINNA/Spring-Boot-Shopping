package com.sale.shopping.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect

public class AOP {
    private static final Logger log = LoggerFactory.getLogger(AOP.class);

    @Around("execution(* com.sale.shopping..*.*(..))")
    public Object timeCheck(ProceedingJoinPoint proceedingJoinPoint){
        long start=System.currentTimeMillis();

        log.info("실향 클래스  :  "+proceedingJoinPoint.getTarget());
        log.info("실행 메서드 :  "+proceedingJoinPoint.getSignature().toString());
        log.info("매개값 :  "+ Arrays.toString(proceedingJoinPoint.getArgs()));

        //공통적으로 실행할 부분 작성 ~~
        Object result=null;

        try {
            result=proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }


        long end=System.currentTimeMillis();

        log.info("메서드 소요 시간 : "+ (end-start)*0.001 + "초");


        return result;
    }
}

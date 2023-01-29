package com.sale.shopping.config;

import com.sale.shopping.model.dto.CommonDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Component
@Aspect

public class AOP {
    private static final Logger log = LoggerFactory.getLogger(AOP.class);

    @Around("execution(* com.sale.shopping..*.*(..))")
    public Object timeCheck(ProceedingJoinPoint proceedingJoinPoint){
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();

        Object[] args = proceedingJoinPoint.getArgs();

        for (Object arg : args) {
            if(arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                // 서비스 : 정상적인 화면 -> 사용자요청
                Map<String, String> errorMap = null;
                if (bindingResult.hasErrors()) {
                    errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        // 로그 레벨 error, warn, info, debug
                        log.warn(type + "." + method + "() => 필드 : " + error.getField() + ", 메시지 : " + error.getDefaultMessage());
                        log.debug(type + "." + method + "() => 필드 : " + error.getField() + ", 메시지 : " + error.getDefaultMessage());


                    }

                }
                return new CommonDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
            }
        }
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

package ru.kpfu.itis.aspect;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Aspect
@Component
public class HandlingAspect {

    @Pointcut("@annotation(ru.kpfu.itis.aspect.CustomExceptionHandler)")
    public void handleException() {

    }

    @Around("handleException()")
    public Object handleExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            // Обработка исключения
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}

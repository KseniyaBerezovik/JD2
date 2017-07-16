package by.itacademy.aspect;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j
public class ServiceLogger {

    @Pointcut("execution(* by.itacademy.service.*(..))")
    public void serviceMethods(){}

    @Before(value = "serviceMethods()", argNames = "joinPoint")
    public void methodInvoke(JoinPoint joinPoint) {
        log.info("Method Name :" + joinPoint.getSignature().toShortString() + "| Args => " + Arrays.asList(joinPoint.getArgs()));
    }
}

package com.stussy.stussyclone20220930jugeon.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Code;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Slf4j //lombok에서 가져온 것. //log4j 내장되어있음
@Aspect
@Component
public class LogAop { //로그 찍어주고 로그파일 생성해줌

    @Pointcut("execution(* com.stussy.stussyclone20220930jugeon.api.*Api.*(..))")
    private void pointCut() {}

    @Pointcut("@annotation(com.stussy.stussyclone20220930jugeon.aop.annotation.LogAspect)")
    private void annotationPointCut() {}

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature(); // 다운캐스팅 가능
        String className = codeSignature.getDeclaringTypeName(); //클래스도 자료형이라서 typeName
        String methodName = codeSignature.getName();
        String[] parameterNames = codeSignature.getParameterNames(); //Names = String 배열 //이거 가져올려면 codesignature로 다운캐스팅 해야됨
        Object[] args = joinPoint.getArgs(); //매개변수 값 들고오기

        for(int i=0; i< parameterNames.length; i++) {
            log.info("<<<< Parameter info >>>> {}.{} >>> [{}: {}]", className, methodName, parameterNames[i], args[i]);
        }

        Object result = joinPoint.proceed();

        log.info("<<<< Return >>>> {}.{} >>> [{}]", className, methodName, result);

        return result;
    }
}

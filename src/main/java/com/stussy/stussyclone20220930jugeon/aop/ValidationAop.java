package com.stussy.stussyclone20220930jugeon.aop;

import com.stussy.stussyclone20220930jugeon.exception.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidationAop {
                                                            //.. = 하위 모든
    @Pointcut("execution(* com.stussy.stussyclone20220930jugeon..*Api.*(..))")
    // *모든 메소드에 적용해라. get* get으로 시작하는 메소드 적용, set* set으로 시작 하는,  *.*는 모든 클래스
    // *api.* api로 끝나는 클래스 모두
    // 패키지는 지우고 ..으로 하면 하위 모든 패키지 적용.

    private void executionPointCut(){}

    @Around("executionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        BeanPropertyBindingResult bindingResult =null;

        for(Object arg : args){
            if(arg.getClass() == BeanPropertyBindingResult.class){
                 bindingResult = (BeanPropertyBindingResult) arg;
                 break;
            }
        }

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<String, String>();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors){
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()); //키, 밸류
            }

            throw new CustomValidationException("Validation Error", errorMap);

        }

        Object result = null;
        result = joinPoint.proceed();

        return result;
    }
}

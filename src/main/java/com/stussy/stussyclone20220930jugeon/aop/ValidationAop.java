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
    @Pointcut("execution(* com.stussy.stussyclone20220930jugeon..*Api.*(..))") //get* = get으로 시작하는 메소드명 전체//(..) = 메소드만 적으면 된다.
                         //* = 모든 리턴 자료형 허용
    private void executionPointCut(){}


    @Around("executionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        BeanPropertyBindingResult bindingResult =null;

        for(Object arg : args){
            System.out.println(arg);
            if(arg.getClass() == BeanPropertyBindingResult.class){
                 bindingResult = (BeanPropertyBindingResult) arg;
                 break;
            }
        }

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<String, String>();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors){
                System.out.println("필드명 : " + fieldError.getField()); //키
                System.out.println("에러 메세지 : " + fieldError.getDefaultMessage()); //밸류
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()); //키, 밸류
            }

            throw new CustomValidationException("Validation Error", errorMap);

        }

        Object result = null;
        result = joinPoint.proceed();

        return result;
    }
}

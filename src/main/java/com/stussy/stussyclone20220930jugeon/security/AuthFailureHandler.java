package com.stussy.stussyclone20220930jugeon.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler implements AuthenticationFailureHandler { //여기서 예외처리 해준다.

    @Override //SecurityConfig에 추가해줘야됨 failureHandler
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception.getClass() == UsernameNotFoundException.class || exception.getClass() == BadCredentialsException.class){ //BadCredentialsException : 비번 틀렷을 때
            response.sendRedirect("/account/login?error=auth"); //새로운 요청을 날림, 무조건 get요청,
        }
        else if(exception.getClass() == CredentialsExpiredException.class){
            response.sendRedirect("/account/login?error=passwordExpired");
        }
        else{
            response.sendRedirect("/account/login?error");
        }
    }
}

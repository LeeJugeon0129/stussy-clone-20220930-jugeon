package com.stussy.stussyclone20220930jugeon.config;

import com.stussy.stussyclone20220930jugeon.security.AuthFailureHandler;
import com.stussy.stussyclone20220930jugeon.service.PrincipalOauth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@Configuration //config 나오면 달아줌
@RequiredArgsConstructor //DI할 때 필요
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOauth2Service principalOauth2Service;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests()
                .antMatchers("/account/mypage", "/index", "/checkout")
                .authenticated()
//                .antMatchers("/admin/**") //이 주소로 시작하는거는
//                .hasRole("ADMIN") //ADMIN 권한이 있어야 한다.
                .antMatchers("/admin/**", "/api/admin/**")
                .permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .loginPage("/account/login") // login page Get 요청
                .loginProcessingUrl("/account/login") // login service Post 요청
                .failureHandler(new AuthFailureHandler())
//                .successForwardUrl() //성공했을 때
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalOauth2Service)
                .and()
                .defaultSuccessUrl("/index");
    }
}

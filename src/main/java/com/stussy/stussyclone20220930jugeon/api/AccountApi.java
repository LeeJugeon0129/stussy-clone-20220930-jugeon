package com.stussy.stussyclone20220930jugeon.api;

import com.stussy.stussyclone20220930jugeon.aop.annotation.LogAspect;
import com.stussy.stussyclone20220930jugeon.dto.CMRespDto;
import com.stussy.stussyclone20220930jugeon.dto.RegisterReqDto;
import com.stussy.stussyclone20220930jugeon.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220930jugeon.exception.CustomValidationException;
import com.stussy.stussyclone20220930jugeon.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/account")
@RestController //@Controller + @ResponseBody
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;

    @LogAspect //여기에 aop 적용해라
    @PostMapping("/register")              //@RequestBody : json으로 받기 //@Valid : 유효성 검사
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) throws Exception {

        accountService.duplicateEmail(registerReqDto);
        accountService.register(registerReqDto);

        return ResponseEntity.created(URI.create("/account/login")).body(new CMRespDto<>("회원가입 성공", registerReqDto.getEmail())); //아이디 바로 입력 가능하게 해줌
    }
}

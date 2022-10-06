package com.stussy.stussyclone20220930jugeon.api;

import com.stussy.stussyclone20220930jugeon.dto.CMRespDto;
import com.stussy.stussyclone20220930jugeon.dto.RegisterReqDto;
import com.stussy.stussyclone20220930jugeon.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220930jugeon.exception.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/account")
@RestController //일반 컨트롤러에 리스폰스 바디를 항상 가지고 있음
public class AccountApi {

    @PostMapping("/register")              //@RequestBody : json으로 받기 //@Valid : 유효성 검사
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) {
        return ResponseEntity.created(null).body(new CMRespDto<>("회원가입 성공", registerReqDto));
    }
}

package com.stussy.stussyclone20220930jugeon.api;

import com.stussy.stussyclone20220930jugeon.dto.RegisterReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/account")
@RestController //일반 컨트롤러에 리스폰스 바디를 항상 가지고 있음
public class AccountApi {

    @PostMapping("/register")              //@RequestBody : json으로 받기
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto) {
        System.out.println("회원가입 요청 데이터 : " + registerReqDto);
        return null;
    }
}

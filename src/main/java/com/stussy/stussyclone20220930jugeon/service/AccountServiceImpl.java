package com.stussy.stussyclone20220930jugeon.service;

import com.stussy.stussyclone20220930jugeon.domain.User;
import com.stussy.stussyclone20220930jugeon.dto.RegisterReqDto;
import com.stussy.stussyclone20220930jugeon.exception.CustomValidationException;
import com.stussy.stussyclone20220930jugeon.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public void register(RegisterReqDto registerReqDto) throws Exception {

        User user = accountRepository.findUserByEmail(registerReqDto.getEmail());
        if(user !=null){
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("email", "이미 사용중인 이메일 주소입니다.");

            throw new CustomValidationException("Duplicate email", errorMap);
        }


    }
}

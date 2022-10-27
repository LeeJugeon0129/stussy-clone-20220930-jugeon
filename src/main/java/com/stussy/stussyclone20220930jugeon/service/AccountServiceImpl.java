package com.stussy.stussyclone20220930jugeon.service;

import com.stussy.stussyclone20220930jugeon.domain.User;
import com.stussy.stussyclone20220930jugeon.dto.RegisterReqDto;
import com.stussy.stussyclone20220930jugeon.exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220930jugeon.exception.CustomValidationException;
import com.stussy.stussyclone20220930jugeon.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor //IoC에서 가져옴 //final 상수 //매개변수로 필수로 들어와야된다.
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public void duplicateEmail(RegisterReqDto registerReqDto) throws Exception {
        //이메일 중복확인
        User user = accountRepository.findUserByEmail(registerReqDto.getEmail()); //entity

        if(user !=null){
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("email", "이미 사용중인 이메일 주소입니다.");

            throw new CustomValidationException("Duplicate email", errorMap); //handler가 잡음
        }
    }

    @Override
    public void register(RegisterReqDto registerReqDto) throws Exception {
        //회원가입 진행 (예외 발생 x 시)
        User user = registerReqDto.toEntity();
        int result = accountRepository.saveUser(user);

        if(result == 0){ //db 안들어갓으면 예외 발생해라.
            throw new CustomInternalServerErrorException("회원가입 중 문제가 발생하였습니다.");
        }
    }

}

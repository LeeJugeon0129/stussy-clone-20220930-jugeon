package com.stussy.stussyclone20220930jugeon.service;

import com.stussy.stussyclone20220930jugeon.dto.RegisterReqDto;

public interface AccountService {

    public void duplicateEmail(RegisterReqDto registerReqDto) throws Exception;
    public void register(RegisterReqDto registerReqDto) throws Exception;

}

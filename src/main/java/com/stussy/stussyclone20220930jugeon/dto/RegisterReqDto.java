package com.stussy.stussyclone20220930jugeon.dto;

import lombok.Data;

@Data //들어오는 거 (getter, setter)
public class RegisterReqDto {

    private String lastName;
    private String firstName;
    private String email;
    private String password;
}

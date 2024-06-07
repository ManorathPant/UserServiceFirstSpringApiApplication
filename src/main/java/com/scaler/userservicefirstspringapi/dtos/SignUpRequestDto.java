package com.scaler.userservicefirstspringapi.dtos;

import lombok.*;

@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String name;
    private  String password;
}

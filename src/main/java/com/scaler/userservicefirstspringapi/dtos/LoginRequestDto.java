package com.scaler.userservicefirstspringapi.dtos;

import lombok.*;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}

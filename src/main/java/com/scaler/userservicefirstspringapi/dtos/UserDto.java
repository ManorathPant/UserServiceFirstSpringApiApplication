package com.scaler.userservicefirstspringapi.dtos;

import com.scaler.userservicefirstspringapi.models.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class UserDto {
    private String email;
    private String name;
    private List<Role> roles;

    public static UserDto from(User user)
    {
        if(user == null)
        {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRoles(user.getRoles());
        return dto;
    }
}

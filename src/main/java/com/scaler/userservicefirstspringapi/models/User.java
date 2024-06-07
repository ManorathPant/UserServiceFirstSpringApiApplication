package com.scaler.userservicefirstspringapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String hashedPassword;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;
}

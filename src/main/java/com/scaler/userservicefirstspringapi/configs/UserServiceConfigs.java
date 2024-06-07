package com.scaler.userservicefirstspringapi.configs;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.*;

@Configuration
public class UserServiceConfigs {
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

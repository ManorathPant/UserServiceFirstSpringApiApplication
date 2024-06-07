package com.scaler.userservicefirstspringapi.controllers;

import com.scaler.userservicefirstspringapi.dtos.*;
import com.scaler.userservicefirstspringapi.models.*;
import com.scaler.userservicefirstspringapi.repositories.*;
import com.scaler.userservicefirstspringapi.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private final TokenRepository tokenRepository;

    UserController(UserService userService,
                   TokenRepository tokenRepository){
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto){
        User user = userService.signUp(
                requestDto.getEmail(),
                requestDto.getName(),
                requestDto.getPassword()
        );
        return UserDto.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto){
        Token token = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        return token;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto) {
        userService.logout(requestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token)
    {
        User user = userService.validateToken(token);
        return UserDto.from(user);
    }
}

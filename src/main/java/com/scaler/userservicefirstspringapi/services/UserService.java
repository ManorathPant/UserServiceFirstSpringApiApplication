package com.scaler.userservicefirstspringapi.services;

import com.scaler.userservicefirstspringapi.exceptions.*;
import com.scaler.userservicefirstspringapi.models.*;
import com.scaler.userservicefirstspringapi.repositories.*;
import org.apache.commons.lang3.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;

    UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                UserRepository userRepository,
                TokenRepository tokenRepository)
    {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }
    public  User signUp(String email,
                        String name,
                        String pass)
    {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(pass));
        user.setEmailVerified(true);

        //save the user object to the database
        return userRepository.save(user);
    }

    public Token login(String email, String password)
    {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty())
        {
            throw  new UserNotFoundException("User with email " + email + " doesn't exist");
        }

        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password,user.getHashedPassword()))
        {
            //throw some exception
            return null;
        }
        //Login Successful
        Token token = generateToken(user);
        Token savedToken = tokenRepository.save(token);
        return savedToken;
    }

    private Token generateToken(User user){
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDayLaterDate = currentDate.plusDays(30);

        Date expiryDate = Date.from(thirtyDayLaterDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Token token = new Token();
        token.setExpiryAt(expiryDate);
        token.setValue(RandomStringUtils.randomAlphabetic(128));
        token.setUser(user);
        return token;
    }

    public void logout(String tokenValue){
        Optional<Token> optionalUser = tokenRepository.findByValueAndDeleted(tokenValue,false);

        if(optionalUser.isEmpty())
        {
            //Throw some excepion
            return;
        }

        Token token = optionalUser.get();
        token.setDeleted(true);
        tokenRepository.save(token);


    }

    public User validateToken(String tokenValue){
        Optional<Token> optionalUser = tokenRepository
                .findByValueAndDeletedAndExpiryAtGreaterThan(tokenValue,false,new Date());
        if(optionalUser.isEmpty())
        {
            //Throw some excepion
            return null;
        }
        return optionalUser.get().getUser();
    }
}

package com.scaler.userservicefirstspringapi.repositories;

import com.scaler.userservicefirstspringapi.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.swing.text.html.*;
import java.util.*;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Override
    Token save(Token token);

    Optional<Token> findByValueAndDeleted(String tokenValue, boolean deleted);

    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String token,boolean deleted,Date currentTime);
}

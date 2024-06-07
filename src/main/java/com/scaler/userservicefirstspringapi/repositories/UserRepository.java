package com.scaler.userservicefirstspringapi.repositories;

import com.scaler.userservicefirstspringapi.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    User save(User user);

    Optional<User> findByEmail(String email);
}

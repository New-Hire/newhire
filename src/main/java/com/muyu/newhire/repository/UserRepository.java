package com.muyu.newhire.repository;

import com.muyu.newhire.model.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {

    @Nullable
    User findByAccount(String account);

}

package com.muyu.newhire.service;

import com.muyu.newhire.model.User;
import com.muyu.newhire.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Nullable
    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

}

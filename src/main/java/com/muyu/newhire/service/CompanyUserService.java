package com.muyu.newhire.service;

import com.muyu.newhire.model.User;
import com.muyu.newhire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyUserService {

    private final UserService userService;

}

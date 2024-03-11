package com.muyu.newhire.service;

import com.muyu.newhire.model.User;
import com.muyu.newhire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByAccount(String account) throws UsernameNotFoundException {
        return userRepository.findByAccount(account)
                .orElseThrow(() -> new UsernameNotFoundException("account not found with account: " + account));
    }

    public User findById(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id not found with id: " + id));
    }

    public List<User> findAllByIds(List<Long> ids) {
        return userRepository.findAllByIdIn(ids);
    }

}

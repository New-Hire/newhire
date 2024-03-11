package com.muyu.newhire.service;

import com.muyu.newhire.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    public String getToken(String account, String password) {
        var user = this.userService.findByAccount(account);
        return this.jwtTokenProvider.generateToken(
                user.getId().toString(),
                Map.of("account", user.getAccount(), "role", user.getRole(),
                        "currentCompanyId", user.getCurrentCompanyId() == null ? 0 : user.getCurrentCompanyId())
        );
    }

}

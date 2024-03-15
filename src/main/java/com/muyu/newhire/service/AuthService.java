package com.muyu.newhire.service;

import com.muyu.newhire.dto.GetAuthTokenDto;
import com.muyu.newhire.model.User;
import com.muyu.newhire.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    public GetAuthTokenDto login(String account, String password) throws Exception {
        var user = this.userService.findByAccount(account);
        // TODO: 加密
        if (!user.getPassword().equals(password)) {
            throw new Exception("密码不正确");
        }
        // TODO: 拦截没有公司的HR
        var token = getToken(user);
        return new GetAuthTokenDto(
                user.getId(),
                user.getRole(),
                token
        );

    }

    private String getToken(User user) {
        return this.jwtTokenProvider.generateToken(
                user.getId().toString(),
                Map.of("account", user.getAccount(), "role", user.getRole(),
                        "currentCompanyId", user.getCurrentCompanyId() == null ? 0 : user.getCurrentCompanyId())
        );
    }

}

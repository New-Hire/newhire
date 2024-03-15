package com.muyu.newhire.controller;

import com.muyu.newhire.auth.CurrentUser;
import com.muyu.newhire.dto.AuthTokenDto;
import com.muyu.newhire.dto.GetAuthTokenDto;
import com.muyu.newhire.pojo.AuthUser;
import com.muyu.newhire.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/token")
    public GetAuthTokenDto getToken(@RequestBody AuthTokenDto authTokenDto) throws Exception {
        return authService.login(authTokenDto.getAccount(), authTokenDto.getPassword());
    }

    @GetMapping(value = "/user")
    public AuthUser getUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return new AuthUser(
                currentUser.getUserId(),
                currentUser.getAccount()
        );
    }

}

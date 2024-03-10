package com.muyu.newhire.controller;

import com.muyu.newhire.dto.GetAuthUserDto;
import com.muyu.newhire.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public GetAuthUserDto getUser(@AuthenticationPrincipal UserDetails userDetails) throws Exception {
        var user = userService.findByAccount(userDetails.getUsername());
        if (user == null) {
            throw new Exception("用户不存在");
        }
        return new GetAuthUserDto(user.getAccount(), user.getType());
    }

}

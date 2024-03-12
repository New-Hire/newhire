package com.muyu.newhire.controller;

import com.muyu.newhire.auth.CurrentUser;
import com.muyu.newhire.dto.QueryUserDto;
import com.muyu.newhire.dto.RateUserDto;
import com.muyu.newhire.model.User;
import com.muyu.newhire.pojo.PageData;
import com.muyu.newhire.service.RateService;
import com.muyu.newhire.service.UserService;
import com.muyu.newhire.util.PageableUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CompanyUserController", description = "企业员工")
@RestController
@RequestMapping(value = "/company/users")
@RequiredArgsConstructor
public class CompanyUserController {

    private final UserService userService;
    private final RateService rateService;

    @GetMapping()
    public PageData<User> getUsers(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @AuthenticationPrincipal CurrentUser currentUser
    ) {
        var pageable = PageableUtil.of(pageNumber, pageSize);
        var query = QueryUserDto
                .builder()
                .companyId(currentUser.getCurrentCompanyId())
                .build();
        var userPage = userService.findAll(query, pageable);
        return new PageData<>(userPage);
    }

    @PostMapping("/{userId}/rate")
    public void rateUser(
            @PathVariable Long userId,
            @RequestBody RateUserDto rateUserDto,
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        rateService.rateUser(
                currentUser.getUserId(),
                currentUser.getCurrentCompanyId(),
                userId,
                rateUserDto.getLevel()
        );
    }

}

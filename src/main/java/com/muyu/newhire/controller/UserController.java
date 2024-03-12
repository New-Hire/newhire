package com.muyu.newhire.controller;

import com.muyu.newhire.auth.CurrentUser;
import com.muyu.newhire.dto.GetAuthUserDto;
import com.muyu.newhire.dto.GetRegisterCompanyDto;
import com.muyu.newhire.service.CompanyCandidateService;
import com.muyu.newhire.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "UserController", description = "人才")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyCandidateService companyCandidateService;

    @GetMapping()
    public GetAuthUserDto getUser(@AuthenticationPrincipal CurrentUser currentUser) throws Exception {
        var user = userService.findById(currentUser.getUserId());
        return new GetAuthUserDto(user.getId(), user.getAccount(), user.getRole());
    }

    @GetMapping("/register_companies")
    public List<GetRegisterCompanyDto> getUseRregisterCompanies(@AuthenticationPrincipal CurrentUser currentUser) throws Exception {
        return companyCandidateService.getUserRegisterCompanies(currentUser.getUserId());
    }

    //  TODO：
//    @Operation(summary = "请求他人帮忙推荐")
//    @GetMapping(value = "/recommend_help", produces = MediaType.TEXT_PLAIN_VALUE)
//    public String getCompanyJoinToken(
//            @AuthenticationPrincipal CurrentUser currentUser
//    ) throws Exception {
//        return this.companyService.getCompanyJoinToken(companyId, currentUser.getUserId());
//    }

}

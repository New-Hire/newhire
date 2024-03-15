package com.muyu.newhire.controller;

import com.muyu.newhire.auth.CurrentUser;
import com.muyu.newhire.dto.GetAuthUserDto;
import com.muyu.newhire.dto.GetRegisterCompanyDto;
import com.muyu.newhire.dto.RateUserDto;
import com.muyu.newhire.provider.JwtTokenProvider;
import com.muyu.newhire.service.CompanyCandidateService;
import com.muyu.newhire.service.RateService;
import com.muyu.newhire.service.RecommendSignService;
import com.muyu.newhire.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "UserController", description = "人才")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyCandidateService companyCandidateService;
    private final RecommendSignService recommendSignService;
    private final RateService rateService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping()
    public GetAuthUserDto getUser(@AuthenticationPrincipal CurrentUser currentUser) throws Exception {
        var user = userService.findById(currentUser.getUserId());
        return new GetAuthUserDto(user.getId(), user.getName(), user.getAccount(), user.getRole());
    }

    @GetMapping("/register_companies")
    public List<GetRegisterCompanyDto> getUseRregisterCompanies(@AuthenticationPrincipal CurrentUser currentUser) throws Exception {
        return companyCandidateService.getUserRegisterCompanies(currentUser.getUserId());
    }

    @Operation(summary = "请求他人帮忙推荐")
    @GetMapping(value = "/recommend_sign", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getRecommendSign(
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        return this.recommendSignService.getRecommendSign(currentUser.getUserId());
    }

    @Operation(summary = "同意他人请求，帮助其推荐")
    @PostMapping("/recommend_approve")
    public void approveCompanyRegister(
            @RequestParam String sign,
            @RequestParam Long userId,
            @RequestBody RateUserDto rateUserDto,
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        var claims = jwtTokenProvider.verifyAndGetSubjectFromToken(sign);
        var signedUserId = Long.parseUnsignedLong(claims.getSubject());
        if (userId != signedUserId) {
            throw new Exception("签名不合法");
        }
        rateService.rateUser(
                currentUser.getUserId(),
                currentUser.getCurrentCompanyId(),
                signedUserId,
                rateUserDto.getScore1()
        );
    }

}

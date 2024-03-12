package com.muyu.newhire.controller;

import com.muyu.newhire.auth.CurrentUser;
import com.muyu.newhire.constant.RoleAuthorizeConst;
import com.muyu.newhire.provider.JwtTokenProvider;
import com.muyu.newhire.provider.SignProvider;
import com.muyu.newhire.service.JoinCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Tag(name = "CompanyRegisterController", description = "企业员工登记")
@RestController
@RequestMapping(value = "/company/registers")
@RequiredArgsConstructor
public class CompanyRegisterController {

    private final SignProvider signProvider;
    private final JoinCompanyService joinCompanyService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "[HR] 同意该未登记的员工的请求，让其进入企业")
    @PreAuthorize(RoleAuthorizeConst.HR)
    @PostMapping("/approve")
    public void approveCompanyRegister(
            @RequestParam String sign,
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        var claims = jwtTokenProvider.verifyAndGetSubjectFromToken(sign);
        if (!Objects.equals(claims.get("companyId"), currentUser.getCurrentCompanyId())) {
            throw new Exception("签名不合法");
        }
        joinCompanyService.join(currentUser.getCurrentCompanyId(), Long.parseUnsignedLong(claims.getSubject()));
    }

}

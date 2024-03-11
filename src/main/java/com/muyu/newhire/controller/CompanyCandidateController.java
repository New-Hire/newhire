package com.muyu.newhire.controller;

import com.muyu.newhire.auth.CurrentUser;
import com.muyu.newhire.constant.RoleAuthorizeConst;
import com.muyu.newhire.dto.GetCompanyCandidateDto;
import com.muyu.newhire.service.CompanyCandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CompanyCandidateController", description = "企业候选人")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class CompanyCandidateController {

    private final CompanyCandidateService companyCandidateService;

    @GetMapping("/companies/{companyId}/register")
    public void register(
            @PathVariable long companyId,
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        companyCandidateService.register(currentUser.getUserId(), companyId);
    }

    @Operation(summary = "[HR] 获取企业的候选人列表")
    @PreAuthorize(RoleAuthorizeConst.HR)
    @GetMapping("/company/candidates")
    public List<GetCompanyCandidateDto> getCompanyCandidates(
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        return companyCandidateService.getCompanyCandidates(currentUser.getUserId());
    }

    @Operation(summary = "[HR] 邀请候选人")
    @PreAuthorize(RoleAuthorizeConst.HR)
    @PostMapping("/company/candidates/{userId}/invite")
    public void inviteCompanyCandidate(
            @PathVariable long userId,
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        companyCandidateService.invite(currentUser.getCurrentCompanyId(), userId);
    }

}

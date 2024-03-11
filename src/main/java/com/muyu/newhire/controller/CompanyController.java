package com.muyu.newhire.controller;

import com.muyu.newhire.auth.CurrentUser;
import com.muyu.newhire.model.Company;
import com.muyu.newhire.pojo.PageData;
import com.muyu.newhire.service.CompanyService;
import com.muyu.newhire.util.PageableUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CompanyController", description = "企业")
@RestController
@RequestMapping(value = "/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping()
    public PageData<Company> getCompanies(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        var pageable = PageableUtil.of(pageNumber, pageSize);
        var companyPage = companyService.findAll(pageable);
        return new PageData<>(companyPage);
    }

    @GetMapping("/{companyId}")
    public Company getCompany(
            @PathVariable long companyId,
            @AuthenticationPrincipal CurrentUser currentUser
    ) throws Exception {
        return companyService.findById(companyId);
    }

}

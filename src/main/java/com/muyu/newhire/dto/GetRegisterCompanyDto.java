package com.muyu.newhire.dto;

import com.muyu.newhire.model.Company;
import com.muyu.newhire.model.CompanyCandidate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetRegisterCompanyDto {
    @NotNull
    private Long companyId;
    @NotNull
    private Company company;
    private CompanyCandidate.CandidateStatus status;
}

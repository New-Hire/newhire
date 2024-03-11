package com.muyu.newhire.dto;

import com.muyu.newhire.model.CompanyCandidate;
import com.muyu.newhire.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetCompanyCandidateDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long companyId;
    @NotNull
    private User user;
    private CompanyCandidate.CandidateStatus status;
}

package com.muyu.newhire.service;

import com.muyu.newhire.model.UserCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateService {

    private final UserRecommendService userRecommendService;
    private final UserCompanyService userCompanyService;
    private final UserService userService;

    public void rateUser(
            long raterId,
            long raterCompanyId,
            long userId,
            int level
    ) throws Exception {
        var raterCompanies = userCompanyService.getUserCurrentAndBeforeCompany(raterId);
        var raterCompanyIds = raterCompanies.stream().map(UserCompany::getId).toList();
        var user = userService.findById(userId);
        // 仅允许当前企业员工给其企业以及上家企业员工评价
        if (!raterCompanyIds.contains(user.getCurrentCompanyId())) {
            throw new Exception("被评者与中正官没有关系");
        }
        userRecommendService.create(raterId, raterCompanyId, userId, user.getCurrentCompanyId(), level);
    }

}

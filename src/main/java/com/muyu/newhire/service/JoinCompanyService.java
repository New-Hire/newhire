package com.muyu.newhire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinCompanyService {

    private final UserCompanyService userCompanyService;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public void join(long companyId, long userId) {
        userService.updateCurrentCompany(userId, companyId);
        userCompanyService.create(companyId, userId);
    }

}

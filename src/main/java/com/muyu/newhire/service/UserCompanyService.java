package com.muyu.newhire.service;

import com.muyu.newhire.model.UserCompany;
import com.muyu.newhire.repository.UserCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCompanyService {

    private final UserCompanyRepository userCompanyRepository;

    private UserCompany create(long companyId, long userId, int order) {
        var userCompany = new UserCompany(companyId, userId, order);
        return userCompanyRepository.save(userCompany);
    }

    public UserCompany create(long companyId, long userId) {
        int order = 1;
        var userCompany = userCompanyRepository
                .findFirstByUserIdAndCompanyId(userId, companyId, Sort.by(Sort.Order.desc("order")))
                .orElse(null);
        if (userCompany != null) {
            order = userCompany.getOrder() + 1;
        }
        return create(companyId, userId, order);
    }

    public List<UserCompany> findAllByUserId(long userId) {
        return userCompanyRepository.findAllByUserId(userId);
    }

    /**
     * 获取员工当前企业和上任企业
     *
     * @param userId 员工
     * @return List<UserCompany> 数量<=2
     */
    public List<UserCompany> getUserCurrentAndBeforeCompany(long userId) {
        return userCompanyRepository.findFirst2ByUserId(userId, Sort.by(Sort.Order.desc("order")));
    }

}

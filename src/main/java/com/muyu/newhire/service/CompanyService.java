package com.muyu.newhire.service;

import com.muyu.newhire.model.Company;
import com.muyu.newhire.provider.JwtTokenProvider;
import com.muyu.newhire.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public List<Company> findAllByIds(List<Long> ids) {
        return companyRepository.findAllByIdIn(ids);
    }

    public Company findById(long id) throws Exception {
        return companyRepository.findById(id)
                .orElseThrow(() -> new Exception("找不到"));
    }

    public boolean existById(long id) {
        return companyRepository.existsById(id);
    }

    public String getCompanyJoinToken(long companyId, long userId) throws Exception {
        var user = userService.findById(userId);
        if (user.getCurrentCompanyId() != null) {
            throw new Exception("已经有归属企业了");
        }
        return jwtTokenProvider.generateToken(
                String.valueOf(userId), Map.of("companyId", companyId), 7200000
        );
    }

}

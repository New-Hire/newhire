package com.muyu.newhire.service;

import com.muyu.newhire.constant.RateCalcStatus;
import com.muyu.newhire.dto.GetCompanyCandidateDto;
import com.muyu.newhire.dto.GetRegisterCompanyDto;
import com.muyu.newhire.model.Company;
import com.muyu.newhire.model.CompanyCandidate;
import com.muyu.newhire.model.User;
import com.muyu.newhire.model.UserScore;
import com.muyu.newhire.repository.CompanyCandidateLockRepository;
import com.muyu.newhire.repository.CompanyCandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyCandidateService {

    private final CompanyCandidateRepository companyCandidateRepository;
    private final CompanyCandidateLockRepository companyCandidateLockRepository;
    private final CompanyService companyService;
    private final UserService userService;
    private final JoinCompanyService joinCompanyService;
    private final UserScoreService userScoreService;

    public void register(long userId, long companyId) throws Exception {
        var companyExist = companyService.existById(companyId);
        if (!companyExist) {
            throw new Exception("企业不存在");
        }
        var exist = companyCandidateRepository.existsByCompanyIdAndUserId(companyId, userId);
        if (exist) {
            throw new Exception("记录已存在");
        }
        create(companyId, userId);
    }

    public List<GetRegisterCompanyDto> getUserRegisterCompanies(long userId) throws Exception {
        var companyCandidates = companyCandidateRepository.findAllByUserId(userId);
        if (companyCandidates.isEmpty()) {
            return Collections.emptyList();
        }
        var companyIds = companyCandidates.stream().map(CompanyCandidate::getCompanyId).toList();
        var companies = this.companyService.findAllByIds(companyIds);
        Map<Long, Company> companyMap = companies.stream().collect(Collectors.toMap(Company::getId, v -> v));

        List<GetRegisterCompanyDto> result = new ArrayList<>();
        for (var companyCandidate : companyCandidates) {
            var company = companyMap.get(companyCandidate.getCompanyId());
            if (company == null) continue;
            result.add(new GetRegisterCompanyDto(
                    companyCandidate.getCompanyId(),
                    company,
                    companyCandidate.getStatus()
            ));
        }
        return result;
    }

    public List<GetCompanyCandidateDto> getCompanyCandidates(long companyId) throws Exception {
        var companyCandidates = companyCandidateRepository.findAllByCompanyId(companyId);
        if (companyCandidates.isEmpty()) {
            return Collections.emptyList();
        }

        var userIds = companyCandidates.stream().map(CompanyCandidate::getUserId).toList();

        // 充填User数据
        var users = this.userService.findAllByIds(userIds);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, v -> v));
        // 充填User评分
        var userScores = userScoreService.findAllByUserIds(userIds);
        Map<Long, Integer> userScoreMap = userScores.stream().collect(Collectors.toMap(UserScore::getUserId, UserScore::getScore));

        List<GetCompanyCandidateDto> result = new ArrayList<>();
        for (var companyCandidate : companyCandidates) {
            var user = userMap.get(companyCandidate.getUserId());
            if (user == null) continue;
            var score = userScoreMap.get(companyCandidate.getUserId());

            result.add(new GetCompanyCandidateDto(
                    companyCandidate.getUserId(),
                    companyCandidate.getCompanyId(),
                    user,
                    companyCandidate.getStatus(),
                    RateCalcStatus.READY,
                    score != null ? score : 0
            ));
        }
        return result;
    }

    public CompanyCandidate findById(long id) throws Exception {
        return companyCandidateRepository.findById(id).orElseThrow(() -> new Exception("记录不存在"));
    }

    public CompanyCandidate findByCompanyIdAndUserId(long companyId, long userId) throws Exception {
        return companyCandidateRepository.findByCompanyIdAndUserId(companyId, userId).orElseThrow(() -> new Exception("记录不存在"));
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public CompanyCandidate findByCompanyIdAndUserIdForUpdate(long companyId, long userId) throws Exception {
        return companyCandidateLockRepository.findByCompanyIdAndUserId(companyId, userId).orElseThrow(() -> new Exception("记录不存在"));
    }

    @Transactional(rollbackFor = Exception.class)
    public void invite(long companyId, long userId) throws Exception {
        var companyCandidate = findByCompanyIdAndUserIdForUpdate(companyId, userId);
        if (companyCandidate.getStatus() != CompanyCandidate.CandidateStatus.INITIALIZED) {
            throw new Exception("状态不合法");
        }
        updateStatus(companyCandidate, CompanyCandidate.CandidateStatus.INVITED);
    }

    @Transactional(rollbackFor = Exception.class)
    public void approve(long companyId, long userId) throws Exception {
        var companyCandidate = findByCompanyIdAndUserIdForUpdate(companyId, userId);
        if (companyCandidate.getStatus() != CompanyCandidate.CandidateStatus.INVITED) {
            throw new Exception("状态不合法");
        }
        updateStatus(companyCandidate, CompanyCandidate.CandidateStatus.APPROVED);
        joinCompanyService.join(companyId, userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void refuse(long companyId, long userId) throws Exception {
        var companyCandidate = findByCompanyIdAndUserIdForUpdate(companyId, userId);
        if (companyCandidate.getStatus() == CompanyCandidate.CandidateStatus.APPROVED ||
                companyCandidate.getStatus() == CompanyCandidate.CandidateStatus.REJECTED) {
            throw new Exception("状态不合法");
        }
        updateStatus(companyCandidate, CompanyCandidate.CandidateStatus.REJECTED);
    }

    private CompanyCandidate create(long companyId, long userId) {
        var companyCandidate = new CompanyCandidate(companyId, userId);
        return companyCandidateRepository.save(companyCandidate);
    }

    private CompanyCandidate updateStatus(CompanyCandidate companyCandidate, CompanyCandidate.CandidateStatus status) {
        companyCandidate.setStatus(status);
        return companyCandidateRepository.save(companyCandidate);
    }

}

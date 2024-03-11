package com.muyu.newhire.repository;

import com.muyu.newhire.model.CompanyCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyCandidateRepository extends JpaRepository<CompanyCandidate, Long> {

    boolean existsByCompanyIdAndUserId(Long companyId, Long userId);

    List<CompanyCandidate> findAllByUserId(Long userId);

    List<CompanyCandidate> findAllByCompanyId(Long companyId);

    Optional<CompanyCandidate> findByCompanyIdAndUserId(Long companyId, Long userId);

}

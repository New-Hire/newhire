package com.muyu.newhire.repository;

import com.muyu.newhire.model.CompanyCandidate;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyCandidateLockRepository extends JpaRepository<CompanyCandidate, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CompanyCandidate> findByCompanyIdAndUserId(Long companyId, Long userId);

}

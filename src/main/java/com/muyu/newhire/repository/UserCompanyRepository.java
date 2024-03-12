package com.muyu.newhire.repository;

import com.muyu.newhire.model.UserCompany;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {

    Optional<UserCompany> findFirstByUserIdAndCompanyId(Long userId, Long companyId, Sort sort);

}

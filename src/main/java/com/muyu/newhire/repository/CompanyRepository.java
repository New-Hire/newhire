package com.muyu.newhire.repository;

import com.muyu.newhire.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByIdIn(List<Long> ids);

}

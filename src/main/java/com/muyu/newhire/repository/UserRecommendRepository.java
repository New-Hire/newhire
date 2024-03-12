package com.muyu.newhire.repository;

import com.muyu.newhire.model.UserRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecommendRepository extends JpaRepository<UserRecommend, Long> {

}

package com.muyu.newhire.repository;

import com.muyu.newhire.model.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

    List<UserScore> findAllByUserIdIn(List<Long> ids);

}

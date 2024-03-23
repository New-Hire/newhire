package com.muyu.newhire.service;

import com.muyu.newhire.model.UserScore;
import com.muyu.newhire.repository.UserScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserScoreService {

    private final UserScoreRepository userScoreRepository;

    public List<UserScore> findAllByUserIds(List<Long> userIds) {
        return userScoreRepository.findAllByUserIdIn(userIds);
    }

}

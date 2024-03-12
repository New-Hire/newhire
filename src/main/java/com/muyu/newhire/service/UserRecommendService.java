package com.muyu.newhire.service;

import com.muyu.newhire.model.UserRecommend;
import com.muyu.newhire.repository.UserRecommendRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRecommendService {

    private final UserRecommendRepository userRecommendRepository;

    public void create(
            long raterId,
            long raterCompanyId,
            long userId,
            @Nullable Long userCompanyId,
            int level
    ) {
        UserRecommend userRecommend = new UserRecommend(
                raterId, raterCompanyId, userId, userCompanyId, level
        );
        userRecommendRepository.save(userRecommend);
    }

}

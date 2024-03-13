package com.muyu.newhire.service;

import com.muyu.newhire.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecommendSignService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public String getRecommendSign(long userId) {
        return jwtTokenProvider.generateToken(
                String.valueOf(userId), Map.of(), 7200000
        );
    }

    public String getCompanyJoinSign(long companyId, long userId) throws Exception {
        var user = userService.findById(userId);
        if (user.getCurrentCompanyId() != null) {
            throw new Exception("已经有归属企业了");
        }
        return jwtTokenProvider.generateToken(
                String.valueOf(userId), Map.of("companyId", companyId), 7200000
        );
    }

}

package com.muyu.newhire.repository.spec;

import com.muyu.newhire.dto.QueryUserDto;
import com.muyu.newhire.model.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> byCurrentCompanyId(@Nullable Long currentCompanyId) {
        return (root, cq, cb) ->
                currentCompanyId != null ? cb.equal(root.get("currentCompanyId"), currentCompanyId) : null;
    }

    public static Specification<User> byAll(QueryUserDto query) {
        return byCurrentCompanyId(query.getCurrentCompanyId());
    }

}

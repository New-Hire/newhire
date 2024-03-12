package com.muyu.newhire.repository.spec;

import com.muyu.newhire.dto.QueryUserDto;
import com.muyu.newhire.model.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> byCompanyId(@Nullable Long companyId) {
        return (root, cq, cb) ->
                companyId != null ? cb.equal(root.get("companyId"), companyId) : null;
    }

    public static Specification<User> byAll(QueryUserDto query) {
        return byCompanyId(query.getCompanyId());
    }

}

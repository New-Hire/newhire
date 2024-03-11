package com.muyu.newhire.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CurrentUser extends User {

    @Setter(AccessLevel.NONE)
    private final Long userId;

    @Setter(AccessLevel.NONE)
    private final String account;

    @Setter(AccessLevel.NONE)
    private final Long currentCompanyId;

    public CurrentUser(
            Long userId, String account, Long currentCompanyId,
            String username, String password, Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.userId = userId;
        this.account = account;
        this.currentCompanyId = currentCompanyId;
    }

    public CurrentUser(
            Long userId, String account, Long currentCompanyId,
            String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.account = account;
        this.currentCompanyId = currentCompanyId;
    }
}

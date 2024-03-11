package com.muyu.newhire.filter;

import com.muyu.newhire.auth.CurrentUser;
import com.muyu.newhire.provider.JwtTokenProvider;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        try {
            performAuthentication(request);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication failed");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void performAuthentication(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = extractToken(authorizationHeader);
            var claims = jwtTokenProvider.verifyAndGetSubjectFromToken(token);
            long userId = Long.parseUnsignedLong(claims.getSubject());
            var account = claims.get("account").toString();
            var role = claims.get("role").toString();
            var currentCompanyId = Long.parseUnsignedLong(claims.get("currentCompanyId").toString());

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = new CurrentUser(
                        userId, account, currentCompanyId, account, "",
                        List.of(new SimpleGrantedAuthority("ROLE_" + role))
                );
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }
}

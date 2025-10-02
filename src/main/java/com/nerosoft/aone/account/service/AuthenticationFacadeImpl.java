package com.nerosoft.aone.account.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public long getUserId() {
        var authentication = getAuthentication();
        if (authentication == null) {
            return 0;
        }

        var principal = authentication.getPrincipal();
        if (principal instanceof Jwt jwt) {
            var subject = jwt.getSubject();
            if (subject == null) {
                return 0;
            }

            if(!subject.matches("\\d+")) {
                return 0;
            }

            return Long.getLong(jwt.getSubject());
        } else {
            return 0;
        }
    }
}

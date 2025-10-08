package com.rafael.security.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    public boolean checkAuth(Long ownerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
        boolean hasAdminRole = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserInfoDetails userDetails = (UserInfoDetails) auth.getPrincipal();

        if (!hasAdminRole && !(hasUserRole && Objects.equals(ownerId, userDetails.getOwnerId()))) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
        return true;
    }
}

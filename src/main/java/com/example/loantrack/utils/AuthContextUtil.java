package com.example.loantrack.utils;

import com.example.loantrack.user.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthContextUtil {
    /**
     * Retrieves the currently authenticated user's ID from the SecurityContext.
     * @return The user ID as Long, or null if not authenticated.
     */
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Long) {
            return (Long) authentication.getPrincipal(); // Extract userId
        }
        return null;
    }

    /**
     * Retrieves the currently authenticated user's role from the SecurityContext.
     * @return The Role enum, or null if no role is found.
     */
    public Role getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String roleName = authority.getAuthority().replace("ROLE_", ""); // Remove "ROLE_" prefix
                return Role.fromString(roleName); // Convert to Role enum
            }
        }
        return null;
    }

    /**
     * Checks if a user is authenticated.
     * @return true if authenticated, otherwise false.
     */
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * Clears the security context (logs out the user).
     */
    public void clearAuthContext() {
        SecurityContextHolder.clearContext();
    }
}

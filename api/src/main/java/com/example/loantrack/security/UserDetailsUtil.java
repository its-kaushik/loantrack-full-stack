package com.example.loantrack.security;

import com.example.loantrack.user.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsUtil {

    /**
     * Gets the current authenticated user's ID
     * @return the user ID or null if not authenticated
     */
    public static Long getCurrentUserId() {
        CustomUserDetails userDetails = getCurrentUserDetails();
        return userDetails != null ? userDetails.getUserId() : null;
    }

    /**
     * Gets the current authenticated user's role
     * @return the user role or null if not authenticated
     */
    public static Role getCurrentUserRole() {
        CustomUserDetails userDetails = getCurrentUserDetails();
        return userDetails != null ? userDetails.getRole() : null;
    }

    /**
     * Gets the current authenticated user's company ID
     * @return the company ID or null if not authenticated
     */
    public static Long getCurrentCompanyId() {
        CustomUserDetails userDetails = getCurrentUserDetails();
        return userDetails != null ? userDetails.getCompanyId() : null;
    }

    /**
     * Gets all the user details from the security context
     * @return CustomUserDetails object or null if not authenticated
     */
    public static CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                authentication.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }

        return null;
    }
}

package com.example.loantrack.security;

import com.example.loantrack.user.Role;

public class CustomUserDetails {
    private static final long serialVersionUID = 1L;

    private final Long userId;
    private final Role role;
    private final Long companyId;

    public CustomUserDetails(Long userId, Role role, Long companyId) {
        this.userId = userId;
        this.role = role;
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    public Long getCompanyId() {
        return companyId;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "userId=" + userId +
                ", role=" + role +
                ", companyId=" + companyId +
                '}';
    }
}

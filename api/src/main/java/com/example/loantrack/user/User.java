package com.example.loantrack.user;

import com.example.loantrack.common.BaseEntity;
import com.example.loantrack.validation.countryCode.ValidCountryCode;
import com.example.loantrack.validation.phoneNumber.ValidPhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    @ValidPhoneNumber
    private String phoneNumber;

    @Column(nullable = false)
    @ValidCountryCode
    private String countryCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

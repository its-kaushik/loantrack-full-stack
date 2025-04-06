package com.example.loantrack.auth.dto;

import com.example.loantrack.user.Role;
import com.example.loantrack.validation.countryCode.ValidCountryCode;
import com.example.loantrack.validation.phoneNumber.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignUpRequestDTO {

    @NotBlank(message = "First name is required !")
    private String firstName;

    @NotBlank(message = "Last name is required !")
    private String lastName;

    @NotBlank(message = "Country code is required !")
    @ValidCountryCode
    private String countryCode;

    @NotBlank(message = "Phone number is required !")
    @ValidPhoneNumber
    private String phoneNumber;

    @NotBlank(message = "Company name is required !")
    private String companyName;

    private String companyLogoUrl;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

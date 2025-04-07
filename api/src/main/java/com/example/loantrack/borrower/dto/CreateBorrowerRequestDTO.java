package com.example.loantrack.borrower.dto;

import com.example.loantrack.validation.countryCode.ValidCountryCode;
import com.example.loantrack.validation.phoneNumber.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;

public class CreateBorrowerRequestDTO {
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

    private String profilePictureUrl;

    private String aadharUrl;

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

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getAadharUrl() {
        return aadharUrl;
    }

    public void setAadharUrl(String aadharUrl) {
        this.aadharUrl = aadharUrl;
    }
}

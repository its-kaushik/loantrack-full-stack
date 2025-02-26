package com.example.loantrack.auth.dto;

import com.example.loantrack.validation.countryCode.ValidCountryCode;
import com.example.loantrack.validation.phoneNumber.ValidPhoneNumber;
import jakarta.validation.constraints.NotEmpty;

public class GetOtpRequestDTO {

    @NotEmpty(message = "Phone number is required !")
    @ValidPhoneNumber
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

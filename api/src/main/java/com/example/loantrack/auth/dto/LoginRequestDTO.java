package com.example.loantrack.auth.dto;

import com.example.loantrack.validation.phoneNumber.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank
    @ValidPhoneNumber
    private String phoneNumber;

    @NotBlank
    private String otp;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

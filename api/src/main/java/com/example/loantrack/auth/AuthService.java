package com.example.loantrack.auth;

import com.example.loantrack.auth.dto.GetOtpRequestDTO;
import com.example.loantrack.auth.dto.SignUpRequestDTO;
import com.example.loantrack.user.User;
import com.example.loantrack.user.UserMapper;
import com.example.loantrack.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    public User registerUser(SignUpRequestDTO userDetails) {
        return userService.registerUser(UserMapper.INSTANCE.toUser(userDetails));
    }

    public String getOtp(GetOtpRequestDTO getOtpRequestDTO) {

        User user = userService.getUserByPhoneNumber(getOtpRequestDTO.getPhoneNumber())
                .orElseThrow(() -> new NoSuchElementException("No user found with phone number: " + getOtpRequestDTO.getPhoneNumber()));

        String otp = otpService.generateOtp(getOtpRequestDTO.getPhoneNumber());

        // TODO : Intgerate a SMS OTP sending service

        return otp;
    }
}

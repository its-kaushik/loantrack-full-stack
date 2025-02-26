package com.example.loantrack.auth;

import com.example.loantrack.config.OtpProperties;
import com.example.loantrack.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    private final RedisUtil redisUtil;
    private final Random random = new Random();

    private final OtpProperties otpProperties;

    public OtpService(RedisUtil redisUtil, OtpProperties otpProperties) {
        this.redisUtil = redisUtil;
        this.otpProperties = otpProperties;
    }

    public String generateOtp(String phoneNumber) {
        String otp = String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
        redisUtil.setValue(phoneNumber, otp, otpProperties.getTime(), otpProperties.getUnit());
        return otp;
    }

    public boolean validateOtp(String phoneNumber, String otp) {
        String storedOtp = redisUtil.getValue(phoneNumber);
        if (storedOtp != null && storedOtp.equals(otp)) {
            redisUtil.deleteKey(phoneNumber); // Remove OTP after successful validation
            return true;
        }
        return false;
    }
}

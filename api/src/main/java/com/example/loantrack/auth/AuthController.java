package com.example.loantrack.auth;

import com.example.loantrack.auth.dto.GetOtpRequestDTO;
import com.example.loantrack.auth.dto.SignUpRequestDTO;
import com.example.loantrack.common.ApiResponse;
import com.example.loantrack.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody SignUpRequestDTO userDto){
        User registeredUser = authService.registerUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "User registered successfully", registeredUser));

    }

    @PostMapping("get-otp")
    public ResponseEntity<ApiResponse<String>> getOtp(@Valid @RequestBody GetOtpRequestDTO getOtpRequestDTO){
        String otp = authService.getOtp(getOtpRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Otp sent sucesfully !", otp));
    }
}

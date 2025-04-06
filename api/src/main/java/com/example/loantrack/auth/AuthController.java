package com.example.loantrack.auth;

import com.example.loantrack.auth.dto.AddUserToCompanyDTO;
import com.example.loantrack.auth.dto.GetOtpRequestDTO;
import com.example.loantrack.auth.dto.LoginRequestDTO;
import com.example.loantrack.auth.dto.SignUpRequestDTO;
import com.example.loantrack.common.ApiResponse;
import com.example.loantrack.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/get-otp")
    public ResponseEntity<ApiResponse<String>> getOtp(@Valid @RequestBody GetOtpRequestDTO getOtpRequestDTO){
        String otp = authService.getOtp(getOtpRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Otp sent successfully !", otp));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){

        String token = authService.login(loginRequestDTO);

        if( token == null )
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Invalid phone number / otp !", null));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Login Successfully", token));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-user-to-company")
    public ResponseEntity<ApiResponse<User>> addUserToCompany(@Valid @RequestBody AddUserToCompanyDTO addUserToCompanyDTO){
        User newUser = authService.addUserToCompany(addUserToCompanyDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "User registered successfully", newUser));
    }

}

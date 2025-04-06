package com.example.loantrack.auth;

import com.example.loantrack.auth.dto.AddUserToCompanyDTO;
import com.example.loantrack.auth.dto.GetOtpRequestDTO;
import com.example.loantrack.auth.dto.LoginRequestDTO;
import com.example.loantrack.auth.dto.SignUpRequestDTO;
import com.example.loantrack.company.Company;
import com.example.loantrack.company.CompanyService;
import com.example.loantrack.security.UserDetailsUtil;
import com.example.loantrack.user.Role;
import com.example.loantrack.user.User;
import com.example.loantrack.user.UserMapper;
import com.example.loantrack.user.UserService;
import com.example.loantrack.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public User registerUser(SignUpRequestDTO userDetails) {

        Company company = companyService.registerCompany(new Company(userDetails.getCompanyName(), userDetails.getCompanyLogoUrl()));
        return userService.registerUser(new User(userDetails.getFirstName(), userDetails.getLastName(), userDetails.getPhoneNumber(), userDetails.getCountryCode(), Role.ADMIN, company));
    }

    public String getOtp(GetOtpRequestDTO getOtpRequestDTO) {

        User user = userService.getUserByPhoneNumber(getOtpRequestDTO.getPhoneNumber());

        String otp = otpService.generateOtp(getOtpRequestDTO.getPhoneNumber());

        // TODO : Intgerate a SMS OTP sending service

        return otp;
    }

    public String login(LoginRequestDTO loginRequestDTO){
        if( !otpService.validateOtp(loginRequestDTO.getPhoneNumber(), loginRequestDTO.getOtp()) ){
            return null;
        }

        User user = userService.getUserByPhoneNumber(loginRequestDTO.getPhoneNumber());

        return jwtUtil.generateToken(user.getRole(), user.getId(), user.getCompany().getId());
    }

    public User addUserToCompany(AddUserToCompanyDTO addUserToCompanyDTO){

        Long companyId = UserDetailsUtil.getCurrentCompanyId();
        if (companyId == null) {
            throw new IllegalStateException("Unable to retrieve company ID from authenticated user");
        }

        // Get company entity
        Company company = companyService.getCompanyById(companyId);

        // Create new user with company association
        User newUser = new User(
                addUserToCompanyDTO.getFirstName(),
                addUserToCompanyDTO.getLastName(),
                addUserToCompanyDTO.getPhoneNumber(),
                addUserToCompanyDTO.getCountryCode(),
                addUserToCompanyDTO.getRole(),
                company
        );

        return userService.registerUser(newUser);
    }
}

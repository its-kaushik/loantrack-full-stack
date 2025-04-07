package com.example.loantrack.borrower;

import com.example.loantrack.borrower.dto.CreateBorrowerRequestDTO;
import com.example.loantrack.common.ApiResponse;
import com.example.loantrack.company.Company;
import com.example.loantrack.company.CompanyService;
import com.example.loantrack.security.UserDetailsUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrower")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<Borrower>> createBorrower(@Valid @RequestBody CreateBorrowerRequestDTO createBorrowerRequestDTO) {

        Long companyId = UserDetailsUtil.getCurrentCompanyId();
        if (companyId == null) {
            throw new IllegalStateException("Unable to retrieve company ID from authenticated user");
        }

        // Get company entity
        Company company = borrowerService.getCompanyById(companyId);

        Borrower borrower = borrowerService.createBorrower(new Borrower(createBorrowerRequestDTO.getFirstName(), createBorrowerRequestDTO.getLastName(), createBorrowerRequestDTO.getPhoneNumber(), createBorrowerRequestDTO.getCountryCode(), createBorrowerRequestDTO.getProfilePictureUrl(), createBorrowerRequestDTO.getAadharUrl(), company));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Borrower Created Successfully !", borrower));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Borrower>> getBorrowerById(@PathVariable("id") Long borrowerId){
        if (borrowerId == null || borrowerId <= 0) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Invalid borrower ID provided", null));
        }

        Borrower borrower = borrowerService.getBorrowerById(borrowerId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Borrower Found Successfully !", borrower));
    }
}

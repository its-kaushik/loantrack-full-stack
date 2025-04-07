package com.example.loantrack.borrower;

import com.example.loantrack.company.Company;
import com.example.loantrack.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    @Autowired
    private CompanyService companyService;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Borrower createBorrower(Borrower borrower){
        try {
            return borrowerRepository.save(borrower); // Let the DB handle the insert and uniqueness check
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Phone number already exists", ex); // Handle the exception
        }
    }

    public Company getCompanyById(Long companyId) {
        return companyService.getCompanyById(companyId);
    }
}

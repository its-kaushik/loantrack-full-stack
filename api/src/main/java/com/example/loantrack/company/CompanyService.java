package com.example.loantrack.company;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public Company registerCompany(Company company){
        return companyRepository.save(company);
    }

}

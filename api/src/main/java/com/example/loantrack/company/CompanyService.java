package com.example.loantrack.company;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public Company registerCompany(Company company){
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id){
        return companyRepository.getCompanyById(id).orElseThrow(() -> new NoSuchElementException("Company not found !"));
    }

}

package com.sanjeevnode.jobmanager.company.impl;

import com.sanjeevnode.jobmanager.company.Company;
import com.sanjeevnode.jobmanager.company.CompanyRepository;
import com.sanjeevnode.jobmanager.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    final private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public String addCompany(Company company) {
        return companyRepository.save(company).getId().toString();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        return  companyRepository.findById(id).map(company -> {
            companyRepository.delete(company);
            return true;
        }).orElse(false);
    }

    @Override
    public Company updateCompanyById(Long id, Company company) {
        return companyRepository.findById(id).map(existingCompany -> {
            existingCompany.setName(company.getName());
            existingCompany.setDescription(company.getDescription());
            existingCompany.setJobs(company.getJobs());
            return companyRepository.save(existingCompany);
        }).orElse(null);
    }
}

package com.sanjeevnode.jobmanager.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    String addCompany(Company company);

    Company getCompanyById(Long id);

    boolean deleteCompanyById(Long id);

    Company updateCompanyById(Long id, Company company);
}

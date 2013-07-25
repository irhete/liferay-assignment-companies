package com.nortal.assignment.companymanagement.dao;

import java.util.List;

import com.nortal.assignment.companymanagement.model.Company;

public interface CompanyDAO {

	void saveCompany(Company company);

	List<Company> getCompanies();

	Company getCompany(long id);

}

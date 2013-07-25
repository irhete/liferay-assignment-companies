package com.nortal.assignment.companymanagement.service;

import java.util.List;

import com.nortal.assignment.companymanagement.model.Company;

public interface CompanyManagementService {

	void addCompany(Company company);

	public List<Company> listCompanies();

	public void editCompany(Company company);

	public Company getCompany(long id);

}

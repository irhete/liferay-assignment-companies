package com.nortal.assignment.companymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nortal.assignment.companymanagement.dao.CompanyDAO;
import com.nortal.assignment.companymanagement.model.Companies;
import com.nortal.assignment.companymanagement.model.Company;

@Transactional
@Service
public class CompanyManagementServiceImpl implements CompanyManagementService {

	@Autowired
	private CompanyDAO companyDAO;

	@Override
	public void addCompany(Company company) {
		companyDAO.saveCompany(company);
	}

	@Override
	public Companies listCompanies() {
		Companies companies = new Companies();
		companies.setCompanies(companyDAO.getCompanies());
		return companies;
	}

	@Override
	public void editCompany(Company company) {
		Company updateableCompany = companyDAO.getCompany(company.getId());
		updateableCompany.setName(company.getName());
		updateableCompany.setDescription(company.getDescription());
		updateableCompany.setYear(company.getYear());
		companyDAO.saveCompany(updateableCompany);
	}

	@Override
	public Company getCompany(long id) {
		return companyDAO.getCompany(id);
	}

}

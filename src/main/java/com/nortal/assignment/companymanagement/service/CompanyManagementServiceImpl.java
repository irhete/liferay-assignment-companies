package com.nortal.assignment.companymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nortal.assignment.companymanagement.dao.CompanyDAO;
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
	public List<Company> listCompanies() {
		return companyDAO.getCompanies();
	}

	@Override
	public void editCompany(Company company) {
		companyDAO.saveCompany(company);
	}

	@Override
	public Company getCompany(long id) {
		return companyDAO.getCompany(id);
	}

}

package com.nortal.assignment.companymanagement.service;

import com.nortal.assignment.companymanagement.model.Address;
import com.nortal.assignment.companymanagement.model.Companies;
import com.nortal.assignment.companymanagement.model.Company;

public interface CompanyManagementService {

	void addCompany(Company company);

	public Companies listCompanies();

	public void editCompany(Company company);

	public Company getCompany(long id);

	public void editAddress(Address address);

	public void deleteAddress(long addressId);

}

package com.nortal.assignment.companymanagement.dao;

import java.util.List;

import com.nortal.assignment.companymanagement.model.Address;
import com.nortal.assignment.companymanagement.model.Company;

public interface CompanyDAO {

	void saveCompany(Company company);

	List<Company> getCompanies();

	Company getCompany(long id);

	void saveAddress(Address newAddress);

	void deleteAddress(long addressId);

}

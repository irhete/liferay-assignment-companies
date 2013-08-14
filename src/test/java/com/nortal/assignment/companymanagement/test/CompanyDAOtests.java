package com.nortal.assignment.companymanagement.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nortal.assignment.companymanagement.dao.CompanyDAO;
import com.nortal.assignment.companymanagement.model.Address;
import com.nortal.assignment.companymanagement.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
@DirtiesContext
public class CompanyDAOtests extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	CompanyDAO companyDAO;

	@Test
	public void addCompanySuccessfulTest() {
		Company company = new Company(2, "Tallinna Kaubamaja", "Selling goods",
				1990);
		companyDAO.saveCompany(company);
		assertEquals(2, companyDAO.getCompanies().size());
	}

	@Test
	public void listAllCompanies() {
		List<Company> companies = companyDAO.getCompanies();
		assertEquals("Nortal", companies.get(0).getName());
	}

	@Test
	public void editDescription() {
		Company company = companyDAO.getCompanies().get(0);
		String newDescription = "new description";
		company.setDescription(newDescription);
		assertEquals(newDescription, companyDAO.getCompanies().get(0)
				.getDescription());
	}

	@Test
	public void getStreet() {
		List<Company> companies = companyDAO.getCompanies();
		assertEquals("Lõõtsa", companies.get(0).getAddresses().getAddresses()
				.get(0).getStreet());
	}

	@Test
	public void getCompanyById() {
		Company company = companyDAO.getCompany(1L);
		assertEquals("Nortal", company.getName());
	}

	@Test
	public void setCompanyToAddressTest() {
		Address newAddress = new Address(2, "Leegi", 16, "Tallinn", "Eesti");
		newAddress.setCompany(companyDAO.getCompanies().get(0));
		companyDAO.saveAddress(newAddress);
		assertEquals(2, companyDAO.getCompanies().get(0).getAddresses()
				.getAddresses().size());
	}

	@Test
	public void addAddressesToCompany() {
		Address newAddress = new Address(2, "Leegi", 16, "Tallinn", "Eesti");
		Address newAddress2 = new Address(3, "Õismäe", 115, "Tallinn", "Eesti");
		Company company = companyDAO.getCompanies().get(0);
		company.addAddress(newAddress);
		company.addAddress(newAddress2);
		assertEquals(3, companyDAO.getCompanies().get(0).getAddresses()
				.getAddresses().size());
	}
}

package com.nortal.assignment.companymanagement.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nortal.assignment.companymanagement.dao.CompanyDAO;
import com.nortal.assignment.companymanagement.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
public class CompanyDAOtests extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	CompanyDAO companyDAO;

	@Ignore
	@Test
	public void addCompanySuccessfulTest() {
		Company company = new Company("Tallinna Kaubamaja", "Selling goods",
				1990);
		companyDAO.saveCompany(company);
		assertEquals(2, company.getId());
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
		assertEquals("Lõõtsa", companies.get(0).getAddresses().get(0)
				.getStreet());
	}

	@Test
	public void getCompanyById() {
		Company company = companyDAO.getCompany(1L);
		assertEquals("Nortal", company.getName());
	}
}

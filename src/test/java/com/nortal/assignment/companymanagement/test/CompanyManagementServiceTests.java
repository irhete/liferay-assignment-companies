package com.nortal.assignment.companymanagement.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.nortal.assignment.companymanagement.dao.CompanyDAO;
import com.nortal.assignment.companymanagement.model.Company;
import com.nortal.assignment.companymanagement.service.CompanyManagementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
public class CompanyManagementServiceTests {

	@Mock
	CompanyDAO companyDAO;

	@InjectMocks
	CompanyManagementServiceImpl service;

	@Test
	public void addCompanyTest() {
		Company company = new Company("Tallinna Kaubamaja", "Selling goods",
				1990);
		service.addCompany(company);
		Mockito.verify(companyDAO).saveCompany(company);
	}

	@Test
	public void listCompaniesTest() {
		service.listCompanies();
		Mockito.verify(companyDAO).getCompanies();
	}

	@Test
	public void getCompanyTest() {
		long id = 2;
		service.getCompany(id);
		Mockito.verify(companyDAO).getCompany(id);
	}

	@Test
	public void editCompanyTest() {
		long id = 2;
		Mockito.when(companyDAO.getCompany(id)).thenReturn(
				new Company("Tallinna Kaubamaja", "Selling goods", 1990));
		Company company = service.getCompany(id);
		company.setDescription("new desc");
		service.editCompany(company);
		Mockito.verify(companyDAO).saveCompany(company);
	}
}

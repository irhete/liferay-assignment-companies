package com.nortal.assignment.companymanagement.test;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.nortal.assignment.companymanagement.model.Companies;
import com.nortal.assignment.companymanagement.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
@DirtiesContext
public class CompanyManagementControllerTests extends
		AbstractTransactionalJUnit4SpringContextTests {

	private MockHttpServletRequest request = new MockHttpServletRequest();
	private MockHttpServletResponse response = new MockHttpServletResponse();
	@Autowired
	private RequestMappingHandlerMapping mappingHandler;
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;

	@Before
	public void setUp() {
		request.addHeader("Content-Type", "text/xml");
		request.addHeader("Accept", "text/xml");
		request.setRequestURI("/companies");
	}

	@Test
	public void addCompanyTest() throws Exception {
		request.setMethod("POST");
		String companyXML = "<company><id>0</id><name>Tallinna Kaubamaja</name><description>Selling goods</description><year>1990</year></company>";
		byte[] contentBody = companyXML.getBytes();
		request.setContent(contentBody);
		Object handler = mappingHandler.getHandler(request).getHandler();
		handlerAdapter.handle(request, response, handler);
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		Company company = (Company) jaxbUnMarshaller
				.unmarshal(new StringReader(response.getContentAsString()));
		assertEquals("Tallinna Kaubamaja", company.getName());
	}

	@Test
	public void listCompaniesTest() throws Exception {
		request.setMethod("GET");
		Object handler = mappingHandler.getHandler(request).getHandler();
		handlerAdapter.handle(request, response, handler);
		JAXBContext jaxbContext = JAXBContext.newInstance(Companies.class);
		Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		Companies companies = (Companies) jaxbUnMarshaller
				.unmarshal(new StringReader(response.getContentAsString()));
		List<Company> companyList = companies.getCompanies();
		assertEquals(1, companyList.size());
	}

	@Test
	public void getCompanyTest() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/companies/1");
		Object handler = mappingHandler.getHandler(request).getHandler();
		handlerAdapter.handle(request, response, handler);
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		Company company = (Company) jaxbUnMarshaller
				.unmarshal(new StringReader(response.getContentAsString()));
		assertEquals("Nortal", company.getName());
	}

	@Test
	public void editCompanyTest() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/companies/1");
		String companyXML = "<company><name>Tallinna Kaubamaja</name><description>Selling goods</description><year>1990</year></company>";
		byte[] contentBody = companyXML.getBytes();
		request.setContent(contentBody);
		Object handler = mappingHandler.getHandler(request).getHandler();
		handlerAdapter.handle(request, response, handler);
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		Company company = (Company) jaxbUnMarshaller
				.unmarshal(new StringReader(response.getContentAsString()));
		assertEquals("Tallinna Kaubamaja", company.getName());
	}

	@Test
	public void addCompanyWithAddressTest() throws Exception {
		request.setMethod("POST");
		String companyXML = "<company><id>0</id><name>Tallinna Kaubamaja</name><description>Selling goods</description><year>1990</year><addresses><address><street>Pepleri</street><building>14</building><city>Tartu</city><country>Eesti</country></address></addresses></company>";
		byte[] contentBody = companyXML.getBytes();
		request.setContent(contentBody);
		Object handler = mappingHandler.getHandler(request).getHandler();
		handlerAdapter.handle(request, response, handler);
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		Company company = (Company) jaxbUnMarshaller
				.unmarshal(new StringReader(response.getContentAsString()));
		assertEquals(1, company.getAddresses().getAddresses().size());
	}

}

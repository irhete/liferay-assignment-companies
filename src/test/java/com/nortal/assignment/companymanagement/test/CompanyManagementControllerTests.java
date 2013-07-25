package com.nortal.assignment.companymanagement.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
public class CompanyManagementControllerTests extends
		AbstractTransactionalJUnit4SpringContextTests {

	private MockHttpServletRequest request = new MockHttpServletRequest();
	private MockHttpServletResponse response = new MockHttpServletResponse();
	@Autowired
	private RequestMappingHandlerMapping mappingHandler;
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;

	@Test
	public void addCompanyTest() throws Exception {
		request.addHeader("Content-Type", "text/xml");
		request.addHeader("Accept", "text/xml");
		request.setMethod("POST");
		request.setRequestURI("/companies");
		String companyXML = "<company><id>0</id><name>Tallinna Kaubamaja</name><description>Selling goods</description><year>1990</year></company>";
		byte[] contentBody = companyXML.getBytes();
		request.setContent(contentBody);
		Object handler = mappingHandler.getHandler(request).getHandler();

		handlerAdapter.handle(request, response, handler);
		System.out.println(response.getContentAsString());
		// assertEquals(response.getContentAsString(), company);
	}
}

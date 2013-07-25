package com.nortal.assignment.companymanagement.controller;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nortal.assignment.companymanagement.model.Company;
import com.nortal.assignment.companymanagement.service.CompanyManagementService;

@Controller
@RequestMapping("/companies")
public class CompanyManagementController {

	@Autowired
	CompanyManagementService service;

	@RequestMapping(method = RequestMethod.POST, produces = "text/xml", consumes = "text/xml")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	Company addCompany(@RequestBody Company company) throws JAXBException {
		// JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		// Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		// Company company = (Company) jaxbUnMarshaller
		// .unmarshal(new StringReader(companyXML));
		System.out.println(company.getDescription());
		service.addCompany(company);
		return company;
	}

}

package com.nortal.assignment.companymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nortal.assignment.companymanagement.model.Companies;
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
	Company addCompany(@RequestBody Company company) {
		service.addCompany(company);
		return company;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Companies listCompanies() {
		return service.listCompanies();
	}

	@RequestMapping(value = "/{companyId}", method = RequestMethod.GET, produces = "text/xml")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Company getCompany(@PathVariable long companyId, Model model) {
		model.addAttribute("movie", "this is default movie");
		return service.getCompany(companyId);
	}

	@RequestMapping(value = "/{companyId}", method = RequestMethod.POST, produces = "text/xml", consumes = "text/xml")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Company editCompany(@PathVariable long companyId,
			@RequestBody Company company) {
		company.setId(companyId);
		service.editCompany(company);
		return company;
	}

}

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

import com.nortal.assignment.companymanagement.model.Address;
import com.nortal.assignment.companymanagement.model.Companies;
import com.nortal.assignment.companymanagement.model.Company;
import com.nortal.assignment.companymanagement.service.CompanyManagementService;

@Controller
@RequestMapping("/companies")
public class CompanyManagementController {

	@Autowired
	CompanyManagementService service;

	@RequestMapping(method = RequestMethod.POST, produces = { "text/xml",
			"application/xml" }, consumes = { "text/xml", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	Company addCompany(@RequestBody Company company) {
		service.addCompany(company);
		return company;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { "text/xml",
			"application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Companies listCompanies() {
		return service.listCompanies();
	}

	@RequestMapping(value = "/{companyId}", method = RequestMethod.GET, produces = {
			"text/xml", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Company getCompany(@PathVariable long companyId, Model model) {
		Company company = service.getCompany(companyId);
		return company;
	}

	@RequestMapping(value = "/{companyId}", method = RequestMethod.POST, produces = {
			"text/xml", "application/xml" }, consumes = { "text/xml",
			"application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Company editCompany(@PathVariable long companyId,
			@RequestBody Company company) {
		company.setId(companyId);
		service.editCompany(company);
		return company;
	}

	@RequestMapping(value = "/{companyId}/addresses/{addressId}", method = RequestMethod.POST, produces = {
			"text/xml", "application/xml" }, consumes = { "text/xml",
			"application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Address editAddress(@PathVariable long companyId,
			@PathVariable long addressId, @RequestBody Address address) {
		address.setId(addressId);
		address.setCompany(service.getCompany(companyId));
		service.editAddress(address);
		return address;
	}

	@RequestMapping(value = "/{companyId}/addresses/{addressId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void editAddress(@PathVariable long addressId) {
		service.deleteAddress(addressId);
	}

}

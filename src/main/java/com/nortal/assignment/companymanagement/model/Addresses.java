package com.nortal.assignment.companymanagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Addresses {

	@XmlElement(name = "address")
	List<Address> addresses = new ArrayList<Address>();

	public Addresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Addresses() {
	}

	@XmlTransient
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
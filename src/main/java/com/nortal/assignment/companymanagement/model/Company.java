package com.nortal.assignment.companymanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "company")
@XmlRootElement
public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "company_seq")
	@Column(name = "id")
	@XmlElement
	private long id;

	@Column(name = "name", nullable = false)
	@XmlElement
	private String name;

	@Column(name = "description", nullable = false)
	@XmlElement
	private String description;

	@Column(name = "year", nullable = false)
	@XmlElement
	private int year;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.ALL)
	@XmlTransient
	private List<Address> addresses = new ArrayList<Address>();

	public Company(String name, String description, int year) {
		this.name = name;
		this.description = description;
		this.year = year;
	}

	public Company(long id, String name, String description, int year) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.year = year;
	}

	public Company() {
	}

	@XmlTransient
	public long getId() {
		return id;
	}

	@XmlTransient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement
	public Addresses getAddresses() {
		return new Addresses(addresses);
	}

	public void setAddresses(Addresses addresses) {
		for (Address address : addresses.getAddresses()) {
			addAddress(address);
		}
	}

	public void addAddress(Address address) {
		addresses.add(address);
	}

}

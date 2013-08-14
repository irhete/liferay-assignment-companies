package com.nortal.assignment.companymanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "address")
@XmlRootElement
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "address_seq")
	@Column(name = "id")
	@XmlElement
	private long id;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	@XmlTransient
	private Company company;

	@Column(name = "street", nullable = false)
	@XmlElement
	private String street;

	@Column(name = "building", nullable = false)
	@XmlElement
	private int building;

	@Column(name = "city", nullable = false)
	@XmlElement
	private String city;

	@Column(name = "country", nullable = false)
	@XmlElement
	private String country;

	public Address() {
	}

	public Address(String street, int building, String city, String country) {
		this.street = street;
		this.building = building;
		this.city = city;
		this.country = country;
	}

	public Address(int id, String street, int building, String city,
			String country) {
		this.id = id;
		this.street = street;
		this.building = building;
		this.city = city;
		this.country = country;
	}

	@XmlTransient
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlTransient
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@XmlTransient
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@XmlTransient
	public int getBuilding() {
		return building;
	}

	public void setBuilding(int building) {
		this.building = building;
	}

	@XmlTransient
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlTransient
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}

package com.nortal.assignment.companymanagement.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nortal.assignment.companymanagement.model.Address;
import com.nortal.assignment.companymanagement.model.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveCompany(Company company) {
		sessionFactory.getCurrentSession().saveOrUpdate(company);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {
		return sessionFactory.getCurrentSession().createCriteria(Company.class)
				.list();

	}

	@Override
	public Company getCompany(long id) {
		return (Company) sessionFactory.getCurrentSession().get(Company.class,
				id);
	}

	@Override
	public void addAddress(Address newAddress) {
		sessionFactory.getCurrentSession().saveOrUpdate(newAddress);
	}

}

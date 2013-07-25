package com.nortal.assignment.companymanagement.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nortal.assignment.companymanagement.model.Company;

@Repository
@Transactional
public class CompanyDAOImpl implements CompanyDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveCompany(Company company) {
		sessionFactory.getCurrentSession().save(company);
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

}

package com.nortal.assignment.companymanagement.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
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

		for (Address address : company.getAddresses().getAddresses()) {
			address.setCompany(company);
		}
		sessionFactory.getCurrentSession().merge(company);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {
		List<Company> companies = sessionFactory
				.getCurrentSession()
				.createCriteria(Company.class)
				.setResultTransformer(
						CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		return companies;
	}

	@Override
	public Company getCompany(long id) {
		return (Company) sessionFactory.getCurrentSession().get(Company.class,
				id);
	}

	@Override
	public void saveAddress(Address address) {
		sessionFactory.getCurrentSession().merge(address);
	}

	@Override
	public void deleteAddress(long addressId) {
		Address address = (Address) sessionFactory.getCurrentSession().get(
				Address.class, addressId);
		Company company = getCompany(address.getCompany().getId());
		company.getAddresses().getAddresses().remove(address);
		sessionFactory.getCurrentSession().delete(address);

	}

}

package com.mastering.hibernate.embeddable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class HibernateCustomerDao {

	private EntityManager manager;

	public HibernateCustomerDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void save(Customer employee) {
		manager.persist(employee);
	}

	public void update(Customer employee) {
		String jpql = 
				  " update "
				+ "		Customer as c "
				+ " set "
				+ "		c.name 		   = :name, "
				+ "		c.address.state  = :state, "
				+ "		c.address.street = :street "
				+ " where id = :customer_id";
		Query query = manager.createQuery(jpql);
		query.setParameter("customer_id", employee.getId());
		query.setParameter("name", employee.getName());
		query.setParameter("state", employee.getAddress().getState());
		query.setParameter("street", employee.getAddress().getStreet());
		
		query.executeUpdate();
	}

}

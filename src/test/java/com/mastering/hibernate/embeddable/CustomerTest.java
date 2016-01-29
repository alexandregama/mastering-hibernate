package com.mastering.hibernate.embeddable;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mastering.hibernate.JPAUtil;

public class CustomerTest {

	private EntityManager manager;

	@Before
	public void setUp() {
		manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
	}
	
	@After
	public void setDown() {
		manager.getTransaction().commit();
		
		if (manager.isOpen()) {
			manager.close();
		}
	}
	
	@Test
	public void shouldUpdateCustomerWithItsEmbeddedAddress() throws Exception {
		Customer customer = new Customer();
		customer.setName("Alexandre Gama");
		
		HibernateCustomerDao dao = new HibernateCustomerDao(manager);
		dao.save(customer);
		
		Customer employeeToBeUpdated = new Customer();
		employeeToBeUpdated.setId(customer.getId());
		employeeToBeUpdated.setAddress(new Address("Irvine", "Second Street"));
		dao.update(employeeToBeUpdated);
	}
}

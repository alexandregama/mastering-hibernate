package com.mastering.hibernate.search;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mastering.hibernate.JPAUtil;
import com.mastering.hibernate.embeddable.Customer;
import com.mastering.hibernate.embeddable.HibernateCustomerDao;

public class CustomerSearchTest {

	private EntityManager manager;
	private HibernateCustomerDao customerDao;

	@Before
	public void setup() {
		manager = new JPAUtil().getEntityManager();
		customerDao = new HibernateCustomerDao(manager);
		manager.getTransaction().begin();
		
		Customer alexandre = new Customer();
		alexandre.setName("Alexandre");
		customerDao.save(alexandre);
		
		Customer alexandra = new Customer();
		alexandra.setName("Alexandre");
		customerDao.save(alexandra);
		
		Customer patrick = new Customer();
		patrick.setName("Patrick");
		customerDao.save(patrick);
		
		Customer william = new Customer();
		william.setName("William");
		customerDao.save(william);
		
		Customer wilson = new Customer();
		wilson.setName("wilson");
		customerDao.save(wilson);

	}
	
	@After
	public void setdown() {
		manager.getTransaction().commit();
		manager.close(); 
	}
	
	@Test
	public void shouldFindACustomerWithNameContainingACaseInsensitiveWord() throws Exception {
		List<Customer> customers = customerDao.findByNameContainingSomeCaseInsensitiveWord("Wil");
		
		assertEquals(customers.get(0).getName(), "William");
		assertEquals(customers.get(1).getName(), "wilson");
	}
	
}

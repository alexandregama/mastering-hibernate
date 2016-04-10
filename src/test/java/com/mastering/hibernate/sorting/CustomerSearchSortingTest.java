package com.mastering.hibernate.sorting;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mastering.hibernate.JPAUtil;
import com.mastering.hibernate.embeddable.Customer;
import com.mastering.hibernate.embeddable.HibernateCustomerDao;

public class CustomerSearchSortingTest {

	private EntityManager manager;

	@Before
	public void setup() {
		manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("delete from Customer");
		query.executeUpdate();
		
		Customer alexandreGama = new Customer();
		alexandreGama.setName("Alexandre");
		alexandreGama.setLastname("Gama");
		
		Customer alexandreGomes = new Customer();
		alexandreGomes.setName("Alexandre");
		alexandreGomes.setLastname("Gomes");
		
		Customer patrick = new Customer();
		patrick.setName("Patrick");
		patrick.setLastname("Olsen");
		
		Customer william = new Customer();
		william.setName("William");
		william.setLastname("Aron");
		
		Customer jose = new Customer();
		jose.setName("Jose");
		jose.setLastname("Grey");
		
		manager.persist(alexandreGama); 
		manager.persist(alexandreGomes); 
		manager.persist(patrick); 
		manager.persist(william); 
		manager.persist(jose);
	}
	
	@After
	public void setdown() {
		manager.getTransaction().commit();
		manager.close();
	}
	
	@Test
	public void shouldSortAListOfCustomersByItsName() throws Exception {
		HibernateCustomerDao customers = new HibernateCustomerDao(manager);
		
		List<Customer> listOfCustomers = customers.findAllSortingByItsName();
		
		assertEquals(5, listOfCustomers.size());
		assertEquals("Alexandre", listOfCustomers.get(0).getName());
		assertEquals("Alexandre", listOfCustomers.get(1).getName());
		assertEquals("Jose", listOfCustomers.get(2).getName());
		assertEquals("Patrick", listOfCustomers.get(3).getName());
		assertEquals("William", listOfCustomers.get(4).getName());
	} 
	
	@Test
	public void shouldSortAListOfCustomersByItsNameAndLastName() throws Exception {
		HibernateCustomerDao customers = new HibernateCustomerDao(manager);
		
		List<Customer> listOfCustomers = customers.findAndSortingByItsNameAndLastNameUsing("Alexandre");
		
		assertEquals(2, listOfCustomers.size());
		assertEquals("Alexandre", listOfCustomers.get(0).getName());
		assertEquals("Gama", listOfCustomers.get(0).getLastname());
		
		assertEquals("Alexandre", listOfCustomers.get(1).getName());
		assertEquals("Gomes", listOfCustomers.get(1).getLastname());
	} 
	
	@Test
	public void shouldSortAListOfCustomersByItsNameAndLastNameInDescendentWay() throws Exception {
		HibernateCustomerDao customers = new HibernateCustomerDao(manager);
		
		List<Customer> listOfCustomers = customers.findAndSortingByItsNameAndLastNameInDescendentWayUsing("Alexandre");
		
		assertEquals(2, listOfCustomers.size());
		assertEquals("Alexandre", listOfCustomers.get(0).getName());
		assertEquals("Gomes", listOfCustomers.get(0).getLastname());
		
		assertEquals("Alexandre", listOfCustomers.get(1).getName());
		assertEquals("Gama", listOfCustomers.get(1).getLastname());
	} 
	
}

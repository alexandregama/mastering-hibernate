package com.mastering.hibernate.singletable;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mastering.hibernate.JPAUtil;
import com.mastering.hibernate.dao.EmployeeDao;

public class EmployeeInheritanceTest {

	private EntityManager em;

	@Before
	public void setUp() {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
	}
	
	@After
	public void setDown() {
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void shouldSaveANewEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setName("Alexandre Gama");
		
		EmployeeDao dao = new EmployeeDao(em);
		dao.save(employee);
		
		assertNotNull(employee.getId());
	}

	@Test
	public void shouldSaveANewRegularEmployee() throws Exception {
		Employee employee = new RegularEmployee(1500.0, 100.0);
		employee.setName("Alex Gama");
		
		EmployeeDao dao = new EmployeeDao(em);
		dao.save(employee);
		
		assertNotNull(employee.getId());
	}
	
	@Test
	public void shouldSaveANewContractEmployee() throws Exception {
		Employee employee = new ContractEmployee(40.0, "Weekly");
		employee.setName("Fernando Gama");
		
		EmployeeDao dao = new EmployeeDao(em);
		dao.save(employee);
		
		assertNotNull(employee.getId());
	}
}

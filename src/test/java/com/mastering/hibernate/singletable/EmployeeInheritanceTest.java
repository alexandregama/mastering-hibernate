package com.mastering.hibernate.singletable;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mastering.hibernate.JPAUtil;
import com.mastering.hibernate.dao.HibernateEmployeeDao;

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
		
		HibernateEmployeeDao dao = new HibernateEmployeeDao(em);
		dao.save(employee);
		
		assertNotNull(employee.getId());
	}

	@Test
	public void shouldSaveANewRegularEmployee() throws Exception {
		Employee employee = new RegularEmployee(1500.0, 100.0);
		employee.setName("Alex Gama");
		
		HibernateEmployeeDao dao = new HibernateEmployeeDao(em);
		dao.save(employee);
		
		assertNotNull(employee.getId());
	}
	
	@Test
	public void shouldSaveANewContractEmployee() throws Exception {
		Employee employee = new ContractEmployee(40.0, "Weekly");
		employee.setName("Fernando Gama");
		
		HibernateEmployeeDao dao = new HibernateEmployeeDao(em);
		dao.save(employee);
		
		assertNotNull(employee.getId());
	}
	
	@Test
	public void shouldUpdateJustTheEmployeeName() throws Exception {
		Employee employee = new ContractEmployee(40.0, "Weekly");
		employee.setName("Gustavo");
		
		HibernateEmployeeDao dao = new HibernateEmployeeDao(em);
		dao.save(employee);
		
		employee.setName("Antonio");
		dao.update(employee);
		
		Employee employeeUpdated = dao.findBy(employee.getId());
		
		assertEquals("Antonio", employeeUpdated.getName());
	}
	
}

package com.mastering.hibernate.namedquery;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.mastering.hibernate.JPAUtil;
import com.mastering.hibernate.employee.Assistance;
import com.mastering.hibernate.employee.HibernateEmployeeDao;

@RunWith(MockitoJUnitRunner.class)
public class AssistanceTest {
	
	private EntityManager manager;

	@Before
	public void setup() {
		manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		
		Assistance alexandre = new Assistance("Alexandre", "Gama", "Software Engineer", "Engineer", "SP");
		Assistance patrick = new Assistance("Patrick", "Gama", "Software Developer", "TI", "SP");
		Assistance william = new Assistance("William", "Palmer", "Software Developer", "TI", "RJ");
		Assistance willson = new Assistance("Willson", "Pollack", "Data Sciense", "Engineer", "SP");
		
		HibernateEmployeeDao dao = new HibernateEmployeeDao(manager);
		dao.save(alexandre);
		dao.save(patrick);
		dao.save(william);
		dao.save(willson);
		
	}
	
	@After
	public void setdown() {
		HibernateEmployeeDao dao = new HibernateEmployeeDao(manager);
		dao.removeAll();
		manager.getTransaction().commit();
		manager.close();
	}
	
	@Test
	public void shouldReturnAllEmployeesWithFirstNameAlexandreUsingNamedQuery() throws Exception {
		HibernateEmployeeDao dao = new HibernateEmployeeDao(manager);
		List<Assistance> employees = dao.findByFirstNameUsingNamedQuery("Alexandre");
		
		assertEquals(1, employees.size());
	}

}

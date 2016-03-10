package com.mastering.hibernate.inheritance;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mastering.hibernate.JPAUtil;

public class UsersHibernateDaoTest {

	private UsersHibernateDao users;
	private EntityManager manager;

	@Before
	public void setUp() {
		manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		users = new UsersHibernateDao(manager);
	}
	
	/* If we save a new User without any information about the DescriminatorValue so the User will be saved with its own name "User" in the column called DTYPE
	 * We must use @DescriminatorValue to distinguish its subclasses from it. Even if we use the @DiscriminatorValue, the column to distinguish them will be called DTYPE
	 *  
	 */
	@Test
	public void shouldSaveANewUser() throws Exception {
		User user = new User("Alexandre Gama");
		
		users.save(user);
		User savedUser = users.findBy(user.getId());
		
		assertEquals("Alexandre Gama", savedUser.getName());
	}
	
	@After
	public void setDown() {
		manager.getTransaction().commit();
	}
}

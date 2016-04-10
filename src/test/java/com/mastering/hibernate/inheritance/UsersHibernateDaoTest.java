package com.mastering.hibernate.inheritance;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

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
		users.removeAll();
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
	
	@Test
	public void shouldSaveANewGuest() throws Exception {
		User user = new Guest("Alexandre", "ale");
		
		users.save(user);
		Guest savedGuest = (Guest) users.findBy(user.getId());
		
		assertEquals("Alexandre", savedGuest.getName());
		assertEquals("ale", savedGuest.getNickname());
	}
	
	@Test
	public void shouldRetrieveJustGuestByItsId() throws Exception {
		User alexandre = new User("Alexandre Gama");
		Guest gustavo = new Guest("Gustavo", "gusta");
		Host jorge = new Host("Jorge", "jo");
		
		users.save(alexandre);
		users.save(gustavo);
		users.save(jorge);
		
		Optional<Guest> guest = users.findUniqueGuestBy(gustavo.getId());
		
		assertTrue(guest.isPresent());
		assertEquals("Gustavo", guest.get().getName());
		assertEquals("gusta", guest.get().getNickname());
	}
	
	@Test
	public void shouldRetrieveAGuestUsingItsDiscriminatorValue() throws Exception {
		Guest alexandre = new Guest("Alexandre", "ale");
		Guest gustavo = new Guest("Gustavo", "gusta");
		Host jorge = new Host("Jorge", "jo");
		
		users.save(alexandre);
		users.save(gustavo);
		users.save(jorge);
		
		List<Guest> guests = users.findAllGuestsByItsDiscriminatorValue();

		assertEquals(guests.size(), 2);
		assertEquals("Alexandre", guests.get(0).getName());
		assertEquals("Gustavo", guests.get(1).getName());
	}
	
	@Test
	public void shouldIndicateThatAnUserObjectIsAGuest() throws Exception {
		Guest alexandre = new Guest("Alexandre", "ale");
		Host jorge = new Host("Jorge", "jo");
		User gustavo = new User("Gustavo");
		
		users.save(alexandre);
		users.save(jorge);
		users.save(gustavo);
		
		List<User> userList = users.findAllUsers();
		
		assertTrue(Guest.class.isAssignableFrom(userList.get(0).getClass()));
		assertFalse(Host.class.isAssignableFrom(userList.get(0).getClass()));
		
		assertTrue(Host.class.isAssignableFrom(userList.get(1).getClass()));
		assertFalse(Guest.class.isAssignableFrom(userList.get(1).getClass()));
		
		assertFalse(Host.class.isAssignableFrom(userList.get(2).getClass()));
		assertFalse(Guest.class.isAssignableFrom(userList.get(2).getClass()));
	}
	
	@Test
	public void shouldTryToRetriveAGuestUsingGuestObject() throws Exception {
		Guest alexandre = new Guest("Alexandre", "gaminha");
		
		users.save(alexandre);
		
		Guest userFound = users.findGuestUsingGuestObjectBy(alexandre.getId());
		
		assertEquals("Alexandre", userFound.getName());
	}
	
	@Test
	public void shouldTryToRetrieveAHosterUsingHosterObject() throws Exception {
		Host ronery = new Host("Ronery", "Av Paulista");
		
		users.save(ronery);
		
		Host hostFound = users.findHostUsingHostObjectByIts(ronery.getId());
		
		assertEquals("Ronery", hostFound.getName());
		assertEquals("Av Paulista", hostFound.getAddress());
	}
	
	@Test
	public void shouldTryToRetrieveAUserUsingUserObject() throws Exception {
		User user = new User("Alexandre");
		
		users.save(user);
		
		User userFound = users.findUserByIts(user.getId());
		
		assertEquals("Alexandre", userFound.getName());
	}
	
	
	@After
	public void setDown() {
		manager.getTransaction().commit();
	}
}

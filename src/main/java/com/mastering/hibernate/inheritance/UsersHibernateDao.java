package com.mastering.hibernate.inheritance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UsersHibernateDao {

	private EntityManager manager;

	public UsersHibernateDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void save(User user) {
		manager.persist(user);
	}

	public User findBy(Long id) {
		return manager.find(User.class, id);
	}

	public Optional<Guest> findUniqueGuestBy(Long id) {
		TypedQuery<User> query = manager.createQuery("select u from User u where u.id = :id", User.class);
		query.setParameter("id", id);
		User user = query.getSingleResult();
		
		return Optional.ofNullable((Guest) user);
	}

	public List<Guest> findAllGuestsByItsDiscriminatorValue() {
		TypedQuery<User> query = manager.createQuery("select u from User u where u.class = :discriminator", User.class);
		query.setParameter("discriminator", "GUEST");
		
		List<User> users = query.getResultList();
		List<Guest> guests = new ArrayList<>();
		users.stream().forEach(user -> guests.add((Guest)user));
		
		return guests;
	}
	
	public List<User> findAllUsers() {
		TypedQuery<User> query = manager.createQuery("select u from User u", User.class);
		
		return query.getResultList();
	}

	public void removeAll() {
		Query query = manager.createQuery("delete from User");
		query.executeUpdate();
	}
	
}

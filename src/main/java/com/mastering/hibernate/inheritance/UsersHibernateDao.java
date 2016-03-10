package com.mastering.hibernate.inheritance;

import javax.persistence.EntityManager;

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
	
}

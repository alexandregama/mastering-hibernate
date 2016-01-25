package com.mastering.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mastering-hibernate");
	
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.close();
		System.out.println("Entity Manager closed!");
	}
	
}

package com.mastering.hibernate.employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class HibernateEmployeeDao {

	private EntityManager em;

	public HibernateEmployeeDao(EntityManager em) {
		this.em = em;
	}

	public void save(Assistance employee) {
		em.persist(employee);
	}

	public Assistance findBy(Long id) {
		return em.find(Assistance.class, id);
	}

	public List<Assistance> findByFirstNameUsingNamedQuery(String firstName) {
		TypedQuery<Assistance> query = em.createNamedQuery("Assistance.findByFirstName", Assistance.class);
		query.setParameter("firstName", firstName);
		
		return query.getResultList();
	}

	public void removeAll() {
		Query query = em.createQuery("delete from Assistance");
		query.executeUpdate();
	}


}

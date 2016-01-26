package com.mastering.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mastering.hibernate.singletable.Employee;

public class EmployeeDao {

	private EntityManager em;

	public EmployeeDao(EntityManager em) {
		this.em = em;
	}

	public void save(Employee employee) {
		em.persist(employee);
	}

	public void update(Employee employee) {
		String jpql = "update Employee set name = :name where id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", employee.getId());
		query.setParameter("name", employee.getName());
		
		query.executeUpdate();
	}

	public Employee findBy(Long id) {
		return em.find(Employee.class, id);
	}

}

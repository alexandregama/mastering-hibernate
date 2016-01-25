package com.mastering.hibernate.dao;

import javax.persistence.EntityManager;

import com.mastering.hibernate.singletable.Employee;

public class EmployeeDao {

	private EntityManager em;

	public EmployeeDao(EntityManager em) {
		this.em = em;
	}

	public void save(Employee employee) {
		em.persist(employee);
	}

}

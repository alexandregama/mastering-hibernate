package com.mastering.hibernate.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.google.common.base.Objects;

@Entity
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "regular_employee")
public class RegularEmployee extends Employee {

	@Column(name = "salary")
	private Double salary;
	
	@Column(name = "bonus")
	private Double bonus;
	
	public RegularEmployee(Double salary, Double bonus) {
		this.salary = salary;
		this.bonus = bonus;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("salary", this.salary)
				.add("bonus", this.bonus)
				.toString();
	}
}

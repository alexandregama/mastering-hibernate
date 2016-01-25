package com.mastering.hibernate.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.google.common.base.Objects;

@Entity
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "contract_employee")
public class ContractEmployee extends Employee {

	@Column(name = "value_per_hour")
	private Double valuePerHour;
	
	@Column(name = "contract_period")
	private String contractPeriod;

	public ContractEmployee(Double valuePerHour, String contractPeriod) {
		this.valuePerHour = valuePerHour;
		this.contractPeriod = contractPeriod;
	}

	public Double getValuePerHour() {
		return valuePerHour;
	}

	public void setValuePerHour(Double valuePerHour) {
		this.valuePerHour = valuePerHour;
	}

	public String getContractPeriod() {
		return contractPeriod;
	}

	public void setContractPeriod(String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("valuePerHour", this.valuePerHour)
				.add("contractPeriod", this.contractPeriod)
				.toString();
	}
}

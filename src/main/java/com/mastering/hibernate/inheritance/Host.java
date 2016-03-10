package com.mastering.hibernate.inheritance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "HOST")
public class Host extends User {

	@Column(name = "address")
	private String address;
	
	@Deprecated //Hibernate eyes only
	Host() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}

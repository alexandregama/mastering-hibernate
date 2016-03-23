package com.mastering.hibernate.inheritance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "GUEST")
public class Guest extends User {

	@Column(name = "nickname")
	private String nickname;
	
	@Deprecated //Hibernate eyes only
	Guest() {
	}
	
	public Guest(String name, String nickname) {
		super(name);
		this.nickname = nickname;
	}



	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

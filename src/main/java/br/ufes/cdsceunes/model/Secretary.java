package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="secretary")
public class Secretary extends User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
		
	public Secretary() {
		
	}

	@Override
	void setRole(Role role) {
		if (role != Role.SECRETARY) {
			this.role = Role.SECRETARY;
		}
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

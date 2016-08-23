package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="secretary")
public class Secretary extends AbstractModel {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	@OneToOne
	private UserDetails details;
	
	public Secretary() {
		
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
	
	public UserDetails getUserDetails() {
		return details;
	}
	

}

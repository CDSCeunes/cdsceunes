package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;

@Entity
@Table(name="secretary")
public class Secretary extends AbstractModel {
	
	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private Long id;
	
	@JsonView(View.Summary.class)
	private String name;

	@OneToOne
	@JsonView(View.SummaryWithDetails.class)
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

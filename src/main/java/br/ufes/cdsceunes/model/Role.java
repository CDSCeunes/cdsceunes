package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;

@Entity
@Table(name="role")
public class Role {
	
	@Id
	@GeneratedValue
	@JsonView(View.SummaryWithDetails.class)
	private Long Id;
	
	@JsonView(View.SummaryWithDetails.class)
	private String name;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	

}

package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;

@Entity
@Table(name = "department")
public class Department extends AbstractModel {

	@JsonView(View.Summary.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonView(View.Summary.class)
	@NotBlank
	private String name;
	
	@JsonView(View.Summary.class)
	@NotBlank
	private String center;

	@JsonView(View.DepartmentWithTeachers.class)
	@OneToMany(mappedBy = "department")
	private List<Teacher> teachers;

	/* Getters and Setters */
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

}

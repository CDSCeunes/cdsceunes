package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Discipline extends AbstractModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String course;
	@NotBlank
	@OneToMany
	private List<String> semesters;
	
	// TODO TEL? What kind of attribute is that?
	
	/* Getters and Setters */
	
	public List<String> getSemesters() {
		return semesters;
	}
	public void setSemesters(List<String> semesters) {
		this.semesters = semesters;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	

}

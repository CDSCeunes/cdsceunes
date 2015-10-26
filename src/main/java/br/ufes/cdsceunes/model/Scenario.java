package br.ufes.cdsceunes.model;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.ufes.cdsceunes.model.Discipline;
import br.ufes.cdsceunes.model.Teacher;

@Entity
@Table
public class Scenario extends AbstractModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String semester;

	@OneToMany(cascade = CascadeType.ALL)
	private Map<Teacher, Discipline> scenario;
	
	
	/* Getters and Setters */
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Map<Teacher, Discipline> getScenario() {
		return scenario;
	}
	public void setScenario(Map<Teacher, Discipline> scenario) {
		this.scenario = scenario;
	}
	
}

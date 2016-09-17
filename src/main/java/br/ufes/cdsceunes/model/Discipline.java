package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.ufes.cdsceunes.util.serialiazers.DisciplineSerializer;

@Entity
@Table(name = "discipline")
@JsonSerialize(using = DisciplineSerializer.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Discipline extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String course;

	@OneToMany(mappedBy="discipline")
	private List<OfferedClass> classes;

	private Integer teoricLoad;

	private Integer exerciseLoad;

	private Integer labLoad;

	@OneToMany(mappedBy = "discipline")
	private List<Preferences> preferences;

	

	/* Getters and Setters */

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

	public List<OfferedClass> getClasses() {
		return classes;
	}

	public void setClasses(List<OfferedClass> classes) {
		this.classes = classes;
	}

	public Integer getTeoricLoad() {
		return teoricLoad;
	}

	public void setTeoricLoad(Integer teoricLoad) {
		this.teoricLoad = teoricLoad;
	}

	public Integer getExerciseLoad() {
		return exerciseLoad;
	}

	public void setExerciseLoad(Integer exerciseLoad) {
		this.exerciseLoad = exerciseLoad;
	}

	public Integer getLabLoad() {
		return labLoad;
	}

	public void setLabLoad(Integer labLoad) {
		this.labLoad = labLoad;
	}

	public List<Preferences> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preferences> preferences) {
		this.preferences = preferences;
	}

	public Long getId() {
		return id;
	}


}

package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "semesters", joinColumns = @JoinColumn(name = "discipline_id"))
	@Column(name = "semester")
	private List<String> semesters;

	private Integer teoricLoad;

	private Integer exerciseLoad;

	private Integer labLoad;

	@OneToMany(mappedBy = "discipline")
	private List<Preferences> preferences;

	/* Getters and Setters */

	public Long getId() {
		return id;
	}

	public Integer getTeoricLoad() {
		return teoricLoad;
	}

	public void setTeoricLoad(int teoricLoad) {
		this.teoricLoad = teoricLoad;
	}

	public Integer getExerciseLoad() {
		return exerciseLoad;
	}

	public void setExerciseLoad(int exerciseLoad) {
		this.exerciseLoad = exerciseLoad;
	}

	public Integer getLabLoad() {
		return labLoad;
	}

	public void setLabLoad(int labLoad) {
		this.labLoad = labLoad;
	}

	@CollectionTable(name = "semesters", joinColumns = @JoinColumn(name = "discipline_id"))
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

	public void setPreferences(List<Preferences> prefs) {
		this.preferences = prefs;
	}

	public List<Preferences> getPreferences() {
		return preferences;
	}

}

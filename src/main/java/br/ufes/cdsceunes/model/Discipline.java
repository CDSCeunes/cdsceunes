package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table
public class Discipline extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String course;

	@ElementCollection
	@CollectionTable(name = "semesters", joinColumns = @JoinColumn(name = "discipline_id") )
	@Column(name = "semester")
	private List<String> semesters;

	private int teoricLoad;
	
	private int exerciseLoad;
	
	private int labLoad;

	/* Getters and Setters */

	public Long getId() {
		return id;
	}
	public int getTeoricLoad() {
		return teoricLoad;
	}

	public void setTeoricLoad(int teoricLoad) {
		this.teoricLoad = teoricLoad;
	}

	public int getExerciseLoad() {
		return exerciseLoad;
	}

	public void setExerciseLoad(int exerciseLoad) {
		this.exerciseLoad = exerciseLoad;
	}

	public int getLabLoad() {
		return labLoad;
	}

	public void setLabLoad(int labLoad) {
		this.labLoad = labLoad;
	}

	@CollectionTable(name = "semesters", joinColumns = @JoinColumn(name = "discipline_id") )
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

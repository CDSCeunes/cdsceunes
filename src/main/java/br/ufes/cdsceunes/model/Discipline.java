package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;

@Entity
@Table(name = "discipline")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Discipline extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonView(View.Summary.class)
	private String name;

	@JsonView(View.Summary.class)
	private String course;

	@JsonView(View.Summary.class)
	@JsonIgnoreProperties("discipline")
	@OneToMany(mappedBy="discipline")
	private List<OfferedClass> classes;

	@JsonView(View.Summary.class)
	private Integer teoricLoad;

	@JsonView(View.Summary.class)
	private Integer exerciseLoad;

	@JsonView(View.Summary.class)
	private Integer labLoad;
	

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

	@JsonIgnoreProperties
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

	public Long getId() {
		return id;
	}


}

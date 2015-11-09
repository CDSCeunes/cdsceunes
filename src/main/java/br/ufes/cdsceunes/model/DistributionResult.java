package br.ufes.cdsceunes.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class DistributionResult extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;
	
	@OneToOne
	private Teacher teacher;
	@OneToOne
	private Discipline discipline;
	
	@ManyToOne
	private Scenario scenario;
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Discipline getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	public Long getId() {
		return id;
	}
	
	public DistributionResult(Discipline discipline, Teacher teacher) {
		this.discipline = discipline;
		this.teacher = teacher;
	}
	
	public DistributionResult() {
	}
}

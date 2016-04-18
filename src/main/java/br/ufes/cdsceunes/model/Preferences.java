package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Preferences extends AbstractModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated
	private Preference value;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Teacher teacher;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Discipline discipline;

	public Preference getValue() {
		return value;
	}

	public void setValue(Preference value) {
		this.value = value;
	}

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
	
	

}

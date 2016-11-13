package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;

@Entity
@Table(name="preferences")
public class Preferences extends AbstractModel {

	@JsonView(View.Summary.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonView(View.Summary.class)
	@Enumerated
	private Preference value;

	@JsonView(View.Summary.class)
	@JsonIgnoreProperties({"preferences", "positions"})
	@ManyToOne(fetch=FetchType.EAGER)
	private Teacher teacher;

	@JsonView(View.Summary.class)
	@JsonIgnoreProperties({"preferences", "teacher", "semester"})
	@ManyToOne
	private OfferedClass offeredClass;

	public Long getId() {
		return id;
	}

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

	public OfferedClass getOfferedClass() {
		return offeredClass;
	}

	public void setOfferedClass(OfferedClass offer) {
		this.offeredClass = offer;
	}

}

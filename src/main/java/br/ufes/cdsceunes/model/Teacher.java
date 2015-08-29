package br.ufes.cdsceunes.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Teacher extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String login;

	private Boolean available;

	@Temporal(value = TemporalType.DATE)
	private Calendar admissionDate;

	@Temporal(value = TemporalType.DATE)
	private Calendar returnFromLastRemoval;
	
	@Temporal(value = TemporalType.DATE)
	private Calendar returnFromCapacitacion;

	@OneToMany(mappedBy = "teacher")
	private List<Position> positions;

	@ManyToOne
	private Department department;

	/* Getters and Setters */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	// Will this really be needed? Weird.
	public void setLogin(String login) {
		this.login = login;
	}

	public Calendar getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Calendar admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Calendar getReturnFromLastRemoval() {
		return returnFromLastRemoval;
	}

	public void setReturnFromLastRemoval(Calendar returnFromLastRemoval) {
		this.returnFromLastRemoval = returnFromLastRemoval;
	}

	public Calendar getReturnFromCapacitacion() {
		return returnFromCapacitacion;
	}

	public void setReturnFromCapacitacion(Calendar returnFromCapacitacion) {
		this.returnFromCapacitacion = returnFromCapacitacion;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}

package br.ufes.cdsceunes.model;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@NotBlank
	private boolean available;
	@NotBlank
	private Calendar admissionDate;
	private Calendar returnFromLastRemoval;
	private Calendar returnFromCapacitacion;
	private ArrayList<Position> positions;
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
	public ArrayList<Position> getPositions() {
		return positions;
	}
	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	
}

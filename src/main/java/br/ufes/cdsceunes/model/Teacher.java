package br.ufes.cdsceunes.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Teacher extends AbstractModel {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String login;
	@NotBlank
	private Calendar admissionDate;
	private Calendar returnFromLastRemoval;
	private Calendar returnFromCapacitacion;
	@NotBlank
	private boolean available;
	
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
	
	// Will this really be needed?
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
	
	
	
	
}

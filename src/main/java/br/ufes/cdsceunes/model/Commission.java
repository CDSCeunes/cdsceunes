package br.ufes.cdsceunes.model;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Commission extends AbstractModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	@NotBlank
	private int minNumber;
	@NotBlank
	private int maxNumber;
	@NotBlank
	private Calendar creationDate;
	@NotBlank
	private String scope;
	@NotBlank
	private ArrayList<Position> positions;
	
	
	/* Getters and Setters */
	
	public ArrayList<Position> getPositions() {
		return positions;
	}
	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
	
	
}

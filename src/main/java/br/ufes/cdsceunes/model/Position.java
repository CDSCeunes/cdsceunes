package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Position extends AbstractModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private double minWorkload;
	@NotBlank
	private double maxWorkload;
	@NotBlank
	private double currentWorkload;
	
	
	/* Getters and Setters */
	
	public double getMinWorkload() {
		return minWorkload;
	}
	public void setMinWorkload(double minWorkload) {
		this.minWorkload = minWorkload;
	}
	public double getMaxWorkload() {
		return maxWorkload;
	}
	public void setMaxWorkload(double maxWorkload) {
		this.maxWorkload = maxWorkload;
	}
	public double getCurrentWorkload() {
		return currentWorkload;
	}
	public void setCurrentWorkload(double currentWorkload) {
		this.currentWorkload = currentWorkload;
	}
	
	
}

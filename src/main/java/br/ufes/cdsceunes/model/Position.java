package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private double minWorkload;

	private double maxWorkload;

	private double currentWorkload;

	@ManyToOne
	private Commission commission;

	@ManyToOne
	private Teacher teacher;

	/* Getters and Setters */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

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

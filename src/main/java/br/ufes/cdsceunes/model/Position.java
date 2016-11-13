package br.ufes.cdsceunes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;

@Entity
@Table(name = "position")
public class Position extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.Summary.class)
	private Long id;

	@JsonView(View.Summary.class)
	private String name;

	@JsonView(View.Summary.class)
	private double minWorkload;

	@JsonView(View.Summary.class)
	private double maxWorkload;

	@JsonView(View.Summary.class)
	private double currentWorkload;

	@ManyToOne
	private Commission commission;

	@ManyToOne
	private Teacher teacher;
	
	public Long getId() {
		return id;
	}

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

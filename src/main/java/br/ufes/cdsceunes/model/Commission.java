package br.ufes.cdsceunes.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;

@Entity
@Table(name = "commission")
public class Commission extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.Summary.class)
	private Long id;

	@JsonView(View.Summary.class)
	private String name;

	@JsonView(View.Summary.class)
	private int minNumber;

	@JsonView(View.Summary.class)
	private int maxNumber;

	@Column(columnDefinition="DATE")
	@JsonView(View.Summary.class)
	private LocalDate creationDate;

	@JsonView(View.Summary.class)
	private String scope;

	@OneToMany(mappedBy = "commission")
	@JsonView(View.Summary.class)
	private List<Position> positions;

	/* Getters and Setters */

	public List<Position> getPositions() {
		return positions;
	}

	public Long getId() {
		return id;
	}

	public void setPositions(List<Position> positions) {
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

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}

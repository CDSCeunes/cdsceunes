package br.ufes.cdsceunes.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "scenario")
public class Scenario extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Semester semester;

	@OneToMany(mappedBy = "scenario")
	private List<DistributionResult> distribution;
	
	@ManyToMany(mappedBy="scenarios")
	private List<Semester> semesters;

	@Column(nullable=false)
	boolean activated;


	public void put(DistributionResult result) {
		distribution.add(result);
	}

	public List<DistributionResult> getDistributionList() {
		return distribution;
	}

	public Scenario() {
		distribution = new LinkedList<DistributionResult>();
		activated = false;
	}

	public Long getId() {
		return id;
	}

	public void setState(boolean state) {
		activated = state;
	}

	public boolean getState() {
		return activated;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

}

package br.ufes.cdsceunes.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table//(name="t_scenario")
public class Scenario extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String semester;

	@OneToMany(mappedBy="scenario", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<DistributionResult> distribution;
	
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
	
	public String getSemester() {
		return semester;
	}
	
}

package br.ufes.cdsceunes.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Scenario extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String semester;

	@OneToMany(mappedBy="scenario")
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
	
	public void activate() {
		activated = true;
	}
	
}

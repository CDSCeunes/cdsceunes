package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.ufes.cdsceunes.util.model.SemesterPK;

@Entity
@Table(name = "semester")
public class Semester {
	
	@EmbeddedId
	private SemesterPK id;
	
	@OneToMany(mappedBy="semester")
	private List<OfferedClass> classes;
	
	@ManyToMany
	private List<Scenario> scenarios;

	
	public SemesterPK getId() {
		return this.id;
	}


}
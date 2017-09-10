package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.ufes.cdsceunes.util.deserializers.SemesterDeserializer;
import br.ufes.cdsceunes.util.serializers.SemesterSerializer;

@Entity
@Table(name = "semester")
@JsonSerialize(using = SemesterSerializer.class)
@JsonDeserialize(using = SemesterDeserializer.class)
public class Semester {

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(length = 10)),
			@AttributeOverride(name = "semester", column = @Column(length = 10)) })
	private SemesterPK id;

	@OneToMany(mappedBy = "semester")
	private List<OfferedClass> classes;

	@ManyToMany
	private List<Scenario> scenarios;

	public Semester() {

	}

	public Semester(String year, String semester) {
		this.id = new SemesterPK(year, semester);
	}

	public SemesterPK getId() {
		return this.id;
	}

}
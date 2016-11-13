package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.ufes.cdsceunes.jsonview.View;
import br.ufes.cdsceunes.util.deserializers.SemesterDeserializer;
import br.ufes.cdsceunes.util.model.SemesterPK;
import br.ufes.cdsceunes.util.serializers.SemesterSerializer;

@Entity
@Table(name = "semester")
@JsonSerialize(using = SemesterSerializer.class)
@JsonDeserialize(using = SemesterDeserializer.class)
public class Semester {

	@EmbeddedId
	@JsonView(View.Summary.class)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Semester other = (Semester) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return this.id.getYear() + "/" + this.id.getSemester();
	}

}

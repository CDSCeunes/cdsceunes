package br.ufes.cdsceunes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class SemesterPK implements Serializable {

	private static final long serialVersionUID = 6980566632729531190L;


	protected String year;
	protected String semester;

	public SemesterPK() {
	}

	public SemesterPK(String year, String semester) {
		this.year = year;
		this.semester = semester;
	}

	public String getYear() {
		return year;
	}

	public String getSemester() {
		return semester;
	}
}

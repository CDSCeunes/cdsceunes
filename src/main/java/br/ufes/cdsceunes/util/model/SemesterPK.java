package br.ufes.cdsceunes.util.model;

import java.io.Serializable;

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

	public boolean equals(SemesterPK obj) {
		return this.getYear() == obj.getYear() && this.getSemester() == obj.getSemester();
	}
}

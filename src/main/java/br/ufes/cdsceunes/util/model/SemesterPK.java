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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		SemesterPK other = (SemesterPK) obj;
		if (semester == null) {
			if (other.semester != null)
				return false;
		} else if (!semester.equals(other.semester))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}

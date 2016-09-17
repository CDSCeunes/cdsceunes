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
}

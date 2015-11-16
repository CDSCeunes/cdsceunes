package br.ufes.cdsceunes.databuilders;

import br.ufes.cdsceunes.model.Discipline;

public class DisciplineBuilder {
	private static int count = 1;
	
	public static Discipline createDiscipline() {
		Discipline d = new Discipline();
		d.setName("Discipline " + count++);
		return d;
	}
	
	public static Discipline createDiscipline(String name) {
		Discipline disc = new Discipline();
		disc.setName(name);
		return disc;
	}

}

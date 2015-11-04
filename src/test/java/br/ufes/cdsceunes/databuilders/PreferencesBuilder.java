package br.ufes.cdsceunes.databuilders;

import br.ufes.cdsceunes.model.Discipline;
import br.ufes.cdsceunes.model.Preference;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.model.Teacher;

public class PreferencesBuilder {
	
	public static Preferences createPreferences(Teacher t, Discipline d, Preference pf) {
		Preferences p = new Preferences();
		p.setDiscipline(d);
		p.setTeacher(t);
		p.setValue(pf);
		return p;
	}
	
}

package br.ufes.cdsceunes.lib.distribution;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufes.cdsceunes.databuilders.DisciplineBuilder;
import br.ufes.cdsceunes.databuilders.PreferencesBuilder;
import br.ufes.cdsceunes.databuilders.TeachersBuilder;
import br.ufes.cdsceunes.model.Discipline;
import br.ufes.cdsceunes.model.DistributionResult;
import br.ufes.cdsceunes.model.Preference;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.model.Scenario;
import br.ufes.cdsceunes.model.Teacher;

public class DistributionRunnerTest {
	
	private List<Preferences> preferences = new LinkedList<Preferences>();;
	
	@BeforeClass
	public static void construct() {
		
	}
	
	@After
	public void destruct() {
		preferences.clear();
	}
	
	
	@Test
	public void testOneTeacherAndOneDisciplineWithWantPreference() {
		Teacher pardal = TeachersBuilder.createTeacher("Pardal");
		Discipline architecture = DisciplineBuilder.createDiscipline("Architecture");
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(1, results.size());
		DistributionResult result = results.get(0);
		Assert.assertEquals("Pardal", result.getTeacher().getName());
		Assert.assertEquals("Archictecture", result.getDiscipline().getName());
	}
	
	/*
	public void testTwoDisciplinesWithWantPreference() {
		Teacher pardal = TeachersBuilder.createTeacher("Pardal");
		Teacher joao = TeachersBuilder.createTeacher("Joao");
		Teacher maria = TeachersBuilder.createTeacher("Maria");
		Discipline architecture = DisciplineBuilder.createDiscipline("Architecture");
		Discipline prog3 = DisciplineBuilder.createDiscipline("Prog3");
		Discipline os = DisciplineBuilder.createDiscipline("OS");
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, os, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, prog3, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(maria, prog3, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(maria, os, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(maria, architecture, Preference.NONE));
		preferences.add(PreferencesBuilder.createPreferences(joao, os, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(joao, prog3, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(joao, architecture, Preference.NONE));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		for (DistributionResult dist : results) {
			String teacher = dist.getTeacher().getName();
			
		}
	}*/
	
	
	
	
	
}

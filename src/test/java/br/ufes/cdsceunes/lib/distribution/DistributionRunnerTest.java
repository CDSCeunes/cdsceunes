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
	private Teacher pardal;
	private Teacher t1;
	private Discipline architecture;
	private Discipline prog3;
	private Discipline os;

	@Before
	public void construct() {
		preferences.clear();
		pardal = TeachersBuilder.createTeacher("Pardal");
		architecture = DisciplineBuilder.createDiscipline("Architecture");
		prog3 = DisciplineBuilder.createDiscipline("Prog3");
		os = DisciplineBuilder.createDiscipline("OS");
		t1 = TeachersBuilder.createTeacher("T1");
	}

	@After
	public void destruct() {
		preferences.clear();
	}

	@Test
	public void testOneTeacherAndOneDisciplineWithWantPreference() {
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(1, results.size());
		DistributionResult result = results.get(0);
		Assert.assertEquals("Pardal", result.getTeacher().getName());
		Assert.assertEquals("Architecture", result.getDiscipline().getName());
	}

	@Test
	public void testTwoTeacherAndOneDisciplineWithWantAndAcceptPreference() {
		preferences.add(PreferencesBuilder.createPreferences(t1, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(1, results.size());
		DistributionResult result = results.get(0);
		Assert.assertEquals("Pardal", result.getTeacher().getName());
		Assert.assertEquals("Architecture", result.getDiscipline().getName());
	}

	@Test
	public void testMultipleTeachersAndOneDisciplineWithJustAcceptPreferences() {
		preferences.add(PreferencesBuilder.createPreferences(t1, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(TeachersBuilder.createTeacher(), architecture, Preference.ACCEPT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(1, results.size());
		DistributionResult result = results.get(0);
		Assert.assertEquals("Pardal",result.getTeacher().getName());
		Assert.assertEquals("Architecture", result.getDiscipline().getName());
	}
	
	@Test
	public void testMultipleTeachersWithMultiplePreferences() {
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, prog3, Preference.NONE));
		preferences.add(PreferencesBuilder.createPreferences(pardal, os, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t1, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t1, prog3, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t1, os, Preference.ACCEPT));
		Teacher t2 = TeachersBuilder.createTeacher("T2");
		preferences.add(PreferencesBuilder.createPreferences(t2, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t2, prog3, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t2, os, Preference.NONE));
		Teacher t3 = TeachersBuilder.createTeacher("T3");
		preferences.add(PreferencesBuilder.createPreferences(t3, architecture, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t3, prog3, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t3, os, Preference.NONE));
		Teacher t4 = TeachersBuilder.createTeacher("T4");
		preferences.add(PreferencesBuilder.createPreferences(t4, architecture, Preference.NONE));
		preferences.add(PreferencesBuilder.createPreferences(t4, prog3, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t4, os, Preference.WANT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(3, results.size());
		// Pos 0
		Assert.assertEquals("Pardal",results.get(0).getTeacher().getName());
		Assert.assertEquals("Architecture", results.get(0).getDiscipline().getName());
		// Pos 1
		Assert.assertEquals("T4",results.get(1).getTeacher().getName());
		Assert.assertEquals("OS", results.get(1).getDiscipline().getName());	
		// Pos 2
		Assert.assertEquals("T2",results.get(2).getTeacher().getName());
		Assert.assertEquals("Prog3", results.get(2).getDiscipline().getName());	

	}
	
	/*
	 * public void testTwoDisciplinesWithWantPreference() { Teacher pardal =
	 * TeachersBuilder.createTeacher("Pardal"); Teacher joao =
	 * TeachersBuilder.createTeacher("Joao"); Teacher maria =
	 * TeachersBuilder.createTeacher("Maria"); Discipline architecture =
	 * DisciplineBuilder.createDiscipline("Architecture"); Discipline prog3 =
	 * DisciplineBuilder.createDiscipline("Prog3"); Discipline os =
	 * DisciplineBuilder.createDiscipline("OS");
	 * preferences.add(PreferencesBuilder.createPreferences(pardal,
	 * architecture, Preference.WANT));
	 * preferences.add(PreferencesBuilder.createPreferences(pardal, os,
	 * Preference.WANT));
	 * preferences.add(PreferencesBuilder.createPreferences(pardal, prog3,
	 * Preference.ACCEPT));
	 * preferences.add(PreferencesBuilder.createPreferences(maria, prog3,
	 * Preference.WANT));
	 * preferences.add(PreferencesBuilder.createPreferences(maria, os,
	 * Preference.ACCEPT));
	 * preferences.add(PreferencesBuilder.createPreferences(maria, architecture,
	 * Preference.NONE));
	 * preferences.add(PreferencesBuilder.createPreferences(joao, os,
	 * Preference.WANT));
	 * preferences.add(PreferencesBuilder.createPreferences(joao, prog3,
	 * Preference.ACCEPT));
	 * preferences.add(PreferencesBuilder.createPreferences(joao, architecture,
	 * Preference.NONE)); List<DistributionResult> results =
	 * DistributionRunner.generateDistribution(preferences).getDistributionList(
	 * ); for (DistributionResult dist : results) { String teacher =
	 * dist.getTeacher().getName();
	 * 
	 * } }
	 */

}

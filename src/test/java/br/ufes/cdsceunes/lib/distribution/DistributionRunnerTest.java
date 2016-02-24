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
	private Discipline cg;

	@Before
	public void construct() {
		preferences.clear();
		pardal = TeachersBuilder.createTeacher("Pardal");
		architecture = DisciplineBuilder.createDiscipline("Architecture");
		prog3 = DisciplineBuilder.createDiscipline("Prog3");
		os = DisciplineBuilder.createDiscipline("OS");
		cg = DisciplineBuilder.createDiscipline("CG");
		t1 = TeachersBuilder.createTeacher("T1");
	}

	@After
	public void destruct() {
		preferences.clear();
	}

	@Test
	public void testOneTeacherAndOneDisciplineWithWantPreference() {
		System.out.println("TEST 1! Pardal should get 1 discipline!");
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(1, results.size());
		DistributionResult result = results.get(0);
		Assert.assertEquals("Pardal", result.getTeacher().getName());
		Assert.assertEquals("Architecture", result.getDiscipline().getName());
		DistributionRunner.clearMap();
	}

	@Test
	public void testTwoTeacherAndOneDisciplineWithWantAndAcceptPreference() {
		System.out.println("\nTEST 2! Pardal should get 1 discipline!");
		preferences.add(PreferencesBuilder.createPreferences(t1, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(1, results.size());
		DistributionResult result = results.get(0);
		Assert.assertEquals("Pardal", result.getTeacher().getName());
		Assert.assertEquals("Architecture", result.getDiscipline().getName());
		DistributionRunner.clearMap();
	}

	@Test
	public void testMultipleTeachersAndOneDisciplineWithJustAcceptPreferences() {
		System.out.println("\nTEST 3! Pardal should get 1 discipline!");
		preferences.add(PreferencesBuilder.createPreferences(t1, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(TeachersBuilder.createTeacher(), architecture, Preference.ACCEPT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(1, results.size());
		DistributionResult result = results.get(0);
		Assert.assertEquals("Pardal",result.getTeacher().getName());
		Assert.assertEquals("Architecture", result.getDiscipline().getName());
		DistributionRunner.clearMap();
	}
	
	@Test
	public void testMultipleTeachersWithMultiplePreferences() {
		DistributionRunner.clearMap();
		System.out.println("\nTEST 4! Every teacher should get 1 discipline!");
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
		DistributionRunner.clearMap();

	}
	
	@Test
	public void testMultipleTeachersWithMultiplePreferencesTeacherGetsMultipleDisciplines() {
		System.out.println("\nTEST 5! Pardal should get 2 disciplines! And some other guy just 1.");
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, prog3, Preference.NONE));
		preferences.add(PreferencesBuilder.createPreferences(pardal, os, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t1, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t1, prog3, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t1, os, Preference.NONE));
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
		preferences.add(PreferencesBuilder.createPreferences(t4, os, Preference.ACCEPT));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(3, results.size());
		// Pos 0
		Assert.assertEquals("Pardal",results.get(0).getTeacher().getName());
		Assert.assertEquals("Architecture", results.get(0).getDiscipline().getName());
		// Pos 1
		Assert.assertEquals("Pardal",results.get(1).getTeacher().getName());
		Assert.assertEquals("OS", results.get(1).getDiscipline().getName());	
		// Pos 2
		Assert.assertEquals("T2",results.get(2).getTeacher().getName());
		Assert.assertEquals("Prog3", results.get(2).getDiscipline().getName());	
		DistributionRunner.clearMap();

	}
	
	@Test
	public void testMultipleTeachersWithMultiplePreferencesWithSomeGuyThatWantsEverything() {
		
		System.out.println("\nTEST 6! Pardal should get just 3 disciplines, not 4!");
		preferences.add(PreferencesBuilder.createPreferences(pardal, architecture, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, prog3, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, os, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(pardal, cg, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t1, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t1, prog3, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t1, os, Preference.NONE));
		preferences.add(PreferencesBuilder.createPreferences(t1, cg, Preference.WANT));
		Teacher t2 = TeachersBuilder.createTeacher("T2");
		preferences.add(PreferencesBuilder.createPreferences(t2, architecture, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t2, prog3, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t2, os, Preference.NONE));
		preferences.add(PreferencesBuilder.createPreferences(t2, cg, Preference.NONE));
		Teacher t3 = TeachersBuilder.createTeacher("T3");
		preferences.add(PreferencesBuilder.createPreferences(t3, architecture, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t3, prog3, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t3, os, Preference.NONE));
		preferences.add(PreferencesBuilder.createPreferences(t3, cg, Preference.ACCEPT));
		Teacher t4 = TeachersBuilder.createTeacher("T4");
		preferences.add(PreferencesBuilder.createPreferences(t4, architecture, Preference.NONE));
		preferences.add(PreferencesBuilder.createPreferences(t4, prog3, Preference.ACCEPT));
		preferences.add(PreferencesBuilder.createPreferences(t4, os, Preference.WANT));
		preferences.add(PreferencesBuilder.createPreferences(t4, cg, Preference.NONE));
		List<DistributionResult> results = DistributionRunner.generateDistribution(preferences).getDistributionList();
		Assert.assertEquals(4, results.size());
		// Pos 0
		Assert.assertEquals("Pardal",results.get(0).getTeacher().getName());
		Assert.assertEquals("Architecture", results.get(0).getDiscipline().getName());
		// Pos 1
		Assert.assertEquals("Pardal",results.get(1).getTeacher().getName());
		Assert.assertEquals("CG", results.get(1).getDiscipline().getName());
		// Pos 2
		Assert.assertEquals("Pardal",results.get(2).getTeacher().getName());
		Assert.assertEquals("OS", results.get(2).getDiscipline().getName());	
		// Pos 3
		Assert.assertNotEquals("Pardal",results.get(3).getTeacher().getName());
		Assert.assertEquals("Prog3", results.get(3).getDiscipline().getName());	
		DistributionRunner.clearMap();
	}
	

}

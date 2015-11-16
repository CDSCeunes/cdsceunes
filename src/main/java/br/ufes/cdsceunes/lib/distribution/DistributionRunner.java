package br.ufes.cdsceunes.lib.distribution;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import br.ufes.cdsceunes.model.DistributionResult;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.model.Scenario;

public class DistributionRunner {

	private static HelpTable table;
	private static List<HelpTable> tables = new LinkedList<HelpTable>();

	public static Scenario generateDistribution(List<Preferences> preferences) {
		/*
		 * Sort preferences list by the Discipline name
		 */
		/*
		 * TODO tentar ordenar pelo DAO
		 */
		Comparator<Preferences> byDiscipline = (p1, p2) -> p1.getDiscipline().getName().compareTo(p2.getDiscipline().getName());
		Comparator<Preferences> byName = (p1, p2) -> p1.getTeacher().getName().compareTo(p2.getTeacher().getName());
	
		preferences.sort(byDiscipline.thenComparing(byName));
		
		/*System.out.println("Inicia impressao: ");
		for (Preferences p : preferences) {
			System.out.println(p.getTeacher().getName() + " -> " + p.getDiscipline().getName() + " -> " + p.getValue());
		}*/

		///preferences.sort((Preferences p1, Preferences p2) -> p1.getDiscipline().getName()
		//		.compareTo(p2.getDiscipline().getName()));

		/*
		 * Since our preferences list is sorted by discipline, we need to save
		 * the last discipline iterated to keep track when another discipline
		 * appears. For each discipline, the sum of the preference's value and
		 * the number of occurrences will be obtained in order to generate the
		 * teacher's distribution.
		 */
		String lastDisciplineName = new String(preferences.get(0).getDiscipline().getName());

		int offset = 0;

		for (Preferences p : preferences) {
			HelpTable table;
			List<HelpTable> existance = tables.stream()
					.filter((h) -> h.getDisciplineName().equals(p.getDiscipline().getName()))
					.collect(Collectors.toList());
			if (existance.size() == 0) {
				table = new HelpTable(p.getDiscipline().getName());
				tables.add(table);
				table.setIndexOffset(offset);
				offset = preferences.indexOf(p);
			} else {
				table = existance.get(0);
			}

			/*if (!p.getDiscipline().getName().equals(lastDisciplineName)) {
				table.setIndexOffset(offset);
				tables.add(table);
				offset = preferences.indexOf(p);
				lastDisciplineName = p.getDiscipline().getName();
				table.setSumOfAllPreferences(0);
				table.getIndexes().clear();
				table.setNumberOfOccurences(0);
			}*/
			table.increaseSum(p.getValue().ordinal());
			table.getIndexes().add(p.getValue().ordinal());
			if (p.getValue().ordinal() != 0) {
				table.increaseOccurrences(1);
			}
			offset++;
		}
		// Print table
		/*System.out.println("Imprimindo tabelas:");
		for (HelpTable table: tables) {
			System.out.println(table.getDisciplineName() + " -> " + table.getNumberOfOccurences());
		}*/
		
		// return DistributionRunner.chooseTeacher(preferences);
		return DistributionRunner.selectTeachers(preferences);
	}

	private static Scenario selectTeachers(List<Preferences> preferences) {
		Scenario scenario = new Scenario();

		for (HelpTable table : tables) {
			List<Integer> indexes = table.getIndexes();
			if (indexes.contains(2)) {
				scenario.put(DistributionRunner.getDistribution(preferences, table, 2));
			} else if (indexes.contains(1)) {
				scenario.put(DistributionRunner.getDistribution(preferences, table, 1));
			}
		}
		tables.clear();
		return scenario;
	}

	private static DistributionResult getDistribution(List<Preferences> preferences, HelpTable table,
			int preferenceValue) {
		DistributionResult distribution = new DistributionResult();
		List<Integer> indexes = table.getIndexes();
		for (int i = 0; i < indexes.size(); i++) {
			if (indexes.get(i) == preferenceValue) {
				int displacement = i + table.getIndexOffset();
				distribution.setDiscipline(preferences.get(displacement).getDiscipline());
				distribution.setTeacher(preferences.get(displacement).getTeacher());
				return distribution;
			}
		}

		return distribution;
	}
	/*
	 * private static Scenario chooseTeacher(List<Preferences> preferences) {
	 * int occurrencePriority = 1; int teacherSelectedFlag = 0; int loop =
	 * tables.size(); Scenario distribution = new Scenario(); while (loop > 0) {
	 * for (HelpTable h : tables) { if (h.getNumberOfOccurences() ==
	 * occurrencePriority) { if (h.getNumberOfOccurences() == 1) { for (int i =
	 * 0; i < h.getIndexes().size(); i++) { if (h.getIndexes().get(i) != 0) {
	 * int displacement = i + h.getIndexOffset(); distribution.put(new
	 * DistributionResult(preferences.get(displacement).getDiscipline(),
	 * preferences.get(displacement).getTeacher())); } } } else { for (int i =
	 * 0; i < h.getIndexes().size(); i++) { if (h.getIndexes().get(i) == 2) {
	 * distribution.put(new DistributionResult(preferences.get(i +
	 * h.getIndexOffset()).getDiscipline(), preferences.get(i +
	 * h.getIndexOffset()).getTeacher())); teacherSelectedFlag = 1; } } if
	 * (teacherSelectedFlag == 0) { for (int i = 0; i < h.getIndexes().size();
	 * i++) { if (h.getIndexes().get(i) == 1) {
	 * 
	 * distribution.put(new DistributionResult(preferences.get(i +
	 * h.getIndexOffset()).getDiscipline(), preferences.get(i +
	 * h.getIndexOffset()).getTeacher())); teacherSelectedFlag = 1; } } }
	 * teacherSelectedFlag = 0; } } } occurrencePriority++; loop--; } return
	 * distribution; }
	 */
}

package br.ufes.cdsceunes.lib.distribution;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.ufes.cdsceunes.model.DistributionResult;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.model.Scenario;

public class DistributionRunner {

	private static HelpTable table;
	private static List<HelpTable> tables = new LinkedList<HelpTable>();
	private static Map<String, Integer> distributionCheckout = new HashMap<String, Integer>();

	public static Scenario generateDistribution(List<Preferences> preferences) {

		/*
		 * The preferences are sorted by a two-factor procedure that organizes
		 * the list first by the discipline name, and second by the teachers'
		 * name. Therefore we obtain a list that can be easily processed
		 * linearly, discipline by discipline.
		 */
		Comparator<Preferences> byDiscipline = (p1, p2) -> p1.getOfferedClass().getDiscipline().getName()
				.compareTo(p2.getOfferedClass().getDiscipline().getName());
		Comparator<Preferences> byName = (p1, p2) -> p1.getTeacher().getName().compareTo(p2.getTeacher().getName());

		preferences.sort(byDiscipline.thenComparing(byName));

		/*
		 * Since our preferences list is sorted by discipline, we need to save
		 * the last discipline iterated to keep track when another discipline
		 * appears. For each discipline, the sum of the preference's value and
		 * the number of occurrences will be obtained in order to generate the
		 * teacher's distribution.
		 */
		String lastDisciplineName = new String(preferences.get(0).getOfferedClass().getDiscipline().getName());

		/*
		 * The offset is used to keep track of the current list position when
		 * the transition between the disciplines occur.
		 */
		int offset = 0;

		for (Preferences p : preferences) {
			HelpTable table;
			List<HelpTable> existance = tables.stream()
					.filter((h) -> h.getDisciplineName().equals(p.getOfferedClass().getDiscipline().getName()))
					.collect(Collectors.toList());
			/*
			 * Checks the possibility that the current discipline has not
			 * already been evaluated. Therefore, the 'existance' size would be
			 * equal zero.
			 */
			if (existance.size() == 0) {
				table = new HelpTable(p.getOfferedClass().getDiscipline().getName());
				tables.add(table);
				table.setIndexOffset(offset);
				offset = preferences.indexOf(p);
			} else {
				table = existance.get(0);
			}

			table.increaseSum(p.getValue().ordinal());
			table.getIndexes().add(p.getValue().ordinal());
			if (p.getValue().ordinal() != 0) {
				table.increaseOccurrences(1);
			}
			offset++;
		}

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

		/*
		 * Check if the map is being correctly built
		 */
		for (Map.Entry<String, Integer> entry : distributionCheckout.entrySet()) {
			System.out.println("Name : " + entry.getKey() + "\tNumber of disciplines : " + entry.getValue());
		}
		System.out.println("***END OF THE PRINT***\n");
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

				if (!distributionCheckout.containsKey(preferences.get(displacement).getTeacher().getName())
						|| (distributionCheckout.get(preferences.get(displacement).getTeacher().getName()) < 3)) {

					distribution.setDiscipline(preferences.get(displacement).getOfferedClass().getDiscipline());
					distribution.setTeacher(preferences.get(displacement).getTeacher());

					/*
					 * The set teacher is saved in the map so the disciplines
					 * yet to be processed are able to use the information as
					 * distribution parameters.
					 */
					if (!distributionCheckout.containsKey(preferences.get(displacement).getTeacher().getName())) {
						distributionCheckout.put(preferences.get(displacement).getTeacher().getName(), 1);
					} else {
						distributionCheckout.replace(preferences.get(displacement).getTeacher().getName(),
								distributionCheckout.get(preferences.get(displacement).getTeacher().getName()) + 1);
					}

					return distribution;
				}
			}
		}

		return distribution;
	}

	public static void clearMap() {
		distributionCheckout.clear();
	}

}

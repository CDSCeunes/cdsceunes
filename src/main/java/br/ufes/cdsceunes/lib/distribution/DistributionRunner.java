package br.ufes.cdsceunes.lib.distribution;

import java.util.LinkedList;
import java.util.List;

import br.ufes.cdsceunes.model.DistributionResult;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.model.Scenario;

public class DistributionRunner {
	
	private static HelpTable table;
	private static List<HelpTable> tables = new LinkedList<HelpTable>();
	
	public static void generateDistribution(List<Preferences> preferences) {
		/*
		 * Sort preferences list by the Discipline name
		 */
		/*
		 * TODO tentar ordenar pelo DAO
		 */
		preferences.sort((Preferences p1, Preferences p2) -> p1.getDiscipline().getName()
				.compareTo(p2.getDiscipline().getName()));

		/*
		 * Since our preferences list is sorted by discipline, we need to save
		 * the last discipline iterated to keep track when another discipline
		 * appears. For each discipline, the sum of the preference's value and
		 * the number of occurrences will be obtained in order to generate the
		 * teacher's distribution.
		 */
		String lastDisciplineName = new String(preferences.get(0).getDiscipline().getName());
		HelpTable table = new HelpTable();
		int offset = 0;

		for (Preferences p : preferences) {
			if (!p.getDiscipline().getName().equals(lastDisciplineName)) {
				table.setIndexOffset(offset);
				tables.add(table);
				offset = preferences.indexOf(p);
				lastDisciplineName = p.getDiscipline().getName();
				table.setSumOfAllPreferences(0);
				table.getIndexes().clear();
				table.setNumberOfOccurences(0);
			}
			table.increaseSum(p.getValue().ordinal());
			table.getIndexes().add(p.getValue().ordinal());
			if (p.getValue().ordinal() != 0) {
				table.increaseOccurrences(1);
			}
		}
		DistributionRunner.chooseTeacher(preferences);

	}

	private static Scenario chooseTeacher(List<Preferences> preferences) {
		int occurrencePriority = 1;
		int teacherSelectedFlag = 0;
		int loop = tables.size();
		Scenario distribution = new Scenario();
		while (loop > 0) {
			for (HelpTable h : tables) {
				if (h.getNumberOfOccurences() == occurrencePriority) {
					if (h.getNumberOfOccurences() == 1) {
						for (int i = 0; i < h.getIndexes().size(); i++) {
							if (h.getIndexes().get(i) != 0) {
								distribution.put(new DistributionResult(preferences.get(i + h.getIndexOffset()).getDiscipline(),
										preferences.get(i + h.getIndexOffset()).getTeacher()));
							}
						}
					} else {
						for (int i = 0; i < h.getIndexes().size(); i++) {
							if (h.getIndexes().get(i) == 2) {
								distribution.put(new DistributionResult(preferences.get(i + h.getIndexOffset()).getDiscipline(),
										preferences.get(i + h.getIndexOffset()).getTeacher()));
								teacherSelectedFlag = 1;
							}
						}
						if (teacherSelectedFlag == 0) {
							for (int i = 0; i < h.getIndexes().size(); i++) {
								if (h.getIndexes().get(i) == 1) {
									
									distribution.put(new DistributionResult(preferences.get(i + h.getIndexOffset()).getDiscipline(),
											preferences.get(i + h.getIndexOffset()).getTeacher()));
									teacherSelectedFlag = 1;
								}
							}
						}
						teacherSelectedFlag = 0;
					}
				}
			}
			occurrencePriority++;
			loop--;
		}
		return distribution;
	}

}

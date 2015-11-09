package br.ufes.cdsceunes.lib.distribution;

import java.util.LinkedList;
import java.util.List;

public class HelpTable {

		private int sumOfAllPreferences;
		private int numberOfOccurences;
		private int indexOffset;
		private List<Integer> indexes;
		private String disciplineName;

		public HelpTable() {
			sumOfAllPreferences = 0;
			numberOfOccurences = 0;
			indexOffset = 0;
			indexes = new LinkedList<Integer>();
		}
		
		public HelpTable(String disciplineName) {
			this();
			this.disciplineName = disciplineName;
		}
			
		public int getSumOfAllPreferences() {
			return sumOfAllPreferences;
		}
		public void setSumOfAllPreferences(int sumOfAllPreferences) {
			this.sumOfAllPreferences = sumOfAllPreferences;
		}
		public int getNumberOfOccurences() {
			return numberOfOccurences;
		}
		public void setNumberOfOccurences(int numberOfOccurences) {
			this.numberOfOccurences = numberOfOccurences;
		}
		public int getIndexOffset() {
			return indexOffset;
		}
		public void setIndexOffset(int indexOffset) {
			this.indexOffset = indexOffset;
		}
		public List<Integer> getIndexes() {
			return indexes;
		}
		public void setIndexes(List<Integer> indexes) {
			this.indexes = indexes;
		}
		
		public void increaseSum(int value) {
			sumOfAllPreferences += value;
		}
		
		public void increaseOccurrences(int value) {
			numberOfOccurences += value;
		}
		
		public String getDisciplineName() {
			return disciplineName;
		}
		
		

}

package br.ufes.cdsceunes.model;

public enum Preference {
	/*
	 * The 'NONE' enum has the value 0, as the 'ACCEPT' has the value 1 and
	 * finally the 'WANT' has the value 2. Called by the .ordinal() method
	 */
	NONE {
		@Override
		public String toString() {
			return "Nenhum";
		}
	},
	ACCEPT {
		@Override
		public String toString() {
			return "Aceito";
		}
	},
	WANT {
		@Override
		public String toString() {
			return "Quero";
		}
	}
}

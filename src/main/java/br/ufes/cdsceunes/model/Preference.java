package br.ufes.cdsceunes.model;

public enum Preference {
	NONE {
		@Override
		public String toString() {
			return "Nenhum";
		}
	}, ACCEPT {
		@Override
		public String toString() {
			return "Aceito";
		}
	}, WANT {
		@Override
		public String toString() {
			return "Quero";
		}
	}
}

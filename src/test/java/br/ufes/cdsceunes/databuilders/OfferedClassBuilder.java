package br.ufes.cdsceunes.databuilders;

import br.ufes.cdsceunes.model.Discipline;
import br.ufes.cdsceunes.model.OfferedClass;

public class OfferedClassBuilder {
	public static OfferedClass createOffer(Discipline discipline) {
		OfferedClass offer = new OfferedClass();
		offer.setDiscipline(discipline);
		return offer;
	}

}

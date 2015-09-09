package br.ufes.cdsceunes.converter;

import java.beans.PropertyEditorSupport;

import br.ufes.cdsceunes.dao.DisciplineDAO;
import br.ufes.cdsceunes.model.Discipline;

public class DisciplineEditor extends PropertyEditorSupport {
	
	private final DisciplineDAO dao;
	
	public DisciplineEditor(DisciplineDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Discipline dis = dao.findById(Long.parseLong(text));
		setValue(dis);
	}
	
}

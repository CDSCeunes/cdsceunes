package br.ufes.cdsceunes.converter;

import java.beans.PropertyEditorSupport;

import br.ufes.cdsceunes.dao.CommissionDAO;
import br.ufes.cdsceunes.model.Commission;

public class CommissionEditor extends PropertyEditorSupport {
	private final CommissionDAO dao;
	
	public CommissionEditor(CommissionDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Commission com = dao.findById(Long.parseLong(text));
		setValue(com);
	}
}

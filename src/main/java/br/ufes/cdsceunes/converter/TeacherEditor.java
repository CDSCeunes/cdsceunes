package br.ufes.cdsceunes.converter;

import java.beans.PropertyEditorSupport;

import br.ufes.cdsceunes.dao.TeacherDAO;
import br.ufes.cdsceunes.model.Teacher;

public class TeacherEditor extends PropertyEditorSupport {
	
	private final TeacherDAO dao;
	
	public TeacherEditor(TeacherDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Teacher tea = dao.findById(Long.parseLong(text));
		setValue(tea);
	}
	
	
}

package br.ufes.cdsceunes.converter;

import java.beans.PropertyEditorSupport;

import br.ufes.cdsceunes.dao.DepartmentDAO;
import br.ufes.cdsceunes.model.Department;

public class DepartmentEditor extends PropertyEditorSupport {
	
	private final DepartmentDAO dao;
	
	public DepartmentEditor(DepartmentDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Department dep = dao.findById(Long.parseLong(text));
		setValue(dep);
	}
	
}

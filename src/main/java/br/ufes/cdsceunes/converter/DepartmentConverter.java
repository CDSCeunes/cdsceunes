package br.ufes.cdsceunes.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import br.ufes.cdsceunes.dao.DepartmentDAO;
import br.ufes.cdsceunes.model.Department;

public class DepartmentConverter implements Converter<String, Department> {

	@Autowired
	private DepartmentDAO departments;
	
	public Department convert(String id) {
		return departments.findById(Long.getLong(id));
	}
}

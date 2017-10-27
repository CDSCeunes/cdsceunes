package br.ufes.cdsceunes.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufes.cdsceunes.model.Teacher;

public class TeacherRepositoryImpl implements RepositoryCustom<Teacher> {

	@Autowired
	private TeacherRepository repository;

	@Override
	public List<String> save(Map<String, Object> updates, Teacher teacher) {
		String name = updates.get("name").toString();
		if (name != null) {
			teacher.setName(name);
		}

		repository.save(teacher);

		return new ArrayList<>();
	}

	@Override
	public List<String> save(Teacher updates, Teacher object) {
		object.setAdmissionDate(updates.getAdmissionDate());
		object.setName(updates.getName());
		
		repository.save(object);
		return null;
	}

}

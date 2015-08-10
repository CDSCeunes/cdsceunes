package br.ufes.cdsceunes.dao;

import java.util.List;
import br.ufes.cdsceunes.model.Teacher;

public class TeacherDAO extends AbstractDAO<Teacher> {

	@Override
	public Teacher findById(Integer id) {
		Teacher teacher = manager.find(Teacher.class, id);
		return teacher;
	}

	@Override
	public void save(Teacher teacher) {
		manager.persist(teacher);
	}

	@Override
	public List<Teacher> list() {
		return manager.createQuery("select Teacher t from Teacher", Teacher.class).getResultList();
	}

}

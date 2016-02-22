package br.ufes.cdsceunes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.cdsceunes.model.Teacher;

@Repository
public class TeacherDAO extends AbstractDAO<Teacher, Long> {

	@Override
	public Teacher findById(Long id) {
		Teacher teacher = manager.find(Teacher.class, id);
		return teacher;
	}

	@Override
	public void save(Teacher teacher) {
		manager.persist(teacher);
	}

	@Override
	public List<Teacher> list() {
		return manager.createQuery("select t from Teacher t", Teacher.class).getResultList();
	}
	
	public Teacher update(Teacher teacher) {
		return manager.merge(teacher);
	}

	@Override
	public void delete(Teacher teacher) {
		manager.remove(teacher);		
	}

}

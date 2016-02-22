package br.ufes.cdsceunes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.cdsceunes.model.Department;

@Repository
public class DepartmentDAO extends AbstractDAO<Department, Long> {

	@Override
	public Department findById(Long id) {
		return manager.find(Department.class, id);
	}

	@Override
	public void save(Department dep) {
		manager.persist(dep);
	}

	@Override
	public List<Department> list() {
		return manager.createQuery("select t from Department t", Department.class).getResultList();
	}

	@Override
	public void delete(Department object) {
		// TODO Auto-generated method stub
		
	}

}

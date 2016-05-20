package br.ufes.cdsceunes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.cdsceunes.model.OfferedClass;

@Repository
public class ClassDAO extends AbstractDAO<OfferedClass, Long> {

	@Override
	public OfferedClass findById(Long id) {
		return manager.find(OfferedClass.class, id);
	}

	@Override
	public void save(OfferedClass ocla) {
		manager.persist(ocla);
	}

	@Override
	public List<OfferedClass> list() {
		return manager.createQuery("select o from OfferedClass o", OfferedClass.class).getResultList();
	}

	@Override
	public void delete(OfferedClass object) {
		// TODO Auto-generated method stub
		
	}
}

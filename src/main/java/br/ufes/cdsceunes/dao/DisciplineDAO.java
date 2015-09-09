package br.ufes.cdsceunes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.cdsceunes.model.Discipline;

@Repository
public class DisciplineDAO extends AbstractDAO<Discipline, Long> {
	
	@Override
	public Discipline findById(Long id) {
		return manager.find(Discipline.class, id);
	}

	@Override
	public void save(Discipline dis) {
		manager.persist(dis);
	}

	@Override
	public List<Discipline> list() {
		return manager.createQuery("from Discipline as d left join fetch d.semesters", Discipline.class).getResultList();
	}

}

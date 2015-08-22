package br.ufes.cdsceunes.dao;

import java.util.List;

import br.ufes.cdsceunes.model.Commission;

public class CommissionDAO extends AbstractDAO<Commission, Long> {

	@Override
	public Commission findById(Long id) {
		return manager.find(Commission.class, id);
	}

	@Override
	public void save(Commission commission) {
		manager.persist(commission);
	}

	@Override
	public List<Commission> list() {
		String jpql = "select c from Commission c";
		return manager.createQuery(jpql, Commission.class).getResultList();
	}

}

package br.ufes.cdsceunes.dao;

import java.util.List;

import br.ufes.cdsceunes.model.Position;

public class PositionDAO extends AbstractDAO<Position, Long> {

	@Override
	public Position findById(Long id) {
		return manager.find(Position.class, id);
	}

	@Override
	public void save(Position pos) {
		manager.persist(pos);
	}

	@Override
	public List<Position> list() {
		return manager.createQuery("select p from Position p", Position.class).getResultList();
	}

}

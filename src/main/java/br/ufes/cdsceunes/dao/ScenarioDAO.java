package br.ufes.cdsceunes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.cdsceunes.model.Scenario;

@Repository
public class ScenarioDAO extends AbstractDAO<Scenario, Long> {
	
	@Override
	public Scenario findById(Long id) {
		Scenario scenario = manager.find(Scenario.class, id);
		return scenario;
	}

	@Override
	public void save(Scenario scenario) {
		manager.persist(scenario);
	}

	@Override
	public List<Scenario> list() {
		return manager.createQuery("select s from Scenario s", Scenario.class).getResultList();
	}

}

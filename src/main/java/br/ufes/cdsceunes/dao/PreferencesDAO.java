package br.ufes.cdsceunes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.cdsceunes.model.Preferences;

@Repository
public class PreferencesDAO extends AbstractDAO<Preferences, Long> {
	
	@Override
	public Preferences findById(Long id) {
		return manager.find(Preferences.class, id);
	}
	
	@Override
	public void save(Preferences pref) {
		manager.persist(pref);
	}

	@Override
	public List<Preferences> list() {
		return manager.createQuery("select p from Preferences p", Preferences.class).getResultList();
	}
}

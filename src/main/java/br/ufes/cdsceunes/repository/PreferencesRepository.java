package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Preferences;

public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

}

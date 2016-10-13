package br.ufes.cdsceunes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufes.cdsceunes.model.Preferences;

public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

	@Query("SELECT prefs FROM Preferences prefs where prefs.offeredClass.id = :id")
	public List<Preferences> findByClass(@Param("id") Long id);
}

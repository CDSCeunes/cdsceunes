package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufes.cdsceunes.model.Scenario;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

}

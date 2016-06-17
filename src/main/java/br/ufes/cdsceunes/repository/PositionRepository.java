package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}

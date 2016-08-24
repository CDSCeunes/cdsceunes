package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

}

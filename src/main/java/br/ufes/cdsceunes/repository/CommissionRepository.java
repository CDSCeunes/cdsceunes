package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Commission;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

}

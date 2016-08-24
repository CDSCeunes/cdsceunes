package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Secretary;

public interface SecretaryRepository extends JpaRepository<Secretary, Long> {

}

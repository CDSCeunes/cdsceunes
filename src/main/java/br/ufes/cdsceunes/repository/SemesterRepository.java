package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Semester;
import br.ufes.cdsceunes.model.SemesterPK;

public interface SemesterRepository extends JpaRepository<Semester, SemesterPK> {

}

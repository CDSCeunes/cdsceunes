package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}

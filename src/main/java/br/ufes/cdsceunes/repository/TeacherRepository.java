package br.ufes.cdsceunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
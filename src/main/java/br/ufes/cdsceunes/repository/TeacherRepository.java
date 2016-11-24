package br.ufes.cdsceunes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufes.cdsceunes.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query("SELECT t FROM Teacher t WHERE t.available=1")
	List<Teacher> findAllAvailable();
	
}


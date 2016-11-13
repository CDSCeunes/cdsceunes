package br.ufes.cdsceunes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.cdsceunes.model.Department;
import br.ufes.cdsceunes.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	public List<Teacher> findByDepartmentName(Department department);
}
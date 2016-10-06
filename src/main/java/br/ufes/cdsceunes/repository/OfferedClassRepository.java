package br.ufes.cdsceunes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufes.cdsceunes.model.OfferedClass;
import br.ufes.cdsceunes.model.Semester;

public interface OfferedClassRepository extends JpaRepository<OfferedClass, Long> {

	@Query("SELECT oc FROM OfferedClass oc where oc.semester = :semester")
	public List<OfferedClass> findBySemester(@Param("semester") Semester semester);
}

package br.ufes.cdsceunes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.View;
import br.ufes.cdsceunes.model.OfferedClass;
import br.ufes.cdsceunes.model.Semester;
import br.ufes.cdsceunes.model.Teacher;
import br.ufes.cdsceunes.repository.TeacherRepository;

@RequestMapping("/api/v1/teachers")
@RestController
public class TeacherController extends AbstractController<Teacher, TeacherRepository> {

	
	@JsonView(View.TeacherWithClasses.class)
	@RequestMapping(value = "/with_classes", method=RequestMethod.GET)
	public ResponseEntity<List<Teacher>> getTeachersWithClasses() {
		return new ResponseEntity<List<Teacher>>(repository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(View.TeacherWithClasses.class)
	@RequestMapping(value="/with_classes/{year}/{semester}", method=RequestMethod.GET)
	public ResponseEntity<List<Teacher>> getTeachersWithClassesBySemester(@PathVariable String year, @PathVariable String semester) {
		semester = Integer.valueOf(semester).toString();
		List<Teacher> teachers = repository.findAll();
		Semester semester_ = new Semester(year, semester);
		teachers.forEach(teacher -> {
			List<OfferedClass> tmp = teacher.getClasses().stream()
					.filter(class_ ->  (class_.getSemester().equals(semester_)))
					.collect(Collectors.toList());
			teacher.setClasses(tmp);
		});
		return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
		Teacher t = repository.findOne(id);
		if (t == null || t.getId() != teacher.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.saveAndFlush(teacher);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
		repository.save(teacher);
		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Teacher> delete(@PathVariable("id") Long id) {
		Teacher teacher = repository.findOne(id);
		if (teacher != null) {
			repository.delete(teacher);
			return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Teacher>(HttpStatus.BAD_REQUEST);
	}

}

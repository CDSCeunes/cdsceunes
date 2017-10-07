package br.ufes.cdsceunes.controller.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.controller.AbstractController;
import br.ufes.cdsceunes.jsonview.TeacherView;
import br.ufes.cdsceunes.model.Teacher;
import br.ufes.cdsceunes.repository.TeacherRepository;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

	@Autowired
	private TeacherRepository repository;
	
	@GetMapping({"", "/"})
	@JsonView(TeacherView.Public.class)
	public ResponseEntity<List<Teacher>> index() {
		List<Teacher> teachers = repository.findAll();
		System.out.println(teachers.size());
		return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
	}
	
	@PutMapping("/:id")
	public ResponseEntity<?> patch(@RequestBody Teacher teacher, @RequestParam("id") Long id) {
		if (teacher.getId() == id) {
			repository.save(teacher);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping({"", "/"})
	public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
		teacher = repository.save(teacher);
		return new ResponseEntity<>(teacher, HttpStatus.OK);
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

	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Teacher> getUserById(@PathVariable Long id) {
		Teacher teacher = repository.findOne(id);
		if (teacher != null) {
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
		return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
	}*/


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

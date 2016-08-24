package br.ufes.cdsceunes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.ufes.cdsceunes.model.Teacher;
import br.ufes.cdsceunes.repository.TeacherRepository;

@RequestMapping("/api/v1/teachers")
@RestController
public class TeacherController extends AbstractController {

	@Autowired
	private TeacherRepository repository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Teacher> listAll() {
		System.out.println("testing");
		return repository.findAll();
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Teacher> getUserById(@PathVariable Long id) {
		Teacher teacher = repository.findOne(id);
		if (teacher != null) {
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
		return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
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

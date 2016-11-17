package br.ufes.cdsceunes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.model.Teacher;
import br.ufes.cdsceunes.model.UserDetails;
import br.ufes.cdsceunes.repository.TeacherRepository;
import br.ufes.cdsceunes.repository.UserDetailsRepository;

@RequestMapping("/api/v1/teachers")
@RestController
public class TeacherController extends AbstractController<Teacher, TeacherRepository> {

	@Autowired
	private UserDetailsRepository detailsRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
		Teacher t = repository.findOne(id);
		if (t == null || t.getId() != teacher.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.saveAndFlush(teacher);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET) public
	 * ResponseEntity<Teacher> getUserById(@PathVariable Long id) { Teacher
	 * teacher = repository.findOne(id); if (teacher != null) { return new
	 * ResponseEntity<Teacher>(teacher, HttpStatus.OK); } return new
	 * ResponseEntity<Teacher>(HttpStatus.NO_CONTENT); }
	 */

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
		UserDetails details = detailsRepository.findByLogin(teacher.getDetails().getLogin());
		if (details != null) {
			teacher.setDetails(details);
			teacher = repository.save(teacher);
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
		return new ResponseEntity<Teacher>(HttpStatus.BAD_REQUEST);

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

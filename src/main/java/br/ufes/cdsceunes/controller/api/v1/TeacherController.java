package br.ufes.cdsceunes.controller.api.v1;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.ufes.cdsceunes.jsonview.TeacherView;
import br.ufes.cdsceunes.model.Teacher;
import br.ufes.cdsceunes.repository.TeacherRepository;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

	@Autowired
	private TeacherRepository repository;

	@GetMapping("")
	@JsonView(TeacherView.Public.class)
	public ResponseEntity<?> index() {
		List<Teacher> teachers = repository.findAll();
		return ResponseEntity.ok(teachers);
	}

	@GetMapping("/:id")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Teacher teacher = repository.findOne(id);
		if (teacher == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(teacher);
	}

	@PostMapping("")
	public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
		teacher = repository.save(teacher);
		return ResponseEntity.ok(teacher);
	}

	@PatchMapping("/:id")
	public ResponseEntity<?> patch(@RequestBody Map<String, Object> updates, @PathVariable("id") Long id) {
		Teacher teacher = repository.findOne(id);
		if (teacher == null) {
			return ResponseEntity.notFound().build();
		}
		List<String> errors = repository.save(updates, teacher);
		if (!errors.isEmpty()) {
			return new ResponseEntity<List<String>>(errors, HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok("resource updated");

	}

	@PutMapping("/:id")
	public ResponseEntity<?> put(@RequestBody Teacher updates, @PathVariable("id") Long id) {
		Teacher teacher = repository.findOne(id);
		if (teacher == null) {
			return ResponseEntity.notFound().build();
		}
		List<String> errors = repository.save(updates, teacher);
		if (!errors.isEmpty()) {
			return new ResponseEntity<List<String>>(errors, HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok("resource updated");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Teacher teacher = repository.findOne(id);
		if (teacher != null) {
			// repository.delete(teacher);
			teacher.setAvailable(false);
			teacher.getDetails().deactivate();
			repository.save(teacher);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody
	 * Teacher teacher) { Teacher t = repository.findOne(id); if (t == null ||
	 * t.getId() != teacher.getId()) { return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); } repository.saveAndFlush(teacher);
	 * return new ResponseEntity<>(HttpStatus.OK); }
	 */

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET) public
	 * ResponseEntity<Teacher> getUserById(@PathVariable Long id) { Teacher teacher
	 * = repository.findOne(id); if (teacher != null) { return new
	 * ResponseEntity<Teacher>(teacher, HttpStatus.OK); } return new
	 * ResponseEntity<Teacher>(HttpStatus.NO_CONTENT); }
	 */

}

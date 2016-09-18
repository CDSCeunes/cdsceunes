package br.ufes.cdsceunes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.model.Department;
import br.ufes.cdsceunes.repository.DepartmentRepository;

@RequestMapping("/api/v1/departments")
@RestController
public class DepartmentController extends AbstractController<Department, DepartmentRepository> {

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
		Department d = repository.findOne(id);
		if (d == null || d.getId() != department.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.saveAndFlush(department);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Department getDepartmentById(@PathVariable Long id) {
		return repository.findOne(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Department> save(@RequestBody Department dep) {
		repository.save(dep);
		return new ResponseEntity<Department>(dep, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Department> delete(@PathVariable("id") Long id) {
		Department dep = repository.findOne(id);
		if (dep != null) {
			repository.delete(dep);
			return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
	}
}

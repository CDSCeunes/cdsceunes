package br.ufes.cdsceunes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.model.Discipline;
import br.ufes.cdsceunes.repository.DisciplineRepository;

@RequestMapping("/api/v1/disciplines")
@RestController
public class DisciplineController extends AbstractController<Discipline, DisciplineRepository> {

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline discipline) {
		Discipline d = repository.findOne(id);
		if (d == null || d.getId() != discipline.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		repository.saveAndFlush(discipline);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Discipline getDisciplineById(@PathVariable Long id) {
		return repository.findOne(id);
	}*/

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Discipline> save(@RequestBody Discipline discipline) {
		repository.save(discipline);
		return new ResponseEntity<Discipline>(discipline, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Discipline> delete(@PathVariable("id") Long id) {
		Discipline discipline = repository.findOne(id);
		if (discipline != null) {
			repository.delete(discipline);
			return new ResponseEntity<Discipline>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Discipline>(HttpStatus.BAD_REQUEST);
	}
}

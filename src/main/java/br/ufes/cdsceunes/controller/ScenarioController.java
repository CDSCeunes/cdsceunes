package br.ufes.cdsceunes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.model.Scenario;
import br.ufes.cdsceunes.repository.ScenarioRepository;

@RequestMapping("/api/v1/scenarios")
@RestController
public class ScenarioController extends AbstractController<Scenario, ScenarioRepository> {

	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Scenario getScenarioId(@PathVariable Long id) {
		return repository.findOne(id);
	}
	*/
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Scenario> save(@RequestBody Scenario scenario) {
		repository.save(scenario);
		return new ResponseEntity<Scenario>(scenario, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Scenario> delete(@PathVariable("id") Long id) {
		Scenario scenario = repository.findOne(id);
		if(scenario != null) {
			return new ResponseEntity<Scenario>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Scenario>(HttpStatus.BAD_REQUEST);
	}
	
}

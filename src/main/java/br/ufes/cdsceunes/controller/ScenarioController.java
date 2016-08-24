package br.ufes.cdsceunes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.ufes.cdsceunes.model.Scenario;
import br.ufes.cdsceunes.repository.ScenarioRepository;

@RequestMapping("/api/v1/scenarios")
@RestController
public class ScenarioController extends AbstractController {

	@Autowired
	private ScenarioRepository repository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Scenario> listAll() {
		return repository.findAll();
	}
}

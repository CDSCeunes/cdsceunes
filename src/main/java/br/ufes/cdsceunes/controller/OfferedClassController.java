package br.ufes.cdsceunes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.ufes.cdsceunes.model.OfferedClass;
import br.ufes.cdsceunes.repository.OfferedClassRepository;

@RequestMapping("/api/v1/classes")
@RestController
public class OfferedClassController extends AbstractController {

	@Autowired
	private OfferedClassRepository repository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<OfferedClass> listAll() {
		return repository.findAll();
	}
}

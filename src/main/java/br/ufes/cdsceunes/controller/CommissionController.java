package br.ufes.cdsceunes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.ufes.cdsceunes.model.Commission;
import br.ufes.cdsceunes.repository.CommissionRepository;

@RequestMapping("/api/v1/commissions")
@RestController
public class CommissionController extends AbstractController {

	@Autowired
	private CommissionRepository repository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Commission> listAll() {
		return repository.findAll();
	}

}

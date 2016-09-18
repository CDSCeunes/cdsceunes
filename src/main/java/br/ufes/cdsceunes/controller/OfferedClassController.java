package br.ufes.cdsceunes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.model.OfferedClass;
import br.ufes.cdsceunes.repository.OfferedClassRepository;

@RequestMapping("/api/v1/classes")
@RestController
public class OfferedClassController extends AbstractController<OfferedClass, OfferedClassRepository> {

}

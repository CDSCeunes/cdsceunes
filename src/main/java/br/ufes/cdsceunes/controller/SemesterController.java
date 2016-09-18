package br.ufes.cdsceunes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.model.Semester;
import br.ufes.cdsceunes.repository.SemesterRepository;

@RequestMapping("/api/v1/semester")
@RestController
public class SemesterController extends AbstractController<Semester, SemesterRepository> {


}

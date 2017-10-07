package br.ufes.cdsceunes.controller.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.controller.AbstractController;
import br.ufes.cdsceunes.model.Semester;
import br.ufes.cdsceunes.repository.SemesterRepository;

@RequestMapping("/api/v1/semesters")
@RestController
public class SemesterController extends AbstractController<Semester, SemesterRepository> {


}

package br.ufes.cdsceunes.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.dao.DisciplineDAO;
import br.ufes.cdsceunes.model.Discipline;

@Controller
@Transactional	
@RequestMapping("/disciplines")
@RestController
public class DisciplineController extends AbstractController {

	@Autowired
	private DisciplineDAO disciplines;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Discipline>> listAllDisciplines() {
		List<Discipline> list = disciplines.list();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Discipline>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Discipline>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline discipline) {
		disciplines.update(discipline);
		return new ResponseEntity<Discipline>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
		Discipline d = disciplines.findById(id);
		if (d != null) {
			return new ResponseEntity<Discipline>(d, HttpStatus.OK);
		}
		return new ResponseEntity<Discipline>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Discipline> save(@RequestBody Discipline discipline) {
		disciplines.save(discipline);
		return new ResponseEntity<Discipline>(discipline, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Discipline> delete(@PathVariable("id") Long id) {
		Discipline discipline = disciplines.findById(id);
		if (discipline != null) {
			disciplines.delete(discipline);
			return new ResponseEntity<Discipline>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Discipline>(HttpStatus.BAD_REQUEST);
	}

	/*
	 * @RequestMapping("/") public ModelAndView list() { ModelAndView mad = new
	 * ModelAndView("discipline/list");
	 * mad.addObject("disciplines",disciplines.list()); return mad; }
	 * 
	 * @RequestMapping(value = "/form", name = "addDiscipline") public
	 * ModelAndView form(@ModelAttribute Discipline discipline) { ModelAndView
	 * mad = new ModelAndView("discipline/form"); return mad; }
	 * 
	 * @RequestMapping(method = RequestMethod.POST, name="createDiscipline",
	 * value="save") public ModelAndView
	 * save(@ModelAttribute("discipline") @Valid Discipline discipline,
	 * BindingResult binding, RedirectAttributes redirectAttributes) { if
	 * (binding.hasErrors()) { return form(discipline); }
	 * disciplines.save(discipline);
	 * redirectAttributes.addFlashAttribute("sucess",
	 * "Disciplina cadastrado com sucesso!"); return new
	 * ModelAndView("redirect:"); }
	 */
}

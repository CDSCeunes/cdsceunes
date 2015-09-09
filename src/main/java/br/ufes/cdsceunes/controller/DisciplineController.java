package br.ufes.cdsceunes.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.cdsceunes.dao.DisciplineDAO;
import br.ufes.cdsceunes.model.Discipline;

@Controller
@Transactional
@RequestMapping("/disciplines")
public class DisciplineController extends AbstractController {
	
	@Autowired
	private DisciplineDAO disciplines;
	
	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("discipline/list");
		mad.addObject("disciplines",disciplines.list());
		return mad;
	}

	@RequestMapping(value = "/form", name = "addDiscipline")
	public ModelAndView form(@ModelAttribute Discipline discipline) {
		ModelAndView mad = new ModelAndView("discipline/form");
		return mad;
	}

	@RequestMapping(method = RequestMethod.POST, name="createDiscipline", value="save")
	public ModelAndView save(@ModelAttribute("discipline") @Valid Discipline discipline, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			return form(discipline);
		}
		disciplines.save(discipline);
		redirectAttributes.addFlashAttribute("sucess", "Disciplina cadastrado com sucesso!");
		return new ModelAndView("redirect:");
	}
}

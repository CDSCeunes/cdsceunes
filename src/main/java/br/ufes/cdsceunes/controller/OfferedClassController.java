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

import br.ufes.cdsceunes.dao.ClassDAO;
import br.ufes.cdsceunes.model.OfferedClass;

@Controller
@Transactional
@RequestMapping("/classes")
public class OfferedClassController extends AbstractController{

	@Autowired
	private ClassDAO classes;
	
	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("class/list");
		mad.addObject("class",classes.list());
		return mad;
	}

	@RequestMapping(value = "/form", name = "addClasses")
	public ModelAndView form(@ModelAttribute OfferedClass offeredClass) {
		ModelAndView mad = new ModelAndView("class/form");
		return mad;
	}

	@RequestMapping(method = RequestMethod.POST, name="createClasses", value="save")
	public ModelAndView save(@ModelAttribute("offeredClass") @Valid OfferedClass offeredClass, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			return form(offeredClass);
		}
		classes.save(offeredClass);
		redirectAttributes.addFlashAttribute("sucess", "Turma cadastrada com sucesso!");
		return new ModelAndView("redirect:");
	}
}

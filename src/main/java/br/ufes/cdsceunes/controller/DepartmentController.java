package br.ufes.cdsceunes.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.cdsceunes.model.Department;

@Controller
@Transactional
@RequestMapping("/department")
public class DepartmentController extends AbstractController {

	//TODO Colocar repository correspondente aqui:
	
	
	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("/list");
		//Adicionar objeto com método list no ModelAndView
		return mad;
	}
	
	@RequestMapping(value = "/form", name = "add")
	public ModelAndView form(@ModelAttribute Department department) {
		ModelAndView mad = new ModelAndView("/form");
		return mad;
	}
	
	@RequestMapping(method = RequestMethod.POST, name="create", value="save")
	public ModelAndView save(@ModelAttribute("teacher") @Valid Department department, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			return form(department);
		}
		
		//TODO Salvar no repository
		
		redirectAttributes.addFlashAttribute("sucess", "Departamento cadastrado com sucesso");
		return new ModelAndView("redirect:");
	}
}

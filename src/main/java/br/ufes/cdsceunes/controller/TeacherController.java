package br.ufes.cdsceunes.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.cdsceunes.dao.TeacherDAO;
import br.ufes.cdsceunes.model.Teacher;
import br.ufes.cdsceunes.validators.TeacherValidator;

@Controller
@Transactional
@RequestMapping("/teachers")
public class TeacherController extends AbstractController {

	@Autowired
	private TeacherDAO teachers;
	
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		//dataBinder.setValidator(new TeacherValidator());
	}

	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("teacher/list");
		mad.addObject("teachers", teachers.list());
		return mad;
	}

	@RequestMapping(value = "/form", name = "add")
	public ModelAndView form(@ModelAttribute Teacher teacher) {
		ModelAndView mad = new ModelAndView("teacher/form");
		return mad;
	}
 
	@RequestMapping(method = RequestMethod.POST, name="create", value="save")
	public ModelAndView save(@ModelAttribute("teacher") @Valid Teacher teacher, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			return form(teacher);
		}
		
		teachers.save(teacher);
		
		redirectAttributes.addFlashAttribute("sucess", "Professor cadastrado com sucesso");
		return new ModelAndView("redirect:");
	}
}

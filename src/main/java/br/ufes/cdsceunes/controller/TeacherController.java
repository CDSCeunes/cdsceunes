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

import br.ufes.cdsceunes.converter.DepartmentEditor;
import br.ufes.cdsceunes.dao.DepartmentDAO;
import br.ufes.cdsceunes.dao.DisciplineDAO;
import br.ufes.cdsceunes.dao.TeacherDAO;
import br.ufes.cdsceunes.model.Department;
import br.ufes.cdsceunes.model.Teacher;
import br.ufes.cdsceunes.validators.TeacherValidator;

@Controller
@Transactional
@RequestMapping("/teachers")
public class TeacherController extends AbstractController {

	@Autowired
	private TeacherDAO teachers;
	
	@Autowired
	private DepartmentDAO departments;
	
<<<<<<< HEAD
	@Autowired
	private DisciplineDAO disciplines;
=======
>>>>>>> nikolas-fix

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new TeacherValidator());
		binder.registerCustomEditor(Department.class, new DepartmentEditor(departments));
	}
	
	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("teacher/list");
		mad.addObject("teachers", teachers.list());
		return mad;
	}

	@RequestMapping(value = "/form", name = "addTeacher")
	public ModelAndView form(@ModelAttribute Teacher teacher) {
		ModelAndView mad = new ModelAndView("teacher/form");
		mad.addObject("departmentList", departments.list());
		return mad;
	}
	
<<<<<<< HEAD
	@RequestMapping(value = "/preferences", name = "addPreference")
		public ModelAndView preference(@ModelAttribute Teacher teacher) {
			ModelAndView mad = new ModelAndView("teacher/preferenceForm");
			mad.addObject("teacherList", teachers.list());
			mad.addObject("disciplineList", disciplines.list());
			return mad;
		}

=======
>>>>>>> nikolas-fix
	@RequestMapping(method = RequestMethod.POST, name="createTeacher", value="save")
	public ModelAndView save(@ModelAttribute("teacher") @Valid Teacher teacher, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			System.out.println(binding.getFieldError());
			System.out.println("Deu erro");
			return form(teacher);
		}
		System.out.println("Deu certo!");
		teachers.save(teacher);
		redirectAttributes.addFlashAttribute("sucess", "Professor cadastrado com sucesso!");
		return new ModelAndView("redirect:");
	}
	
	@RequestMapping(method = RequestMethod.PUT, name="createPreference", value="save")
	public ModelAndView update(@ModelAttribute("teacher") @Valid Teacher teacher, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			System.out.println(binding.getFieldError());
			System.out.println("Deu erro");
			return form(teacher);
		}
		System.out.println("Deu certo!");
		teachers.update(teacher);
		redirectAttributes.addFlashAttribute("sucess", "Preferência alterada com sucesso!");
		return new ModelAndView("redirect:");
	}
}

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

import br.ufes.cdsceunes.dao.DepartmentDAO;
import br.ufes.cdsceunes.model.Department;

@Controller
@Transactional
@RequestMapping("/departments")
public class DepartmentController extends AbstractController {

	@Autowired
	private DepartmentDAO departments;

	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("department/list");
		mad.addObject("departments",departments.list());
		return mad;
	}

	@RequestMapping(value = "/form", name = "addDepartment")
	public ModelAndView form(@ModelAttribute Department department) {
		ModelAndView mad = new ModelAndView("department/form");
		return mad;
	}

	@RequestMapping(method = RequestMethod.POST, name="createDepartment", value="save")
	public ModelAndView save(@ModelAttribute("department") @Valid Department department, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			return form(department);
		}
		departments.save(department);
		redirectAttributes.addFlashAttribute("sucess", "Departamento cadastrado com sucesso!");
		return new ModelAndView("redirect:");
	}
}

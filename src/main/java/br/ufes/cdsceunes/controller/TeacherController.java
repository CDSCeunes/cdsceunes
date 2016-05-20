package br.ufes.cdsceunes.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class TeacherController extends AbstractController {

	@Autowired
	private TeacherDAO teachers;

	@Autowired
	private DepartmentDAO departments;

	@Autowired
	private DisciplineDAO disciplines;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new TeacherValidator());
		binder.registerCustomEditor(Department.class, new DepartmentEditor(departments));
	}

	/*
	 * @RequestMapping("/") public ModelAndView list() { ModelAndView mad = new
	 * ModelAndView("teacher/list"); mad.addObject("teachers", teachers.list());
	 * return mad; }
	 */

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> listAllTeachers() {
		List<Teacher> list = teachers.list();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Teacher>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Teacher>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
		teachers.update(teacher);
		return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Teacher> getUserById(@PathVariable Long id) {
		Teacher t = teachers.findById(id);
		if (t != null) {
			return new ResponseEntity<Teacher>(t, HttpStatus.OK);
		}
		return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
		teachers.save(teacher);
		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Teacher> delete(@PathVariable("id") Long id) {
		Teacher teacher = teachers.findById(id);
		if (teacher != null) {
			teachers.delete(teacher);
			return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Teacher>(HttpStatus.BAD_REQUEST);
	}
	
	/*

	@RequestMapping(value = "/form", name = "addTeacher")
	public ModelAndView form(@ModelAttribute Teacher teacher) {
		ModelAndView mad = new ModelAndView("teacher/form");
		mad.addObject("departmentList", departments.list());
		return mad;
	}

	@RequestMapping(value = "/preferences", name = "addPreference")
	public ModelAndView preference(@ModelAttribute Teacher teacher) {
		ModelAndView mad = new ModelAndView("teacher/preferenceForm");
		mad.addObject("teacherList", teachers.list());
		mad.addObject("disciplineList", disciplines.list());
		return mad;
	}

	@RequestMapping(method = RequestMethod.POST, name = "createTeacher", value = "save")
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

	@RequestMapping(method = RequestMethod.PUT, name = "createPreference", value = "save")
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
	}*/
}

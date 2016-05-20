package br.ufes.cdsceunes.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.cdsceunes.converter.DisciplineEditor;
import br.ufes.cdsceunes.converter.TeacherEditor;
import br.ufes.cdsceunes.dao.DisciplineDAO;
import br.ufes.cdsceunes.dao.PreferencesDAO;
import br.ufes.cdsceunes.dao.TeacherDAO;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.model.Teacher;
import br.ufes.cdsceunes.model.Discipline;
import br.ufes.cdsceunes.model.Preference;

@Controller
@Transactional
@RequestMapping("/preferences")
@RestController
public class PreferencesController extends AbstractController {
	
	@Autowired
	private PreferencesDAO preferences;
	
	@Autowired
	private DisciplineDAO disciplines;
	
	@Autowired
	private TeacherDAO teachers;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Discipline.class, new DisciplineEditor(disciplines));
		binder.registerCustomEditor(Teacher.class, new TeacherEditor(teachers));
	}
	
	@RequestMapping("")
	public ResponseEntity<List<Preferences>> list() {
		List<Preferences> prefs = preferences.list();
		if (prefs.isEmpty()) {
			return new ResponseEntity<List<Preferences>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Preferences>>(prefs, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/form", name = "addPreferences")
	public ModelAndView form(@ModelAttribute Preferences preferences) {
		ModelAndView mad = new ModelAndView("preference/form");
		mad.addObject("disciplineList",disciplines.list());
		mad.addObject("teacherList",teachers.list());
		mad.addObject("valueList", this.valueList());
		return mad;
	}
	
	private List<Preference> valueList() {	
		return new ArrayList<Preference>(Arrays.asList(Preference.values()));
	}

	@RequestMapping(method = RequestMethod.POST, name="createPreferences", value="save")
	public ModelAndView save(@ModelAttribute("preferences") @Valid Preferences preference, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			return form(preference);
		}
		preferences.save(preference);
		redirectAttributes.addFlashAttribute("sucess", "Preferências cadastradas com sucesso!");
		return new ModelAndView("redirect:");
	}
}

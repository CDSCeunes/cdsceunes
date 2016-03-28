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

import br.ufes.cdsceunes.converter.DisciplineEditor;
import br.ufes.cdsceunes.converter.TeacherEditor;
import br.ufes.cdsceunes.dao.DisciplineDAO;
import br.ufes.cdsceunes.dao.PreferencesDAO;
import br.ufes.cdsceunes.dao.TeacherDAO;
import br.ufes.cdsceunes.model.Discipline;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.model.Teacher;

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
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Preferences>> listAllPreferences() {
		List<Preferences> list = preferences.list();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Preferences>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Preferences>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Preferences> updatePreference(@PathVariable Long id, @RequestBody Preferences preference) {
		preferences.update(preference);
		return new ResponseEntity<Preferences>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Preferences> getUserById(@PathVariable Long id) {
		Preferences p = preferences.findById(id);
		if (p != null) {
			return new ResponseEntity<Preferences>(p, HttpStatus.OK);
		}
		return new ResponseEntity<Preferences>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Preferences> save(@RequestBody Preferences preference) {
		preferences.save(preference);
		return new ResponseEntity<Preferences>(preference, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Preferences> delete(@PathVariable("id") Long id) {
		Preferences p = preferences.findById(id);
		if (p != null) {
			preferences.delete(p);
			return new ResponseEntity<Preferences>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Preferences>(HttpStatus.BAD_REQUEST);
	}
	/*
	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("preference/list");
		mad.addObject("preferences",preferences.list());
		return mad;
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
	*/
}

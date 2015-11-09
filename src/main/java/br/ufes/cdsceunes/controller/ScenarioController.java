package br.ufes.cdsceunes.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.cdsceunes.dao.ScenarioDAO;
import br.ufes.cdsceunes.model.Scenario;

@Controller
@Transactional
@RequestMapping("/scenarios")
public class ScenarioController extends AbstractController{
	
	@Autowired
	private ScenarioDAO scenarios;
	
	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("scenario/list");
		mad.addObject("scenarios", scenarios.list());
		return mad;
	}
	
	@RequestMapping(method = RequestMethod.POST, name="createScenario", value="save")
	public ModelAndView save() {
		ModelAndView mad = new ModelAndView();
		return mad;
	}
	
	@RequestMapping(value ="/show/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable(value="id") String id ) {
		ModelAndView mad = new ModelAndView("scenario/show");
		Scenario s = scenarios.findById(Long.getLong(id));
		mad.addObject("scenario", s);
		return mad;
	}
}

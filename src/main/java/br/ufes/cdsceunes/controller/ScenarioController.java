package br.ufes.cdsceunes.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.cdsceunes.dao.PreferencesDAO;
import br.ufes.cdsceunes.dao.ScenarioDAO;
import br.ufes.cdsceunes.lib.distribution.DistributionRunner;
import br.ufes.cdsceunes.model.Preferences;
import br.ufes.cdsceunes.model.Scenario;

@Controller
@Transactional
@RequestMapping("/scenarios")
public class ScenarioController extends AbstractController{
	
	@Autowired
	private ScenarioDAO scenarios;
	
	@Autowired
	private PreferencesDAO preferencesDAO;
	
	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("scenario/list");
		mav.addObject("scenarios", scenarios.list());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, name="createScenario", value="/save")
	public ModelAndView save(@ModelAttribute("semester") String semester) {
		List<Preferences> preferences = preferencesDAO.list();
		Scenario scenario = DistributionRunner.generateDistribution(preferences);
		scenario.getDistributionList().forEach(distribution -> distribution.setScenario(scenario));
		scenarios.save(scenario);
		return new ModelAndView("redirect:");
	}
	
	@RequestMapping(value ="/show/{id}", method = RequestMethod.GET, name="showScenario")
	public ModelAndView show(@PathVariable(value="id") Long id ) {
		ModelAndView mad = new ModelAndView("scenario/show");
		Scenario s = scenarios.findById(id);
		mad.addObject("scenario", s);
		return mad;
	}
}

package br.ufes.cdsceunes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.cdsceunes.dao.PreferencesDAO;
import br.ufes.cdsceunes.dao.ScenarioDAO;
import br.ufes.cdsceunes.lib.distribution.DistributionRunner;
import br.ufes.cdsceunes.model.DistributionResult;
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
	public ModelAndView save() {
		List<Preferences> preferences = preferencesDAO.list();
		Scenario s = DistributionRunner.generateDistribution(preferences);
		scenarios.save(s);
		List<DistributionResult> distribution = s.getDistributionList();
		return new ModelAndView("redirect:");
	}
	
	@RequestMapping(value ="/show/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable(value="id") Long id ) {
		ModelAndView mad = new ModelAndView("scenario/show");
		Scenario s = scenarios.findById(id);
		mad.addObject("scenario", s);
		return mad;
	}
}

package br.ufes.cdsceunes.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.cdsceunes.dao.ScenarioDAO;

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
	
}

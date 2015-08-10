package br.ufes.cdsceunes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends AbstractController {

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mad = new ModelAndView("home/index");
		mad.addObject("name","CDS-Ceunes");
		return mad;
	}
	
}

package br.ufes.cdsceunes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends AbstractController {

	@RequestMapping("/")
	public String index() {
		/*ModelAndView mad = new ModelAndView("home/index");
		mad.addObject("name","CDS-Ceunes");
		return mad;*/
		return "index.html";
	}

}

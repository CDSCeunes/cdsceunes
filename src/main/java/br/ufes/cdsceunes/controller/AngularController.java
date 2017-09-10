package br.ufes.cdsceunes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AngularController {

	@RequestMapping("/**")
	public String index() {
		return "index";
	}
}

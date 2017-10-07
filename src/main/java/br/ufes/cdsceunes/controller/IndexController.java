package br.ufes.cdsceunes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "redirect:/app/";
	}
	
	@RequestMapping({"/app/", "/app"})
	public String app() {
		return "index";
	}
}

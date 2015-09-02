package br.ufes.cdsceunes.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.cdsceunes.dao.CommissionDAO;
import br.ufes.cdsceunes.model.Commission;

@Controller
@Transactional
@RequestMapping("/commission")
public class CommissionController extends AbstractController {

	@Autowired
	private CommissionDAO commissions;
	
	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("commission/list");
		mad.addObject("commissions",commissions.list());
		return mad;
	}

	@RequestMapping(value = "/form", name = "addCommission")
	public ModelAndView form(@ModelAttribute Commission commission) {
		ModelAndView mad = new ModelAndView("commission/form");
		return mad;
	}

	@RequestMapping(method = RequestMethod.POST, name="createCommission", value="save")
	public ModelAndView save(@ModelAttribute("commision") @Valid Commission commission, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			return form(commission);
		}
		commissions.save(commission);
		redirectAttributes.addFlashAttribute("sucess", "Comissão cadastrado com sucesso!");
		return new ModelAndView("redirect:");
	}
	
}

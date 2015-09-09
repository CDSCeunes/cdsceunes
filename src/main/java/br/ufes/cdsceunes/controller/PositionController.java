package br.ufes.cdsceunes.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.cdsceunes.converter.CommissionEditor;
import br.ufes.cdsceunes.dao.CommissionDAO;
import br.ufes.cdsceunes.dao.PositionDAO;
import br.ufes.cdsceunes.model.Commission;
import br.ufes.cdsceunes.model.Position;

@Controller
@Transactional
@RequestMapping("/positions")
public class PositionController extends AbstractController {

	@Autowired
	private PositionDAO positions;

	@Autowired
	private CommissionDAO commissions;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Commission.class, new CommissionEditor(commissions));
	}

	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mad = new ModelAndView("position/list");
		mad.addObject("positions",positions.list());
		return mad;
	}

	@RequestMapping(value = "/form", name = "addPosition")
	public ModelAndView form(@ModelAttribute Position position) {
		ModelAndView mad = new ModelAndView("position/form");
		mad.addObject("commissionList",commissions.list());
		return mad;
	}

	@RequestMapping(method = RequestMethod.POST, name="createPosition", value="save")
	public ModelAndView save(@ModelAttribute("position") @Valid Position position, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			return form(position);
		}
		positions.save(position);
		redirectAttributes.addFlashAttribute("sucess", "Cargo cadastrado com sucesso!");
		return new ModelAndView("redirect:");
	}
}
